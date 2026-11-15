package com.example.servicosaqui.model

import org.junit.Assert.assertEquals
import org.junit.Test

class FiltroCatalogoTest {

    private val maria = Anuncio(
        id = "1",
        nome = "Maria Limpezas",
        categoria = "Limpeza",
        descricao = "Faxina completa de casas",
        areaAtendida = "Manaíra — João Pessoa/PB"
    )
    private val jose = Anuncio(
        id = "2",
        nome = "José Eletricista",
        categoria = "Elétrica",
        descricao = "Instalação de tomadas e chuveiros",
        areaAtendida = "Bessa — João Pessoa/PB"
    )
    private val ana = Anuncio(
        id = "3",
        nome = "Ana Pinturas",
        categoria = "Pintura",
        descricao = "Pintura de paredes e portões",
        areaAtendida = "Centro — Campina Grande/PB"
    )
    private val todos = listOf(maria, jose, ana)

    @Test
    fun semTermoESemCategoriaDevolveTodos() {
        assertEquals(todos, filtrarAnuncios(todos, "", null))
    }

    @Test
    fun termoEmBrancoContaComoVazio() {
        assertEquals(todos, filtrarAnuncios(todos, "   ", null))
    }

    @Test
    fun filtraPorCategoria() {
        assertEquals(listOf(jose), filtrarAnuncios(todos, "", "Elétrica"))
    }

    @Test
    fun termoIgnoraMaiusculasEAcentos() {
        assertEquals(listOf(jose), filtrarAnuncios(todos, "ELETRICA", null))
        assertEquals(listOf(maria), filtrarAnuncios(todos, "manaira", null))
    }

    @Test
    fun termoProcuraEmNome() {
        assertEquals(listOf(ana), filtrarAnuncios(todos, "pinturas", null))
    }

    @Test
    fun termoProcuraEmDescricao() {
        assertEquals(listOf(jose), filtrarAnuncios(todos, "chuveiros", null))
    }

    @Test
    fun termoProcuraEmAreaAtendida() {
        assertEquals(listOf(ana), filtrarAnuncios(todos, "campina", null))
    }

    @Test
    fun termoECategoriaCombinam() {
        assertEquals(listOf(maria), filtrarAnuncios(todos, "joao pessoa", "Limpeza"))
        assertEquals(emptyList<Anuncio>(), filtrarAnuncios(todos, "campina", "Limpeza"))
    }

    @Test
    fun semResultadoDevolveListaVazia() {
        assertEquals(emptyList<Anuncio>(), filtrarAnuncios(todos, "marcenaria", null))
    }
}
