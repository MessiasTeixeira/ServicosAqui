package com.example.servicosaqui.model

data class ServicoPrestador(
    val id: String = "",
    val prestadorId: String = "",
    val categoriaId: String = "",
    val titulo: String = "",
    val descricao: String = "",
    val precoBase: Double = 0.0
)