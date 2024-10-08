const { Middleware, Context } = require('telegraf');
const scenes = require('./scenes');
const { getArticulos } = require('../lib/backend');
const { convertToMediaGroup } = require('../lib/utils');

/**
 * @typedef {Object} Command
 * @property {string} description
 * @property {Middleware<Context>} handler
 */

/**
 * @type {{[name: string]: Command}}
 */
const commands = {};

commands.login = {
  description: 'Iniciar sesión',
  handler: (ctx) => ctx.scene.enter(scenes.login.id),
};

commands.logout = {
  description: 'Cerrar sesión',
  handler: ctx => {
    ctx.session.token = null;
    ctx.reply('Sesión cerrada');
  }
};

commands.articulos = {
  description: 'Ver artículos disponibles',
  handler: async ctx => {
    try {
      const articulos = await getArticulos('/articulos', ctx.session.token);
      const { title, media } = convertToMediaGroup(articulos, { type: 'disponible' });
      await ctx.reply(title);
      await ctx.sendMediaGroup(media, { disable_notification: true });
    } catch (err) {
      await ctx.reply(err.message);
    }
    return ctx.scene.leave();
  }
};

commands.compras = {
  description: 'Ver mis artículos comprados',
  handler: async ctx => {
    try {
      const articulos = await getArticulos('/usuarios/me/compras', ctx.session.token);
      const { title, media } = convertToMediaGroup(articulos, { type: 'comprado' });
      await ctx.reply(title);
      await ctx.sendMediaGroup(media, { disable_notification: true });
    } catch (err) {
      await ctx.reply(err.message);
    }
    return ctx.scene.leave();
  }
};

commands.publicaciones = {
  description: 'Ver mis publicaciones',
  handler: async ctx => {
    try {
      const articulos = await getArticulos('/usuarios/me/articulos', ctx.session.token);
      const { title, media } = convertToMediaGroup(articulos, { type: 'publicado', showCompradores: true });
      await ctx.reply(title);
      await ctx.sendMediaGroup(media, { disable_notification: true });
    } catch (err) {
      await ctx.reply(err.message);
    }
    return ctx.scene.leave();
  }
};

module.exports = commands;
