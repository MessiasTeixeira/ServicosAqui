package com.example.servicosaqui.model

import java.text.Normalizer

private val marcasDeAcento = Regex("\\p{InCombiningDiacriticalMarks}+")

private fun normalizar(texto: String): String {
    return Normalizer.normalize(texto.trim(), Normalizer.Form.NFD)
        .replace(marcasDeAcento, "")
        .lowercase()
}

fun filtrarAnuncios(anuncios: List<Anuncio>, termo: String, categoria: String?): List<Anuncio> {
    val termoNormalizado = normalizar(termo)
    return anuncios.filter { anuncio ->
        val daCategoria = categoria == null || anuncio.categoria == categoria
        val doTermo = termoNormalizado.isEmpty() || listOf(
            anuncio.nome,
            anuncio.categoria,
            anuncio.descricao,
            anuncio.areaAtendida
        ).any { normalizar(it).contains(termoNormalizado) }
        daCategoria && doTermo
    }
}
