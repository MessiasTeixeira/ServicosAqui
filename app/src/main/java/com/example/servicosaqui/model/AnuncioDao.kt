package com.example.servicosaqui.model

import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.toObject
import com.google.firebase.firestore.toObjects
import kotlinx.coroutines.tasks.await

class AnuncioDao {
    private val colecao by lazy { FirebaseFirestore.getInstance().collection("anuncios") }

    suspend fun criar(anuncio: Anuncio): String {
        return colecao.add(anuncio).await().id
    }

    suspend fun listarTodos(): List<Anuncio> {
        return colecao.get().await().toObjects<Anuncio>()
    }

    suspend fun listarAtivos(): List<Anuncio> {
        return colecao.whereEqualTo("ativo", true).get().await().toObjects<Anuncio>()
    }

    suspend fun buscarPorId(id: String): Anuncio? {
        val documento = colecao.document(id).get().await()
        return if (documento.exists()) documento.toObject<Anuncio>() else null
    }

    suspend fun atualizar(anuncio: Anuncio) {
        colecao.document(anuncio.id).set(anuncio).await()
    }

    suspend fun excluir(anuncio: Anuncio) {
        colecao.document(anuncio.id).delete().await()
    }
}
