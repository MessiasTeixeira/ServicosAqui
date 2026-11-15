package com.example.servicosaqui.model

import org.junit.Assert.assertEquals
import org.junit.Test

class AreaAtendidaTest {

    @Test
    fun montaBairroCidadeEUf() {
        val endereco = Endereco(bairro = "Manaíra", localidade = "João Pessoa", uf = "PB")
        assertEquals("Manaíra — João Pessoa/PB", montarAreaAtendida(endereco))
    }

    @Test
    fun semBairroMontaSoCidadeEUf() {
        val endereco = Endereco(bairro = "", localidade = "João Pessoa", uf = "PB")
        assertEquals("João Pessoa/PB", montarAreaAtendida(endereco))
    }

    @Test
    fun bairroSoComEspacosContaComoVazio() {
        val endereco = Endereco(bairro = "  ", localidade = "Campina Grande", uf = "PB")
        assertEquals("Campina Grande/PB", montarAreaAtendida(endereco))
    }

    @Test
    fun removeEspacosSobrandoNasPontas() {
        val endereco = Endereco(bairro = " Tambaú ", localidade = " João Pessoa ", uf = " PB ")
        assertEquals("Tambaú — João Pessoa/PB", montarAreaAtendida(endereco))
    }
}
