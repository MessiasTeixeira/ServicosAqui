package com.example.servicosaqui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servicosaqui.model.Anuncio
import com.example.servicosaqui.model.AnuncioDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class CatalogoUIState(
    val anuncios: List<Anuncio> = emptyList(),
    val termo: String = "",
    val categoria: String? = null,
    val carregando: Boolean = false,
    val erro: String? = null
)

class CatalogoViewModel : ViewModel() {
    private val dao = AnuncioDao()

    private val _uiState = MutableStateFlow(CatalogoUIState())
    val uiState: StateFlow<CatalogoUIState> = _uiState

    fun carregar() {
        viewModelScope.launch {
            _uiState.update { it.copy(carregando = true, erro = null) }
            try {
                val anuncios = dao.listarAtivos().sortedBy { it.nome.lowercase() }
                _uiState.update { it.copy(anuncios = anuncios, carregando = false) }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(carregando = false, erro = "Sem conexão com o catálogo. Verifique a internet e tente de novo.")
                }
            }
        }
    }

    fun alterarTermo(termo: String) {
        _uiState.update { it.copy(termo = termo) }
    }

    fun alternarCategoria(categoria: String) {
        _uiState.update { it.copy(categoria = if (it.categoria == categoria) null else categoria) }
    }
}
