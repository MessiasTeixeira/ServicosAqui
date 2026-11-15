package com.example.servicosaqui.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.ExperimentalLayoutApi
import androidx.compose.foundation.layout.FlowRow
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.automirrored.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.CircularProgressIndicator
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FilterChip
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.servicosaqui.model.CampoAnuncio
import com.example.servicosaqui.model.Categoria
import com.example.servicosaqui.viewmodels.AnuncioFormViewModel

@OptIn(ExperimentalMaterial3Api::class, ExperimentalLayoutApi::class)
@Composable
fun AnuncioFormScreen(
    id: String?,
    onVoltar: () -> Unit,
    viewModel: AnuncioFormViewModel = viewModel()
) {
    val estado by viewModel.uiState.collectAsState()

    LaunchedEffect(id) {
        if (id != null) viewModel.carregar(id)
    }

    LaunchedEffect(estado.salvo) {
        if (estado.salvo) onVoltar()
    }

    Scaffold(
        contentWindowInsets = WindowInsets(0, 0, 0, 0),
        topBar = {
            TopAppBar(
                title = { Text(if (id == null) "Novo anúncio" else "Editar anúncio") },
                navigationIcon = {
                    IconButton(onClick = onVoltar) {
                        Icon(Icons.AutoMirrored.Filled.ArrowBack, contentDescription = "Voltar")
                    }
                }
            )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier
                .fillMaxSize()
                .padding(innerPadding)
                .verticalScroll(rememberScrollState())
                .padding(20.dp),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            if (estado.carregando) {
                CircularProgressIndicator()
            }

            OutlinedTextField(
                value = estado.nome,
                onValueChange = viewModel::alterarNome,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Nome") },
                singleLine = true,
                isError = CampoAnuncio.NOME in estado.camposInvalidos,
                supportingText = if (CampoAnuncio.NOME in estado.camposInvalidos) {
                    { Text("Informe o nome") }
                } else null
            )

            Column {
                Text("Categoria", style = MaterialTheme.typography.labelLarge)
                FlowRow(horizontalArrangement = Arrangement.spacedBy(8.dp)) {
                    Categoria.todas.forEach { categoria ->
                        FilterChip(
                            selected = estado.categoria == categoria,
                            onClick = { viewModel.alterarCategoria(categoria) },
                            label = { Text(categoria) }
                        )
                    }
                }
                if (CampoAnuncio.CATEGORIA in estado.camposInvalidos) {
                    Text(
                        text = "Escolha uma categoria",
                        style = MaterialTheme.typography.bodySmall,
                        color = MaterialTheme.colorScheme.error
                    )
                }
            }

            OutlinedTextField(
                value = estado.descricao,
                onValueChange = viewModel::alterarDescricao,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Descrição") },
                minLines = 3,
                isError = CampoAnuncio.DESCRICAO in estado.camposInvalidos,
                supportingText = if (CampoAnuncio.DESCRICAO in estado.camposInvalidos) {
                    { Text("Informe a descrição") }
                } else null
            )

            OutlinedTextField(
                value = estado.areaAtendida,
                onValueChange = viewModel::alterarAreaAtendida,
                modifier = Modifier.fillMaxWidth(),
                label = { Text("Área atendida") },
                isError = CampoAnuncio.AREA_ATENDIDA in estado.camposInvalidos,
                supportingText = if (CampoAnuncio.AREA_ATENDIDA in estado.camposInvalidos) {
                    { Text("Informe a área atendida") }
                } else null
            )

            Row(verticalAlignment = Alignment.CenterVertically) {
                Text("Anúncio ativo", modifier = Modifier.weight(1f))
                Switch(checked = estado.ativo, onCheckedChange = viewModel::alterarAtivo)
            }

            if (estado.erro != null) {
                Text(text = estado.erro.orEmpty(), color = MaterialTheme.colorScheme.error)
            }

            Button(
                onClick = viewModel::salvar,
                enabled = !estado.salvando,
                modifier = Modifier.fillMaxWidth()
            ) {
                Text(if (estado.salvando) "Salvando..." else "Salvar")
            }
        }
    }
}
