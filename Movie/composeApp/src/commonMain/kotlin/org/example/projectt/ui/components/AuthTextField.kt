// Em commonMain/kotlin/org/example/projectt/ui/components/AuthTextField.kt

package org.example.projectt.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardOptions
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.input.VisualTransformation
import androidx.compose.ui.unit.dp
import org.example.projectt.ui.theme.AppFieldGray
import org.example.projectt.ui.theme.AppPlaceholderGray
import org.example.projectt.ui.theme.AppWhite

@Composable
fun AuthTextField(
    value: String,
    onValueChange: (String) -> Unit,
    placeholder: String,
    isPassword: Boolean = false,
    keyboardOptions: KeyboardOptions = KeyboardOptions.Default,
    modifier: Modifier = Modifier
) {
    TextField(
        value = value,
        onValueChange = onValueChange,
        modifier = modifier
            .fillMaxWidth()
            .padding(horizontal = 32.dp)
            .clip(RoundedCornerShape(12.dp)),
        placeholder = { Text(placeholder, color = AppPlaceholderGray) },


        colors = TextFieldDefaults.colors(

            focusedContainerColor = AppFieldGray,
            unfocusedContainerColor = AppFieldGray,


            focusedTextColor = AppWhite,
            unfocusedTextColor = AppWhite,


            focusedPlaceholderColor = AppPlaceholderGray,
            unfocusedPlaceholderColor = AppPlaceholderGray,


            cursorColor = AppWhite,
            focusedIndicatorColor = Color.Transparent,
            unfocusedIndicatorColor = Color.Transparent
        ),

        singleLine = true,
        visualTransformation = if (isPassword) PasswordVisualTransformation() else VisualTransformation.None,
        keyboardOptions = keyboardOptions
    )
}