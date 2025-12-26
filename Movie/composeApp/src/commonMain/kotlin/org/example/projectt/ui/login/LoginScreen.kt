// Em commonMain/kotlin/org/example/projectt/ui/login/LoginScreen.kt

package org.example.projectt.ui.login

import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.text.style.TextAlign
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

@Composable
fun LoginScreen(

    onLoginSuccess: () -> Unit,
    onNavigateToRegister: () -> Unit,
    onNeedHelpClick: () -> Unit
) {

    val scope = rememberCoroutineScope()
    val auth = remember { Firebase.auth }

    var email by remember { mutableStateOf("") }
    var password by remember { mutableStateOf("") }
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

            ScireFlixLogo(modifier = Modifier.padding(bottom = 60.dp))


            AuthTextField(
                value = email,
                onValueChange = { email = it; errorMessage = null },
                placeholder = "Email",
                keyboardOptions = KeyboardOptions(keyboardType = KeyboardType.Email)
            )

            Spacer(modifier = Modifier.height(16.dp))


            AuthTextField(
                value = password,
                onValueChange = { password = it; errorMessage = null }, // Limpa erro
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

                text = if (isLoading) "Acessando..." else "Acessar",
                onClick = {
                    if (email.isBlank() || password.isBlank()) {
                        errorMessage = "Email e senha são obrigatórios."
                        return@AuthButton
                    }

                    isLoading = true
                    errorMessage = null

                    scope.launch {
                        try {

                            auth.signInWithEmailAndPassword(email.trim(), password)

                            isLoading = false
                            onLoginSuccess()

                        } catch (e: Exception) {

                            isLoading = false

                            errorMessage = when {
                                "INVALID_CREDENTIAL" in (e.message ?: "") -> "Email ou senha inválidos."
                                "USER_NOT_FOUND" in (e.message ?: "") -> "Usuário não encontrado."
                                "INVALID_PASSWORD" in (e.message ?: "") -> "Email ou senha inválidos."
                                else -> "Erro ao tentar fazer login."
                            }
                            println("Erro no login: ${e.message}")
                        }
                    }
                },
                enabled = !isLoading
            )

            Spacer(modifier = Modifier.height(24.dp))


            Text(
                text = "precisa de ajudar?",
                color = AppTextGray,
                modifier = Modifier.clickable(enabled = !isLoading) { onNeedHelpClick() } // NOVO: enabled
            )

            Spacer(modifier = Modifier.height(16.dp))


            Text(
                text = "Criar uma conta",
                color = AppLinkBlue,
                modifier = Modifier.clickable(enabled = !isLoading) { onNavigateToRegister() } // NOVO: enabled
            )
        }
    }
}