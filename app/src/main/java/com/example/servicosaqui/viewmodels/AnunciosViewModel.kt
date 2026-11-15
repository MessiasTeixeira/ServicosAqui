package com.example.servicosaqui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servicosaqui.model.Anuncio
import com.example.servicosaqui.model.AnuncioDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class AnunciosUIState(
    val anuncios: List<Anuncio> = emptyList(),
    val carregando: Boolean = false,
    val erro: String? = null
)

class AnunciosViewModel : ViewModel() {
    private val dao = AnuncioDao()

    private val _uiState = MutableStateFlow(AnunciosUIState())
    val uiState: StateFlow<AnunciosUIState> = _uiState

    fun carregar() {
        viewModelScope.launch {
            _uiState.update { it.copy(carregando = true, erro = null) }
            try {
                val anuncios = dao.listarTodos().sortedBy { it.nome.lowercase() }
                _uiState.update { it.copy(anuncios = anuncios, carregando = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(carregando = false, erro = "Não foi possível carregar os anúncios. Verifique a conexão e tente de novo.")
                }
            }
        }
    }

    fun alternarAtivo(anuncio: Anuncio) {
        viewModelScope.launch {
            try {
                dao.atualizar(anuncio.copy(ativo = !anuncio.ativo))
                carregar()
            } catch (e: Exception) {
                _uiState.update { it.copy(erro = "Não foi possível atualizar o anúncio. Tente de novo.") }
            }
        }
    }

    fun excluir(anuncio: Anuncio) {
        viewModelScope.launch {
            try {
                dao.excluir(anuncio)
                carregar()
            } catch (e: Exception) {
                _uiState.update { it.copy(erro = "Não foi possível excluir o anúncio. Tente de novo.") }
            }
        }
    }
}
