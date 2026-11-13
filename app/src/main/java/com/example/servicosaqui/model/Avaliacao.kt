package com.example.servicosaqui.model

data class Avaliacao(
    val id: String = "",
    val solicitacaoId: String = "",
    val nota: Int = 0,
    val comentario: String = "",
    val dataAvaliacao: Long = System.currentTimeMillis()
)