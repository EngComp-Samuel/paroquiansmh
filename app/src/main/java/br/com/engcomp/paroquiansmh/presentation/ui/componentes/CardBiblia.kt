package br.com.engcomp.paroquiansmh.presentation.ui.componentes

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.data.local.BibliaDTO
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel


@Composable
fun CardBiblia(
    bibliaDTO: BibliaDTO,
    index: Int,
    homeViewModel: HomeViewModel,
    navController: NavController,
    rota: String,
    testamento: Int
){
    Column {
        Card(modifier = Modifier
            .fillMaxWidth()
            .padding(5.dp)
            .clickable {
                homeViewModel.pesquisaCapituloPraEncontrarOsVersiculos(bibliaDTO.nome, (index-1), testamento)
                navController.navigate(rota+"/${bibliaDTO.nome +" - "+index}/${index-1}/${testamento}"){
                    popUpTo(rota+"/${bibliaDTO.nome +" - "+index}/${index-1}/${testamento}"){
                        inclusive = true
                    }
                }
            }
        ){
            Row(modifier = Modifier
                .fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween){
                Text(text = bibliaDTO.nome +" - "+index)
                IconButton(
                    onClick = {
                        homeViewModel.pesquisaCapituloPraEncontrarOsVersiculos(bibliaDTO.nome, (index-1), testamento)
                        navController.navigate(rota+"/${bibliaDTO.nome +" - "+index}/${index-1}/${testamento}"){
                            popUpTo(rota+"/${bibliaDTO.nome +" - "+index}/${index-1}/${testamento}"){
                                inclusive = true
                            }
                        }
                    }
                ) {
                    Icon(
                        painter = painterResource(id = R.drawable.icon_arrow_forward),
                        contentDescription = "icon right"
                    )
                }
            }
        }
    }
}






