package com.example.servicosaqui.viewmodels

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.servicosaqui.model.Anuncio
import com.example.servicosaqui.model.AnuncioDao
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

data class PerfilUIState(
    val anuncio: Anuncio? = null,
    val carregando: Boolean = false,
    val erro: String? = null
)

class PerfilViewModel : ViewModel() {
    private val dao = AnuncioDao()

    private val _uiState = MutableStateFlow(PerfilUIState())
    val uiState: StateFlow<PerfilUIState> = _uiState

    fun carregar(id: String) {
        viewModelScope.launch {
            _uiState.update { it.copy(carregando = true, erro = null) }
            try {
                val anuncio = dao.buscarPorId(id)
                _uiState.update {
                    it.copy(
                        anuncio = anuncio,
                        carregando = false,
                        erro = if (anuncio == null) "Este anúncio não está mais disponível." else null
                    )
                }
            } catch (e: Exception) {
                _uiState.update {
                    it.copy(carregando = false, erro = "Não foi possível carregar o perfil. Verifique a conexão e tente de novo.")
                }
            }
        }
    }
}
