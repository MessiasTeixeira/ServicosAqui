package com.example.servicosaqui.clientservice

import com.example.servicosaqui.model.Endereco
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

class CepNaoEncontradoException : Exception("CEP não encontrado")

class CepRepository {

    companion object {
        val retrofit = Retrofit.Builder()
            .baseUrl("https://viacep.com.br")
            .addConverterFactory(GsonConverterFactory.create())
            .build()
        val service = retrofit.create(EnderecoAPI::class.java)
    }

    suspend fun getEnderecoByCEP(cep: String): Result<Endereco> {
        return try {
            val endereco = service.getEnderecoByCEP(cep)
            if (endereco.erro) {
                Result.failure(CepNaoEncontradoException())
            } else {
                Result.success(endereco)
            }
        } catch (e: Exception) {
            Result.failure(e)
        }
    }
}
