package com.example.servicosaqui.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.servicosaqui.model.Anuncio
import com.example.servicosaqui.viewmodels.AnunciosViewModel

@Composable
fun AnunciosScreen(
    onNovoClick: () -> Unit,
    onAnuncioClick: (String) -> Unit,
    viewModel: AnunciosViewModel = viewModel()
) {
    val estado by viewModel.uiState.collectAsState()
    var anuncioParaExcluir by remember { mutableStateOf<Anuncio?>(null) }

    LaunchedEffect(Unit) {
        viewModel.carregar()
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        floatingActionButton = {
            FloatingActionButton(onClick = onNovoClick) {
                Icon(Icons.Filled.Add, contentDescription = "Novo anúncio")
            }
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
        ) {
            Text(
                text = "Anúncios",
                style = MaterialTheme.typography.headlineSmall,
                color = MaterialTheme.colorScheme.onBackground,
                modifier = Modifier.padding(start = 20.dp, top = 24.dp, end = 20.dp, bottom = 8.dp)
            )

            if (estado.erro != null) {
                Column(modifier = Modifier.padding(20.dp)) {
                    Text(
                        text = estado.erro.orEmpty(),
                        color = MaterialTheme.colorScheme.error
                    )
                    Button(onClick = { viewModel.carregar() }) {
                        Text("Tentar de novo")
                    }
                }
            }

            if (estado.carregando && estado.anuncios.isEmpty()) {
                CircularProgressIndicator(modifier = Modifier.padding(20.dp))
            } else if (estado.anuncios.isEmpty() && estado.erro == null) {
                Text(
                    text = "Nenhum anúncio ainda. Toque em + para criar o primeiro.",
                    color = MaterialTheme.colorScheme.onSurfaceVariant,
                    modifier = Modifier.padding(20.dp)
                )
            }

            LazyColumn(
                contentPadding = PaddingValues(start = 20.dp, end = 20.dp, bottom = 88.dp),
                verticalArrangement = Arrangement.spacedBy(12.dp)
            ) {
                items(estado.anuncios, key = { it.id }) { anuncio ->
                    CartaoGerenciarAnuncio(
                        anuncio = anuncio,
                        onClick = { onAnuncioClick(anuncio.id) },
                        onAtivoChange = { viewModel.alternarAtivo(anuncio) },
                        onExcluirClick = { anuncioParaExcluir = anuncio }
                    )
                }
            }
        }
    }

    anuncioParaExcluir?.let { anuncio ->
        AlertDialog(
            onDismissRequest = { anuncioParaExcluir = null },
            title = { Text("Excluir anúncio") },
            text = { Text("Excluir \"${anuncio.nome}\"? Essa ação não pode ser desfeita.") },
            confirmButton = {
                TextButton(
                    onClick = {
                        viewModel.excluir(anuncio)
                        anuncioParaExcluir = null
                    }
                ) {
                    Text("Excluir")
                }
            },
            dismissButton = {
                TextButton(onClick = { anuncioParaExcluir = null }) {
                    Text("Cancelar")
                }
            }
        )
    }
}

@Composable
private fun CartaoGerenciarAnuncio(
    anuncio: Anuncio,
    onClick: () -> Unit,
    onAtivoChange: () -> Unit,
    onExcluirClick: () -> Unit
) {
    Card(
        onClick = onClick,
        modifier = Modifier.fillMaxWidth(),
        colors = CardDefaults.cardColors(containerColor = MaterialTheme.colorScheme.surface),
        elevation = CardDefaults.cardElevation(defaultElevation = 2.dp)
    ) {
        Row(
            modifier = Modifier.padding(16.dp),
            verticalAlignment = Alignment.CenterVertically
        ) {
            Column(modifier = Modifier.weight(1f)) {
                Text(
                    text = anuncio.nome,
                    style = MaterialTheme.typography.titleMedium,
                    color = MaterialTheme.colorScheme.onSurface
                )
                Text(
                    text = "${anuncio.categoria} · ${anuncio.areaAtendida}",
                    style = MaterialTheme.typography.bodyMedium,
                    color = MaterialTheme.colorScheme.onSurfaceVariant
                )
                Text(
                    text = if (anuncio.ativo) "Ativo" else "Inativo",
                    style = MaterialTheme.typography.labelMedium,
                    color = MaterialTheme.colorScheme.primary
                )
            }
            Switch(checked = anuncio.ativo, onCheckedChange = { onAtivoChange() })
            IconButton(onClick = onExcluirClick) {
                Icon(Icons.Filled.Delete, contentDescription = "Excluir ${anuncio.nome}")
            }
        }
    }
}
