const backendUrl = process.env.BACKEND_URL;

/**
 * @typedef {Object} Articulo
 * @property {string} id
 * @property {string} nombre
 * @property {string} imagen
 * @property {string} deadline
 * @property {number} minPersonas
 * @property {number} maxPersonas
 * @property {{ tipo: 'POR_PERSONA' | 'FIJO', monto: number }} costo
 * @property {string} recepcion
 * @property {Array<{ id: string, nombreDeUsuario: string }>} compradores
 * @property {'ABIERTO' | 'VENDIDO' | 'CERRADO'} estado
 */

/**
 * Realiza una petición al backend para obtener los artículos.
 *
 * @param {'/articulos' | '/usuarios/me/compras' | '/usuarios/me/articulos'} url
 * @returns {Promise<Articulo[]>}
 */
module.exports.getArticulos = async (url, token) => {
  const response = await fetch(`${backendUrl}${url}`, {
    headers: {
      'Content-Type': 'application/json',
      'Authorization': token ? `Bearer ${token}` : undefined,
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

/**
 * Inicia sesión en el backend.
 *
 * @param {{ nombreDeUsuario: string, contrasenia: string }} data
 * @returns {Promise<{ token: string }>}
 */
module.exports.iniciarSesion = async (data) => {
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

  return await response.json();
}
