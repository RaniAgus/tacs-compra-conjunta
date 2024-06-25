const { Telegraf, session, Scenes } = require('telegraf');
const { Redis } = require('@telegraf/session/redis');

const { loginScene } = require('./loginScene');
const { getArticulos } = require('../lib/backend');
const { mostrarArticulos, compradoresFaltan } = require('../lib/utils');

const bot   = new Telegraf(process.env.TELEGRAM_BOT_TOKEN),
      store = Redis({ url: process.env.REDIS_ENDPOINT }),
      stage = new Scenes.Stage([loginScene]);

const s = (array) => array.length !== 1 ? 's' : '';

bot.use(session({ store }));
bot.use(stage.middleware());

bot.start((ctx) => ctx.reply(
  `Hola ${ctx.from.first_name}! Bienvenido a la compra conjunta de productos.`
));

bot.command('login', ctx => ctx.scene.enter(loginScene.id));

bot.command('logout', ctx => {
  ctx.session.token = null;
  ctx.reply('Sesión cerrada');
})

bot.command('articulos', async ctx => {
  const articulos = await getArticulos(ctx, '/articulos');
  await ctx.reply(`${articulos.length} artículo${s(articulos)} disponible${s(articulos)}:`);
  await mostrarArticulos(ctx, articulos, articulo => `${articulo.nombre} - \$${articulo.costo.monto} ${articulo.costo.tipo === 'POR_PERSONA' ? 'por persona' : 'fijo'}`);
  return ctx.scene.leave();
});

bot.command('compras', async ctx => {
  const articulos = await getArticulos(ctx, '/usuarios/me/compras');
  await ctx.reply(`${articulos.length} artículo${s(articulos)} comprado${s(articulos)}:`);
  await mostrarArticulos(ctx, articulos, articulo => `${articulo.nombre} - \$${articulo.costo.monto} ${articulo.costo.tipo === 'POR_PERSONA' ? 'por persona' : 'fijo'}\n Estado: ${articulo.estado} \n ${compradoresFaltan(articulo.maxPersonas, articulo.compradores.length)}`);
  return ctx.scene.leave();
});

bot.command('publicaciones', async ctx => {
  const articulos = await getArticulos(ctx, '/usuarios/me/articulos');
  await ctx.reply(`${articulos.length} artículo${s(articulos)} publicado${s(articulos)}:`);
  await mostrarArticulos(ctx, articulos, articulo => {
    const compradores = articulo.compradores.map(comprador => `- ${comprador.nombreDeUsuario}`).join('\n');
    return `${articulo.nombre} - \$${articulo.costo.monto} ${articulo.costo.tipo === 'POR_PERSONA' ? 'por persona' : 'fijo'}\n Estado: ${articulo.estado} \n ${compradoresFaltan(articulo.maxPersonas, articulo.compradores.length)} \n Compradores:\n ${compradores}`
  });
  return ctx.scene.leave();
});

module.exports.bot = bot;
