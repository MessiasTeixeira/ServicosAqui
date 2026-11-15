package com.example.servicosaqui.model

enum class CampoAnuncio {
    NOME,
    CATEGORIA,
    DESCRICAO,
    AREA_ATENDIDA
}

fun validarAnuncio(anuncio: Anuncio): Set<CampoAnuncio> {
    val invalidos = mutableSetOf<CampoAnuncio>()
    if (anuncio.nome.isBlank()) invalidos.add(CampoAnuncio.NOME)
    if (anuncio.categoria.isBlank()) invalidos.add(CampoAnuncio.CATEGORIA)
    if (anuncio.descricao.isBlank()) invalidos.add(CampoAnuncio.DESCRICAO)
    if (anuncio.areaAtendida.isBlank()) invalidos.add(CampoAnuncio.AREA_ATENDIDA)
    return invalidos
}
