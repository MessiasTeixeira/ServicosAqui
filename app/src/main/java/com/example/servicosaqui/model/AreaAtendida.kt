package com.example.servicosaqui.model

fun montarAreaAtendida(endereco: Endereco): String {
    val bairro = endereco.bairro.trim()
    val cidadeEUf = "${endereco.localidade.trim()}/${endereco.uf.trim()}"
    return if (bairro.isEmpty()) cidadeEUf else "$bairro — $cidadeEUf"
}
