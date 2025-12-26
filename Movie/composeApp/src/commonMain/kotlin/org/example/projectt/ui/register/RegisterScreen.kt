package org.example.projectt.ui.register

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import dev.gitlive.firebase.Firebase
import dev.gitlive.firebase.auth.auth
import kotlinx.coroutines.launch
import org.example.projectt.ui.components.AuthButton
import org.example.projectt.ui.components.AuthTextField
import org.example.projectt.ui.components.ScireFlixLogo
import org.example.projectt.ui.theme.AppDarkBackground
import org.example.projectt.ui.theme.AppLinkBlue
import org.example.projectt.ui.theme.AppTextGray
import org.example.projectt.ui.theme.AppWhite
import androidx.compose.ui.graphics.Color

@Composable
fun RegisterScreen(

    onNavigateToLogin: () -> Unit,

    onRegisterSuccess: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val auth = remember { Firebase.auth }

    var email by remember { mutableStateOf("") }
    var senha by remember { mutableStateOf("") }
    var isLoading by remember { mutableStateOf(false) }
    var errorMessage by remember { mutableStateOf<String?>(null) }


    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(AppDarkBackground),
        contentAlignment = Alignment.Center
    ) {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth().padding(horizontal = 32.dp)
        ) {

            ScireFlixLogo(modifier = Modifier.padding(bottom = 40.dp))


            Text(
                text = "Para criar uma conta, precisamos dos seguintes dados:",
                color = AppWhite,
                modifier = Modifier.padding(bottom = 24.dp),
                textAlign = TextAlign.Center
            )


            AuthTextField(
                value = email,
                onValueChange = { email = it; errorMessage = null },
                placeholder = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))


            AuthTextField(
                value = senha,
                onValueChange = { senha = it; errorMessage = null },
                placeholder = "Senha",
                isPassword = true,
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Password)
            )


            if (errorMessage != null) {
                Spacer(modifier = Modifier.height(16.dp))
                Text(
                    text = errorMessage!!,
                    color = Color.Red,
                    textAlign = TextAlign.Center,
                    modifier = Modifier.fillMaxWidth()
                )
            }

            Spacer(modifier = Modifier.height(32.dp))


            AuthButton(
                text = if (isLoading) "Criando..." else "Criar a conta",
                onClick = {
                    if (email.isBlank() || senha.isBlank()) {
                        errorMessage = "Email e senha não podem estar em branco."
                        return@AuthButton
                    }

                    isLoading = true
                    errorMessage = null

                    scope.launch {
                        try {

                            auth.createUserWithEmailAndPassword(email.trim(), senha)

                            isLoading = false
                            onRegisterSuccess()

                        } catch (e: Exception) {

                            isLoading = false

                            errorMessage = when {
                                "EMAIL_ALREADY_IN_USE" in (e.message ?: "") -> "Este email já está em uso."
                                "WEAK_PASSWORD" in (e.message ?: "") -> "A senha é muito fraca. Tente uma senha mais forte."
                                else -> "Ocorreu um erro. Tente novamente."
                            }
                            println("Erro no registro: ${e.message}")
                        }
                    }
                },
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = buildAnnotatedString {
                    withStyle(style = SpanStyle(color = AppTextGray)) {
                        append("Já tenho uma conta. ")
                    }
                    withStyle(style = SpanStyle(color = AppLinkBlue)) {
                        append("Entrar")
                    }
                },
                modifier = Modifier.clickable(enabled = !isLoading) { onNavigateToLogin() }
            )
        }
    }
}