package br.com.engcomp.paroquiansmh.presentation.ui.textos

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import br.com.engcomp.paroquiansmh.data.local.HomeMensagemInicial
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel
import br.com.engcomp.paroquiansmh.ui.theme.AppTypography
import br.com.engcomp.paroquiansmh.ui.theme.playFairDisplayFontFamily


@Composable
fun TextosPersonalizados(homeViewModel: HomeViewModel){

    Column {
        TextoCorpo(
            texto = homeViewModel.nomeLivroInicialAleatorio.collectAsState().value,
            pesoFonte = FontWeight.Bold)
        TextoCorpo(
            texto = homeViewModel.mensagemInicialAleatoria.collectAsState().value.removePrefix("[").removeSuffix("]"))
    }


}

@Composable
fun TextoCorpo(texto: String, pesoFonte: FontWeight = FontWeight.Medium){
    Text(text = texto,
        fontSize = AppTypography.bodyMedium.fontSize,
        fontWeight = pesoFonte,
        fontFamily = playFairDisplayFontFamily,
        fontStyle = AppTypography.bodyMedium.fontStyle,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center,
        maxLines = 3,
        overflow = TextOverflow.Ellipsis)
}

@Composable
fun TextoTitulo(texto: String, pesoFonte: FontWeight = FontWeight.Bold){
    Text(
        text = texto,
        fontSize = AppTypography.headlineMedium.fontSize,
        fontWeight = pesoFonte,
        fontFamily = playFairDisplayFontFamily,
        fontStyle = AppTypography.headlineMedium.fontStyle,
        color = AppTypography.headlineMedium.color,
        modifier = Modifier
            .padding(5.dp)
            .fillMaxWidth(),
        textAlign = TextAlign.Center)
}