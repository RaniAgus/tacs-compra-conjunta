const { Context } = require('telegraf');
const { Articulo } = require('./backend');

/**
 * @param {Articulo} articulo
 * @returns {string} El costo del artículo formateado.
 */
const costo = ({ costo: { monto, tipo } }) => {
  switch (tipo) {
    case 'POR_PERSONA':
      return `$${monto} por persona`;
    case 'FIJO':
      return `$${monto} entre todos`;
    default:
      return `${monto}`;
  }
}

/**
 * @param {Articulo} articulo
 * @returns {string} El estado del artículo formateado.
 */
const estado = ({ estado, maxPersonas, compradores }) => {
  const restantes = maxPersonas - compradores.length;
  switch (estado) {
    case 'PUBLICADO':
      return `¡Falta${s(restantes, 'n')} ${restantes} persona${s(restantes)}!`;
    case 'VENDIDO':
      return `¡Vendido!`;
    default:
      return 'Cancelado';
  }
}

const compradores = ({ compradores }, { showCompradores = false } = {}) => {
  return Boolean(showCompradores) ? compradores.map(({ nombreDeUsuario }) => `\n- ${nombreDeUsuario}`).join('') : '';
}

/**
 * @param {number | any[]} value
 * @param {string} plural
 * @param {string} singular
 * @returns {string} La 's' si el valor es plural.
*/
const s = (value, plural = 's', singular = '') => {
  return (Array.isArray(value) ? value.length : value) === 1 ? singular : plural;
}

/**
 *
 * @param {Context<import('@telegraf/types').Update>} ctx
 * @param {Array<Articulo>} articulos
 * @param {{ showCompradores?: true }} options
 */
const mostrarArticulos = async (ctx, articulos, opts) => {
  for (const articulo of articulos) {
    await ctx.replyWithPhoto({ url: articulo.imagen }, {
      caption: `${articulo.nombre} - ${costo(articulo)}\n${estado(articulo)}${compradores(articulo, opts)}`,
    });
  }
}

module.exports = {
  s,
  mostrarArticulos,
};
