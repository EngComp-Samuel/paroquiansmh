package br.com.engcomp.paroquiansmh.presentation.ui.componentes
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.ui.theme.AppTypography


@Composable
fun PadraoCardItem(icone: Int, descricao: String, textoBotao: String, navController: NavController, rota: String){
    Card(
        elevation = CardDefaults.elevatedCardElevation(
        defaultElevation = 5.dp,
        pressedElevation = 0.dp
    ),
        modifier = Modifier
            .padding(5.dp)
            .clickable {
                navController.navigate(rota) {
                    popUpTo(rota) {
                        inclusive = true
                    }
                }
            }){
        Image(
            painter = painterResource(id = icone),
            contentDescription = descricao,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier
                .fillMaxWidth())
        Text(
            text = textoBotao,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            textAlign = TextAlign.Center,
            style = MaterialTheme.typography.labelSmall
        )
    }
}