package com.example.servicosaqui.model

import org.junit.Assert.assertEquals
import org.junit.Assert.assertTrue
import org.junit.Test

class ValidacaoAnuncioTest {

    private val completo = Anuncio(
        nome = "Maria Limpezas",
        categoria = "Limpeza",
        descricao = "Faxina completa de casas e apartamentos",
        areaAtendida = "Manaíra — João Pessoa/PB"
    )

    @Test
    fun anuncioCompletoNaoTemCamposInvalidos() {
        assertTrue(validarAnuncio(completo).isEmpty())
    }

    @Test
    fun nomeSoComEspacosEhInvalido() {
        assertEquals(setOf(CampoAnuncio.NOME), validarAnuncio(completo.copy(nome = "   ")))
    }

    @Test
    fun cadaCampoObrigatorioVazioEhApontado() {
        assertEquals(setOf(CampoAnuncio.CATEGORIA), validarAnuncio(completo.copy(categoria = "")))
        assertEquals(setOf(CampoAnuncio.DESCRICAO), validarAnuncio(completo.copy(descricao = "")))
        assertEquals(setOf(CampoAnuncio.AREA_ATENDIDA), validarAnuncio(completo.copy(areaAtendida = "")))
    }

    @Test
    fun anuncioVazioApontaTodosOsCampos() {
        assertEquals(CampoAnuncio.entries.toSet(), validarAnuncio(Anuncio()))
    }

    @Test
    fun ativoNaoEhObrigatorio() {
        assertTrue(validarAnuncio(completo.copy(ativo = false)).isEmpty())
    }
}
