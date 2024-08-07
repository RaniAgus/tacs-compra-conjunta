const { Scenes } = require('telegraf');
const { iniciarSesion } = require('../lib/backend');

/**
 * @type {{[name: string]: Scenes.BaseScene<Scenes.SceneContext>}}
 */
const scenes = {};

scenes.login = new Scenes.WizardScene(
  'login',
  async (ctx) => {
    ctx.wizard.state.data = {};
    await ctx.reply("Ingrese su usuario");
    return ctx.wizard.next();
  },
  async (ctx) => {
    ctx.wizard.state.data.nombreDeUsuario = ctx.message.text;
    ctx.deleteMessage(ctx.message.message_id);
    await ctx.reply("Ingrese su contraseña");
    return ctx.wizard.next();
  },
  async (ctx) => {
    ctx.wizard.state.data.contrasenia = ctx.message.text;
    ctx.deleteMessage(ctx.message.message_id);
    try {
      ctx.session = await iniciarSesion(ctx.wizard.state.data);
      await ctx.reply("Inicio de sesión exitoso");
    } catch (err) {
      await ctx.reply(err.message);
    }
    return ctx.scene.leave();
  },
);

module.exports = scenes;
