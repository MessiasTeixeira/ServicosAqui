package com.example.servicosaqui.ui.screens

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.foundation.text.KeyboardOptions
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
fun LoginScreen(onCadastrarClick: () -> Unit) {

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var mostrarSenha by remember { mutableStateOf(false) }

    Column(
        modifier = Modifier
            .fillMaxSize()
            .background(Fundo)
            .verticalScroll(rememberScrollState())
            .padding(horizontal = 28.dp, vertical = 36.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Spacer(modifier = Modifier.height(24.dp))

        // LOGO
        Row(
            verticalAlignment = Alignment.CenterVertically
        ) {
            Surface(
                modifier = Modifier.size(48.dp),
                shape = RoundedCornerShape(14.dp),
                color = Verde
            ) {
                Box(contentAlignment = Alignment.Center) {
                    Text(
                        text = "S",
                        color = Dourado,
                        fontSize = 28.sp,
                        fontWeight = FontWeight.Bold
                    )
                }
            }

            Spacer(modifier = Modifier.width(12.dp))

            Text(
                text = "ServiçosAqui",
                fontSize = 25.sp,
                fontWeight = FontWeight.Bold,
                color = Verde
            )
        }

        Spacer(modifier = Modifier.height(48.dp))

        // APRESENTAÇÃO
        Column(
            modifier = Modifier.fillMaxWidth()
        ) {
            Text(
                text = "Bem-vindo!",
                fontSize = 29.sp,
                fontWeight = FontWeight.Bold,
                color = Grafite
            )

            Spacer(modifier = Modifier.height(10.dp))

            Text(
                text = "Encontre profissionais de confiança e resolva o que precisa.",
                fontSize = 15.sp,
                color = Color(0xFF777C76),
                lineHeight = 23.sp
            )
        }

        Spacer(modifier = Modifier.height(32.dp))

        // FORMULÁRIO
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
                    text = "Entrar na sua conta",
                    fontSize = 19.sp,
                    fontWeight = FontWeight.SemiBold,
                    color = Grafite
                )

                Spacer(modifier = Modifier.height(22.dp))

                // EMAIL
                Text(
                    text = "E-mail",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Grafite
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = email,
                    onValueChange = { email = it },
                    modifier = Modifier.fillMaxWidth(),
                    placeholder = {
                        Text("Digite seu e-mail")
                    },
                    shape = RoundedCornerShape(12.dp),
                    singleLine = true,
                    keyboardOptions = KeyboardOptions(
                        keyboardType = KeyboardType.Email
                    ),
                    colors = OutlinedTextFieldDefaults.colors(
                        focusedBorderColor = Verde,
                        unfocusedBorderColor = Color(0xFFE1E1DB),
                        focusedContainerColor = Color.White,
                        unfocusedContainerColor = Color.White
                    )
                )

                Spacer(modifier = Modifier.height(18.dp))

                // SENHA
                Text(
                    text = "Senha",
                    fontSize = 14.sp,
                    fontWeight = FontWeight.Medium,
                    color = Grafite
                )

                Spacer(modifier = Modifier.height(8.dp))

                OutlinedTextField(
                    value = senha,
                    onValueChange = { senha = it },
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
                        TextButton(
                            onClick = {
                                mostrarSenha = !mostrarSenha
                            }
                        ) {
                            Text(
                                text = if (mostrarSenha)
                                    "Ocultar"
                                else
                                    "Mostrar",
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

                // RECUPERAR SENHA
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.End
                ) {
                    TextButton(
                        onClick = {}
                    ) {
                        Text(
                            text = "Esqueceu a senha?",
                            color = Verde,
                            fontSize = 13.sp
                        )
                    }
                }

                Spacer(modifier = Modifier.height(12.dp))

                // BOTÃO ENTRAR
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
                        text = "Entrar",
                        fontSize = 16.sp,
                        fontWeight = FontWeight.SemiBold,
                        color = Color.White
                    )
                }

                Spacer(modifier = Modifier.height(22.dp))

                // CADASTRO
                Row(
                    modifier = Modifier.fillMaxWidth(),
                    horizontalArrangement = Arrangement.Center,
                    verticalAlignment = Alignment.CenterVertically
                ) {
                    Text(
                        text = "Ainda não tem uma conta?",
                        fontSize = 13.sp,
                        color = Color(0xFF777C76)
                    )

                    TextButton(
                        onClick = onCadastrarClick
                    ) {
                        Text(
                            text = "Cadastre-se",
                            fontSize = 13.sp,
                            fontWeight = FontWeight.Bold,
                            color = Verde
                        )
                    }
                }
            }
        }

        Spacer(modifier = Modifier.height(32.dp))

        // RODAPÉ
        Text(
            text = "Conectando pessoas a soluções.",
            fontSize = 13.sp,
            color = Color(0xFF858980)
        )

        Spacer(modifier = Modifier.height(12.dp))

        Text(
            text = "SERVIÇOSAQUI",
            fontSize = 11.sp,
            letterSpacing = 2.sp,
            fontWeight = FontWeight.Bold,
            color = Verde
        )
    }
}

