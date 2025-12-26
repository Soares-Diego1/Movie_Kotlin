package org.example.projectt.ui.theme

import androidx.compose.material3.darkColorScheme
import androidx.compose.ui.graphics.Color


val Primary80 = Color(0xFFE50914)
val BackgroundDark = Color(0xFF121212)
val SurfaceDark = Color(0xFF1e1e1e)
val TextPrimaryDark = Color(0xFFFFFFFF)
val ColorErro = Color(0xFFF24E1E)
val Neutral60 = Color(0xFF8A91A8)

internal val AppColorScheme = darkColorScheme(

 primary = Primary80,
    onPrimary = Color.White,
 background =BackgroundDark ,
    onBackground = TextPrimaryDark,
surface= SurfaceDark ,
onSurface =TextPrimaryDark ,
    secondary = Neutral60,
        onSecondary = TextPrimaryDark,
      error =   ColorErro,
     onError = Color.White

)

val AppDarkBackground = Color(0xFF212121) // Fundo escuro
val AppRed = Color(0xFFE50914)           // Vermelho "Flix" (chutei o da Netflix)
val AppWhite = Color(0xFFF5F5F5)          // Branco "Scire"
val AppFieldGray = Color(0xFF4A4A4A)       // Fundo do campo de texto
val AppPlaceholderGray = Color(0xFF8E8E8E) // Texto do placeholder
val AppTextGray = Color(0xFFAAAAAA)        // Texto "precisa de ajudar?"
val AppLinkBlue = Color(0xFF00A8E1)