package com.example.servicosaqui.model

data class Solicitacao(
    val id: String = "",
    val clienteId: String = "",
    val servicoId: String = "",
    val dataSolicitacao: Long = System.currentTimeMillis(),
    val dataAgendada: Long? = null,
    val descricao: String = "",
    val endereco: String = "",
    val status: String = "PENDENTE",
    val valorFinal: Double? = null
)