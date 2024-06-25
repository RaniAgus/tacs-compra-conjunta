module.exports.mostrarArticulos = async (ctx, articulos, caption) => {
  for (const articulo of articulos) {
    await ctx.replyWithPhoto({ url: articulo.imagen }, { caption: caption(articulo) });
  }
}

module.exports.compradoresFaltan = (maxPersonas, compradoresLength) => {
  return "¡Faltan " + (maxPersonas - compradoresLength) + " compradores!"
}
