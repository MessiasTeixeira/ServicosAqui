package com.example.servicosaqui.model;

data class Usuario(
    val id: String = "",
    val nome: String = "",
    val email: String = "",
    val telefone: String = "",
    val dataCadastro: Long = System.currentTimeMillis()
)