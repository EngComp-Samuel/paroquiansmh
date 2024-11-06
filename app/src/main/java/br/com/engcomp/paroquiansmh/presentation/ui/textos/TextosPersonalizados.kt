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



@Composable
fun TextosPersonalizados(homeViewModel: HomeViewModel){

    Column {
        Text(
            text = homeViewModel.nomeLivroInicialAleatorio.collectAsState().value,
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            fontWeight = FontWeight.Bold)
        Text(
            text = homeViewModel.mensagemInicialAleatoria.collectAsState().value.removePrefix("[").removeSuffix("]"),
            modifier = Modifier
                .padding(10.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            maxLines = 4,
            overflow = TextOverflow.Ellipsis)
    }


}
