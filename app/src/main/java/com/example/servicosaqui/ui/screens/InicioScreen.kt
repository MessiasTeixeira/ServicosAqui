package com.example.servicosaqui.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.servicosaqui.model.Anuncio
import com.example.servicosaqui.model.Categoria
import com.example.servicosaqui.model.filtrarAnuncios
import com.example.servicosaqui.viewmodels.CatalogoViewModel

@Composable
fun InicioScreen(
    onAnuncioClick: (String) -> Unit,
    viewModel: CatalogoViewModel = viewModel()
) {
    val estado by viewModel.uiState.collectAsState()

    LaunchedEffect(Unit) {
        viewModel.carregar()
    }

    val filtrados = filtrarAnuncios(estado.anuncios, estado.termo, estado.categoria)

    Column(modifier = Modifier.fillMaxSize()) {
        Text(
            text = "ServiçosAqui",
            style = MaterialTheme.typography.headlineSmall,
            color = MaterialTheme.colorScheme.primary,
            modifier = Modifier.padding(start = 20.dp, top = 24.dp, end = 20.dp)
        )

        OutlinedTextField(
            value = estado.termo,
            onValueChange = viewModel::alterarTermo,
            modifier = Modifier
                .fillMaxWidth()
                .padding(start = 20.dp, top = 16.dp, end = 20.dp),
            placeholder = { Text("Buscar serviço, nome ou bairro") },
            leadingIcon = { Icon(Icons.Filled.Search, contentDescription = null) },
            singleLine = true
        )

        LazyRow(
            contentPadding = PaddingValues(horizontal = 20.dp, vertical = 12.dp),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(Categoria.todas) { categoria ->
                FilterChip(
                    selected = estado.categoria == categoria,
                    onClick = { viewModel.alternarCategoria(categoria) },
                    label = { Text(categoria) }
                )
            }
        }

        when {
            estado.erro != null -> {
                Column(modifier = Modifier.padding(20.dp), verticalArrangement = Arrangement.spacedBy(8.dp)) {
                    Text(text = estado.erro.orEmpty(), color = MaterialTheme.colorScheme.error)
                    Button(onClick = { viewModel.carregar() }) {
                        Text("Tentar de novo")
                    }
                }
            }

            estado.carregando && estado.anuncios.isEmpty() -> {
                CircularProgressIndicator(modifier = Modifier.padding(20.dp))
            }

            filtrados.isEmpty() -> {
                Text(
                    text = if (estado.termo.isBlank() && estado.categoria == null) {
                        "Ainda não há anúncios ativos."
                    } else {
                        "Nenhum anúncio encontrado para essa busca."
                    },
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(20.dp)
                )
            }

            else -> {
                LazyColumn(
                    contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 20.dp),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    items(filtrados, key = { it.id }) { anuncio ->
                        CartaoCatalogo(anuncio = anuncio, onClick = { onAnuncioClick(anuncio.id) })
                    }
                }
            }
        }
    }
}

@Composable
private fun CartaoCatalogo(anuncio: Anuncio, onClick: () -> Unit) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Column(modifier = Modifier.padding(16.dp)) {
            Text(
                text = anuncio.nome,
                style = MaterialTheme.typography.titleMedium,
                color = MaterialTheme.colorScheme.onSurface
            )
            Text(
                text = anuncio.categoria,
                style = MaterialTheme.typography.labelLarge,
                color = MaterialTheme.colorScheme.primary
            )
            Text(
                text = anuncio.areaAtendida,
                style = MaterialTheme.typography.bodyMedium,
                color = MaterialTheme.colorScheme.onSurfaceVariant
            )
        }
    }
}
