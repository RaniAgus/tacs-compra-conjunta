const { Telegraf, session, Scenes } = require('telegraf');
const { Redis } = require('@telegraf/session/redis');

const scenes = require('./scenes');
const commands = require('./commands');

const bot   = new Telegraf(process.env.TELEGRAM_BOT_TOKEN),
      store = Redis({ url: process.env.REDIS_ENDPOINT }),
      stage = new Scenes.Stage(Object.values(scenes));

bot.use(session({ store }));
bot.use(stage.middleware());

bot.start((ctx) => ctx.reply(
`Hola ${ctx.from.first_name}! Bienvenido a la compra conjunta de productos.
Seleccioná una opción del menú para comenzar.`
));

const helpMessage = Object.entries(commands)
  .reduce((helpMessage, [command, { handler, description }]) => {
    bot.command(command, handler);
    return helpMessage + `\n/${command} - ${description}`;
  }, 'Comandos disponibles:');

bot.help(ctx => ctx.reply(helpMessage));

module.exports = bot;
