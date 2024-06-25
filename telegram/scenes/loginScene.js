const { Scenes } = require('telegraf');
const { iniciarSesion } = require('../lib/backend');

module.exports.loginScene = new Scenes.WizardScene(
  "Login",
  async (ctx) => {
    ctx.wizard.state.data = {};
    await ctx.reply("Ingrese su usuario");
    return ctx.wizard.next();
  },
  async (ctx) => {
    ctx.wizard.state.data.nombreDeUsuario = ctx.message.text;
    await ctx.reply("Ingrese su contraseña");
    return ctx.wizard.next();
  },
  async (ctx) => {
    ctx.wizard.state.data.contrasenia = ctx.message.text;
    await iniciarSesion(ctx, ctx.wizard.state.data);
    await ctx.reply("Inicio de sesión exitoso");
    return ctx.scene.leave();
  },
);
