/**
 * Returns the plural or singular form of a word based on the value.
 *
 * @param {number | any[]} value
 * @param {string} plural
 * @param {string} singular
*/
module.exports.s = (value, plural = 's', singular = '') => {
  return (Array.isArray(value) ? value.length : value) === 1 ? singular : plural;
}

module.exports.mostrarArticulos = async (ctx, articulos, caption) => {
  for (const articulo of articulos) {
    await ctx.replyWithPhoto({ url: articulo.imagen }, { caption: caption(articulo) });
  }
}

module.exports.compradoresFaltan = (maxPersonas, compradoresLength) => {
  return "¡Faltan " + (maxPersonas - compradoresLength) + " compradores!"
}
