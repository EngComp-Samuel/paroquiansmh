package br.com.engcomp.paroquiansmh.presentation.ui.screen.biblia

import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.presentation.navigation.NavigationHost
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.CardBiblia
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.PadraoCardItem
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LivroScreen(navController: NavController, homeViewModel: HomeViewModel, tituloLivro: String = "teste"){

    val livroCorintios = homeViewModel.umCorintios.collectAsState().value

    val listState = rememberLazyListState()


    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = tituloLivro)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.navigate(NavigationHost.Biblia.route) {
                                popUpTo(NavigationHost.Biblia.route) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Icon(painter = painterResource(id = R.drawable.icon_arrow_back), contentDescription = "icone de voltar")
                    }
                }
            )
        },
        content = {
                innerPadding->
            LazyColumn(state = listState,modifier = Modifier.fillMaxSize(), contentPadding = innerPadding){
                itemsIndexed(items = livroCorintios){ index, it->

                    Card {
                        CardBiblia(
                            tituloLivro = (it.nome) + "-" + (index+1),
                            mensagens = it.capitulo,
                            numero = 12.toString()
                        )
                    }
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(10.dp))

                }
                item{
                    Text(text = "TESTE")
                }
            }
        }
    )
}