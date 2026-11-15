package com.example.servicosaqui.model

import com.google.firebase.firestore.DocumentId

data class Anuncio(
    @DocumentId val id: String = "",
    val nome: String = "",
    val categoria: String = "",
    val descricao: String = "",
    val areaAtendida: String = "",
    val ativo: Boolean = true
)
