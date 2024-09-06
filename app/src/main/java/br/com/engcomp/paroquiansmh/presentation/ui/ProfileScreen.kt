package br.com.engcomp.paroquiansmh.presentation.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.PadraoBotao
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.PadraoCardItem


@Composable
fun ProfileScreen(navController: NavController){

    /*val listaDeIcones = listOf(
        PadraoBotao(
            id = 0,
            texto = "teste",
            icone = R.drawable.baseline_notifications_24,
            descricao = "teste"
        ),
        PadraoBotao(
            id = 1,
            texto = "teste",
            icone = R.drawable.baseline_notifications_24,
            descricao = "teste"
        ),
        PadraoBotao(
            id = 2,
            texto = "teste",
            icone = R.drawable.baseline_notifications_24,
            descricao = "teste"
        ),
        PadraoBotao(
            id = 3,
            texto = "teste",
            icone = R.drawable.baseline_notifications_24,
            descricao = "teste"
        ),
        PadraoBotao(
            id = 4,
            texto = "teste",
            icone = R.drawable.baseline_notifications_24,
            descricao = "teste"
        ),
        PadraoBotao(
            id = 5,
            texto = "teste",
            icone = R.drawable.baseline_notifications_24,
            descricao = "teste"
        ),
        PadraoBotao(
            id = 6,
            texto = "teste",
            icone = R.drawable.baseline_notifications_24,
            descricao = "teste"
        )
    )
*/

    Column {
        Row {
            Text(text = "Teste")
        }

        Row {
            Text(text = "Teste")
        }

        LazyVerticalGrid(
            columns = GridCells.Fixed(3),
            contentPadding = PaddingValues(8.dp)
        ){

            item {
                Text(text = "Teste")
            }

            /*items(listaDeIcones.size){
                    index ->
                PadraoCardItem(
                    icone = listaDeIcones[index].icone,
                    descricao = listaDeIcones[index].descricao,
                    textoBotao = listaDeIcones[index].texto,
                    navController = navController,
                    rota = "teste"
                )
            }*/
        }
    }



}