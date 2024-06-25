const backendUrl = process.env.BACKEND_URL;

module.exports.getArticulos = async (ctx, url) => {
  const response = await fetch(`${backendUrl}${url}`, {
    headers: {
      'Content-Type': 'application/json',
      'Authorization': `Bearer ${ctx.session.token}`,
    },
  });

  if (response.status === 401) {
    throw new Error('Debe iniciar sesión primero utilizando el comando /login');
  }

  if (!response.ok) {
    throw new Error('Error al obtener los artículos');
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
    throw new Error('Usuario o contraseña incorrectos');
  }

  ctx.session = await response.json();
}
