package com.example.servicosaqui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servicosaqui.clientservice.CepNaoEncontradoException
import com.example.servicosaqui.clientservice.CepRepository
import com.example.servicosaqui.model.Anuncio
import com.example.servicosaqui.model.AnuncioDao
import com.example.servicosaqui.model.CampoAnuncio
import com.example.servicosaqui.model.montarAreaAtendida
import com.example.servicosaqui.model.validarAnuncio
import java.io.IOException
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AnuncioFormUIState(
    val id: String = "",
    val nome: String = "",
    val categoria: String = "",
    val descricao: String = "",
    val areaAtendida: String = "",
    val ativo: Boolean = true,
    val camposInvalidos: Set<CampoAnuncio> = emptySet(),
    val carregando: Boolean = false,
    val salvando: Boolean = false,
    val erro: String? = null,
    val salvo: Boolean = false,
    val cep: String = "",
    val buscandoCep: Boolean = false,
    val erroCep: String? = null
)

class AnuncioFormViewModel : ViewModel() {
    private val dao = AnuncioDao()
    private val cepRepository = CepRepository()

    private val _uiState = MutableStateFlow(AnuncioFormUIState())
    val uiState: StateFlow<AnuncioFormUIState> = _uiState

    fun carregar(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(carregando = true, erro = null) }
            try {
                val anuncio = dao.buscarPorId(id)
                if (anuncio == null) {
                    _uiState.update { it.copy(carregando = false, erro = "Anúncio não encontrado.") }
                } else {
                    _uiState.update {
                        it.copy(
                            id = anuncio.id,
                            nome = anuncio.nome,
                            categoria = anuncio.categoria,
                            descricao = anuncio.descricao,
                            areaAtendida = anuncio.areaAtendida,
                            ativo = anuncio.ativo,
                            carregando = false
                        )
                    }
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(carregando = false, erro = "Não foi possível carregar o anúncio. Verifique a conexão e tente de novo.")
                }
            }
        }
    }

    fun alterarNome(nome: String) {
        _uiState.update { it.copy(nome = nome, camposInvalidos = it.camposInvalidos - CampoAnuncio.NOME) }
    }

    fun alterarCategoria(categoria: String) {
        _uiState.update { it.copy(categoria = categoria, camposInvalidos = it.camposInvalidos - CampoAnuncio.CATEGORIA) }
    }

    fun alterarDescricao(descricao: String) {
        _uiState.update { it.copy(descricao = descricao, camposInvalidos = it.camposInvalidos - CampoAnuncio.DESCRICAO) }
    }

    fun alterarAreaAtendida(areaAtendida: String) {
        _uiState.update { it.copy(areaAtendida = areaAtendida, camposInvalidos = it.camposInvalidos - CampoAnuncio.AREA_ATENDIDA) }
    }

    fun alterarCep(cep: String) {
        _uiState.update { it.copy(cep = cep.filter { c -> c.isDigit() }.take(8), erroCep = null) }
    }

    fun buscarCep() {
        val cep = _uiState.value.cep
        if (cep.length != 8) {
            _uiState.update { it.copy(erroCep = "Informe um CEP com 8 números.") }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(buscandoCep = true, erroCep = null) }
            val resultado = cepRepository.getEnderecoByCEP(cep)
            resultado.fold(
                onSuccess = { endereco ->
                    _uiState.update {
                        it.copy(
                            areaAtendida = montarAreaAtendida(endereco),
                            camposInvalidos = it.camposInvalidos - CampoAnuncio.AREA_ATENDIDA,
                            buscandoCep = false
                        )
                    }
                },
                onFailure = { erro ->
                    val mensagem = when (erro) {
                        is CepNaoEncontradoException -> "CEP não encontrado. Confira os números."
                        is IOException -> "Sem conexão. Verifique a internet e tente de novo."
                        else -> "Não foi possível consultar o CEP. Tente de novo."
                    }
                    _uiState.update { it.copy(buscandoCep = false, erroCep = mensagem) }
                }
            )
        }
    }

    fun alterarAtivo(ativo: Boolean) {
        _uiState.update { it.copy(ativo = ativo) }
    }

    fun salvar() {
        val estado = _uiState.value
        val anuncio = Anuncio(
            id = estado.id,
            nome = estado.nome.trim(),
            categoria = estado.categoria,
            descricao = estado.descricao.trim(),
            areaAtendida = estado.areaAtendida.trim(),
            ativo = estado.ativo
        )
        val invalidos = validarAnuncio(anuncio)
        if (invalidos.isNotEmpty()) {
            _uiState.update { it.copy(camposInvalidos = invalidos) }
            return
        }
        viewModelScope.launch {
            _uiState.update { it.copy(salvando = true, erro = null) }
            try {
                if (anuncio.id.isEmpty()) dao.criar(anuncio) else dao.atualizar(anuncio)
                _uiState.update { it.copy(salvando = false, salvo = true) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(salvando = false, erro = "Não foi possível salvar o anúncio. Verifique a conexão e tente de novo.")
                }
            }
        }
    }
}
