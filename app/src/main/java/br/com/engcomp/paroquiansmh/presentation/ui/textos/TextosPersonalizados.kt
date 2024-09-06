package br.com.engcomp.paroquiansmh.presentation.ui.textos

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import br.com.engcomp.paroquiansmh.ui.theme.AppTypography


@Composable
fun TextosPersonalizados(){

}

@Composable
fun TextoCorpo(texto: String){
    Text(text = texto,
        fontSize = AppTypography.bodyLarge.fontSize,
        fontWeight = AppTypography.bodyLarge.fontWeight,
        fontFamily = AppTypography.bodyLarge.fontFamily,
        fontStyle = AppTypography.bodyLarge.fontStyle,
        modifier = Modifier.padding(5.dp).fillMaxWidth(),
        textAlign = TextAlign.Center)
}

@Composable
fun TextoTitulo(texto: String){
    Text(
        text = texto,
        fontSize = AppTypography.headlineMedium.fontSize,
        fontWeight = AppTypography.headlineMedium.fontWeight,
        fontFamily = AppTypography.headlineMedium.fontFamily,
        fontStyle = AppTypography.headlineMedium.fontStyle,
        color = AppTypography.headlineMedium.color,
        modifier = Modifier.padding(5.dp).fillMaxWidth(),
        textAlign = TextAlign.Center)
}