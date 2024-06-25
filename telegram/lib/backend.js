const backendUrl = process.env.BACKEND_URL;

module.exports.getArticulos = async (ctx, url) => {
  const response = await fetch(`${backendUrl}${url}`, {
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${ctx.session.token}`,
    },
  });

  if (response.status === 401) {
    await ctx.reply('Debe iniciar sesión primero');
    return ctx.scene.enter(loginScene.id);
  }

  if (!response.ok) {
    await ctx.reply('Error al obtener los artículos');
    return ctx.scene.leave();
  }

  return response.json();
}

module.exports.iniciarSesion = async (ctx, data) => {
  const response = await fetch(`${backendUrl}/iniciarSesion`, {
    method: 'POST',
    body: JSON.stringify(data),
    headers: {
      'Content-Type': 'application/json',
    },
  });

  if (!response.ok) {
    await ctx.reply('Usuario o contraseña incorrectos');
    return ctx.scene.leave();
  }

  ctx.session = await response.json();
}
