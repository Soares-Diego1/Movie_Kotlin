

package org.example.projectt.ui.components


import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.sp
import org.example.projectt.ui.theme.AppRed
import org.example.projectt.ui.theme.AppWhite

@Composable
fun ScireFlixLogo(modifier: Modifier = Modifier) {
    Text(
        modifier = modifier,
        text = buildAnnotatedString {
            withStyle(
                style = SpanStyle(
                    color = AppWhite,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Scire")
            }
            withStyle(
                style = SpanStyle(
                    color = AppRed,
                    fontSize = 56.sp,
                    fontWeight = FontWeight.Bold
                )
            ) {
                append("Flix")
            }
        }
    )
}