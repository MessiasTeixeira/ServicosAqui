package com.example.servicosaqui.clientservice

import com.example.servicosaqui.model.Endereco
import retrofit2.http.GET
import retrofit2.http.Path

interface EnderecoAPI {
    @GET("/ws/{cep}/json/")
    suspend fun getEnderecoByCEP(@Path("cep") cep: String): Endereco
}
