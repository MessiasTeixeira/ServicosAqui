package com.example.servicosaqui.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.compose.ui.tooling.preview.Preview

private val Verde = Color(0xFF173F35)
private val Dourado = Color(0xFFD5A85B)
private val Fundo = Color(0xFFF5F2EA)
private val Grafite = Color(0xFF343B36)

@Composable
fun CadastroScreen(onLoginClick: () -> Unit) {

    var nome by remember { mutableStateOf("") }
    var email by remember { mutableStateOf("") }
    var telefone by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var confirmarSenha by remember { mutableStateOf("") }

    var mostrarSenha by remember { mutableStateOf(false) }
    var mostrarConfirmacao by remember { mutableStateOf(false) }

    var tipoUsuario by remember { mutableStateOf<String?>(null) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 30.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(12.dp))

        // LOGO
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(44.dp),
                shape = RoundedCornerShape(13.dp),
                color = Verde
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "S",
                        color = Dourado,
                        fontSize = 26.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(10.dp))

            Text(
                text = "ServiçosAqui",
                fontSize = 23.sp,
                fontWeight = FontWeight.Bold,
                color = Verde
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // APRESENTAÇÃO
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Crie sua conta",
                fontSize = 28.sp,
                fontWeight = FontWeight.Bold,
                color = Grafite
            )

            Spacer(modifier = Modifier.height(8.dp))

            Text(
                text = "Faça parte do ServiçosAqui e encontre novas oportunidades.",
                fontSize = 14.sp,
                color = Color(0xFF777C76),
                lineHeight = 22.sp
            )
        }

        Spacer(modifier = Modifier.height(26.dp))

        Card(
            modifier = Modifier.fillMaxWidth(),
            shape = RoundedCornerShape(22.dp),
            colors = CardDefaults.cardColors(
                containerColor = Color.White
            ),
            elevation = CardDefaults.cardElevation(
                defaultElevation = 3.dp
            )
        ) {

            Column(
                modifier = Modifier.padding(22.dp)
            ) {

                Text(
                    text = "Informações pessoais",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Grafite
                )

                Spacer(modifier = Modifier.height(18.dp))

                // NOME
                CampoCadastro(
                    titulo = "Nome completo",
                    valor = nome,
                    onValueChange = { nome = it },
                    placeholder = "Digite seu nome"
                )

                Spacer(modifier = Modifier.height(16.dp))

                // EMAIL
                CampoCadastro(
                    titulo = "E-mail",
                    valor = email,
                    onValueChange = { email = it },
                    placeholder = "Digite seu e-mail",
                    keyboardType = KeyboardType.Email
                )

                Spacer(modifier = Modifier.height(16.dp))

                // TELEFONE
                CampoCadastro(
                    titulo = "Telefone",
                    valor = telefone,
                    onValueChange = { telefone = it },
                    placeholder = "(00) 00000-0000",
                    keyboardType = KeyboardType.Phone
                )

                Spacer(modifier = Modifier.height(16.dp))

                // SENHA
                CampoSenha(
                    titulo = "Senha",
                    valor = senha,
                    onValueChange = { senha = it },
                    mostrarSenha = mostrarSenha,
                    onMostrarSenha = {
                        mostrarSenha = !mostrarSenha
                    }
                )

                Spacer(modifier = Modifier.height(16.dp))

                // CONFIRMAR SENHA
                CampoSenha(
                    titulo = "Confirmar senha",
                    valor = confirmarSenha,
                    onValueChange = { confirmarSenha = it },
                    mostrarSenha = mostrarConfirmacao,
                    onMostrarSenha = {
                        mostrarConfirmacao = !mostrarConfirmacao
                    }
                )

                Spacer(modifier = Modifier.height(26.dp))

                HorizontalDivider(
                    color = Color(0xFFEEEEEA)
                )

                Spacer(modifier = Modifier.height(22.dp))

                // ESCOLHA DO PERFIL
                Text(
                    text = "Como deseja utilizar o aplicativo?",
                    fontSize = 17.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Grafite
                )

                Spacer(modifier = Modifier.height(6.dp))

                Text(
                    text = "Selecione o perfil da sua conta.",
                    fontSize = 13.sp,
                    color = Color(0xFF777C76)
                )

                Spacer(modifier = Modifier.height(16.dp))

                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.spacedBy(12.dp)
                ) {

                    // CLIENTE
                    CartaoPerfil(
                        titulo = "Cliente",
                        descricao = "Quero contratar serviços",
                        identificador = "CLIENTE",
                        selecionado = tipoUsuario == "CLIENTE",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            tipoUsuario = "CLIENTE"
                        }
                    )

                    // PRESTADOR
                    CartaoPerfil(
                        titulo = "Prestador",
                        descricao = "Quero oferecer serviços",
                        identificador = "PRESTADOR",
                        selecionado = tipoUsuario == "PRESTADOR",
                        modifier = Modifier.weight(1f),
                        onClick = {
                            tipoUsuario = "PRESTADOR"
                        }
                    )
                }

                Spacer(modifier = Modifier.height(26.dp))

                // BOTÃO CADASTRAR
                Button(
                    onClick = {},
                    modifier = Modifier
                        .fillMaxWidth()
                        .height(54.dp),
                    shape = RoundedCornerShape(12.dp),
                    colors = ButtonDefaults.buttonColors(
                        containerColor = Verde
                    )
                ) {
                    Text(
                        text = "Criar minha conta",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(18.dp))

                // VOLTAR PARA LOGIN
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Já possui uma conta?",
                        fontSize = 13.sp,
                        color = Color(0xFF777C76)
                    )

                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            text = "Entrar",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Verde
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(24.dp))

        Text(
            text = "SERVIÇOSAQUI",
            fontSize = 11.sp,
            letterSpacing = 2.sp,
            fontWeight = FontWeight.Bold,
            color = Verde
        )

        Spacer(modifier = Modifier.height(12.dp))
    }
}

@Composable
fun CampoCadastro(
    titulo: String,
    valor: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    keyboardType: KeyboardType = KeyboardType.Text
) {
    Column {
        Text(
            text = titulo,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Grafite
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = valor,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text(placeholder)
            },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            keyboardOptions = KeyboardOptions(
                keyboardType = keyboardType
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Verde,
                unfocusedBorderColor = Color(0xFFE1E1DB),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
    }
}

@Composable
fun CampoSenha(
    titulo: String,
    valor: String,
    onValueChange: (String) -> Unit,
    mostrarSenha: Boolean,
    onMostrarSenha: () -> Unit
) {
    Column {
        Text(
            text = titulo,
            fontSize = 14.sp,
            fontWeight = FontWeight.Medium,
            color = Grafite
        )

        Spacer(modifier = Modifier.height(8.dp))

        OutlinedTextField(
            value = valor,
            onValueChange = onValueChange,
            modifier = Modifier.fillMaxWidth(),
            placeholder = {
                Text("Digite sua senha")
            },
            shape = RoundedCornerShape(12.dp),
            singleLine = true,
            visualTransformation =
                if (mostrarSenha)
                    VisualTransformation.None
                else
                    PasswordVisualTransformation(),
            trailingIcon = {
                TextButton(onClick = onMostrarSenha) {
                    Text(
                        text = if (mostrarSenha) "Ocultar" else "Mostrar",
                        color = Verde,
                        fontSize = 12.sp
                    )
                }
            },
            keyboardOptions = KeyboardOptions(
                keyboardType = KeyboardType.Password
            ),
            colors = OutlinedTextFieldDefaults.colors(
                focusedBorderColor = Verde,
                unfocusedBorderColor = Color(0xFFE1E1DB),
                focusedContainerColor = Color.White,
                unfocusedContainerColor = Color.White
            )
        )
    }
}

@Composable
fun CartaoPerfil(
    titulo: String,
    descricao: String,
    identificador: String,
    selecionado: Boolean,
    modifier: Modifier = Modifier,
    onClick: () -> Unit
) {
    Column(
        modifier = modifier
            .border(
                width = if (selecionado) 2.dp else 1.dp,
                color = if (selecionado) Verde else Color(0xFFE1E1DB),
                shape = RoundedCornerShape(14.dp)
            )
            .background(
                color = if (selecionado)
                    Color(0xFFF0F5F1)
                else
                    Color.White,
                shape = RoundedCornerShape(14.dp)
            )
            .clickable { onClick() }
            .padding(14.dp),
        horizontalAlignment = Alignment.Start
    ) {

        Row(
            modifier = Modifier.fillMaxWidth(),
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically
        ) {
            Text(
                text = if (identificador == "CLIENTE") "C" else "P",
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                color = Verde
            )

            RadioButton(
                selected = selecionado,
                onClick = onClick,
                colors = RadioButtonDefaults.colors(
                    selectedColor = Verde
                )
            )
        }

        Spacer(modifier = Modifier.height(8.dp))

        Text(
            text = titulo,
            fontSize = 15.sp,
            fontWeight = FontWeight.Bold,
            color = Grafite
        )

        Spacer(modifier = Modifier.height(4.dp))

        Text(
            text = descricao,
            fontSize = 11.sp,
            lineHeight = 16.sp,
            color = Color(0xFF777C76)
        )
    }
}

