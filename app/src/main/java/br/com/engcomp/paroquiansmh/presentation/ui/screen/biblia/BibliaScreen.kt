package br.com.engcomp.paroquiansmh.presentation.ui.screen.biblia

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.animation.core.LinearOutSlowInEasing
import androidx.compose.animation.core.animateFloatAsState
import androidx.compose.animation.core.tween
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.SnackbarDuration
import androidx.compose.material3.SnackbarHost
import androidx.compose.material3.SnackbarHostState
import androidx.compose.material3.SnackbarResult
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.derivedStateOf
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight

import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.presentation.navigation.NavigationHost
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.CardBiblia
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.PadraoCardItem
import br.com.engcomp.paroquiansmh.presentation.ui.textos.TextoCorpo
import br.com.engcomp.paroquiansmh.presentation.ui.textos.TextoTitulo
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel
import br.com.engcomp.paroquiansmh.ui.theme.AppTypography
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibliaScreen(navController: NavController, homeViewModel: HomeViewModel){

    var isExpanded by remember { mutableStateOf(false) }
    val rotateState by animateFloatAsState(targetValue = if (isExpanded) 180f else 0f)

    val padding: Dp = 30.dp

    val biblia = homeViewModel.biblia.collectAsState().value
    val livroCorintios = homeViewModel.umCorintios.collectAsState().value
    val cronicas = homeViewModel.umCronicas.collectAsState().value

    val contentPadding: PaddingValues = WindowInsets(
        left = 0.dp,
        top = 10.dp,
        right = 0.dp,
        bottom = padding,
    ).add(WindowInsets.navigationBars).asPaddingValues()
    val listState= rememberLazyListState()

    val sheetState = rememberModalBottomSheetState()
    val sheetStateFilter = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showBottomSheetFilter by remember { mutableStateOf(false) }

    var checked by remember { mutableStateOf(true) }

    val textoTopBar = "teste"

    Scaffold(
        snackbarHost = {
            //essa mesma logica pode ser utilizada para realizar os filtros
            if(showBottomSheet){
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                        sheetState = sheetState,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Text(text = "Selecione um dos livros da Bíblia Sagrada para realizar sua leitura")
                        Button(
                            onClick = {
                                scope.launch {
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                            }
                        ) {
                            Text(text = "Fechar")
                        }
                    }
                }
            /*if(showBottomSheetFilter){
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheetFilter = false },
                    sheetState = sheetStateFilter,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    TextoCorpo(texto = "Filtros", pesoFonte = FontWeight.Bold)
                    Button(
                        onClick = {
                                scope.launch {
                                    sheetStateFilter.hide()
                                }.invokeOnCompletion {
                                    if (!sheetStateFilter.isVisible) {
                                        showBottomSheetFilter = false
                                    }
                                }
                            }
                        ) {
                            Text(text = "Fechar")
                        }
                    Row(modifier = Modifier.fillMaxWidth(), horizontalArrangement = Arrangement.spacedBy(5.dp)){
                        Text(text = "Filtrar por ordem alfabética")
                        Switch(
                            checked = checked,
                            onCheckedChange = {checked = it},
                            thumbContent = if (checked){
                                {
                                    Icon(
                                        painter = painterResource(id = R.drawable.icon_check),
                                        contentDescription = "check")
                                }
                            }else{
                                null
                            })
                    }
                    Row {
                        Text(text = "Filtrar por quantidade de capitulos")
                        Switch(
                            checked = checked,
                            onCheckedChange = {checked = it},
                            thumbContent = if (checked){
                                {
                                    Icon(painter = painterResource(id = R.drawable.icon_check), contentDescription = "check")
                                }
                            }else{
                                null
                            })
                    }

                }
            }*/
        },
        topBar = {
            TopAppBar(
                title = { 
                    Text(text = "Bíblia Sagrada")
                },
                navigationIcon = {

                    IconButton(
                        onClick = {
                            navController.navigate(NavigationHost.Home.route) {
                                popUpTo(NavigationHost.Home.route) {
                                    inclusive = true
                                }
                            }
                        }
                    ) {
                        Icon(painter = painterResource(id = R.drawable.icon_arrow_back), contentDescription = "icone de voltar")
                    }

                },
                actions = {
                    IconButton(onClick = {
                        //homeViewModel.OrdenarLivrosBibliaPorQuantidadeDeCapitulos()
                    }){
                        Icon(painter = painterResource(id = R.drawable.icon_search), contentDescription = "icone de busca")
                    }
                    IconButton(onClick = {
                        //homeViewModel.OrdenarLivrosBibliaPorNome()
                        showBottomSheetFilter = true
                    }){
                        Icon(painter = painterResource(id = R.drawable.icon_filter_list), contentDescription = "icone de filtro")
                    }
                    IconButton(onClick = {
                        showBottomSheet = true
                    }){
                        Icon(painter = painterResource(id = R.drawable.icon_menu_vert), contentDescription = "icone de menu")
                    }
                })
        },
        content = {
                innerPadding->
            LazyColumn(state = listState,modifier = Modifier.fillMaxSize(), contentPadding = innerPadding){
                item {
                    Text(text = "Antigo testamento")
                }
//                items(biblia.size){
//                        index->
//                    CardBiblia(
//                        tituloLivro = "${(biblia.get(index).chapters.size)} - ${biblia.get(index).name}",
//                        mensagem = biblia.get(index).chapters.toString(),
//                        numero = 12.toString()
//                    )
//                        HorizontalDivider()
//                        Spacer(modifier = Modifier.height(10.dp))
//
//                }

                /*itemsIndexed(items = livroCorintios){ index, it->

                    Card {
                        CardBiblia(
                            tituloLivro = (it.nome) + "-" + (index+1),
                            mensagens = it.capitulo,
                            numero = 12.toString()
                        )
                    }
                    HorizontalDivider()
                    Spacer(modifier = Modifier.height(10.dp))

                }*/

                item{
                    LazyVerticalGrid(
                        columns = GridCells.Fixed(3),
                        contentPadding = PaddingValues(10.dp),
                        modifier = Modifier
                            .height(800.dp)
                            .padding(10.dp)

                    ){
                        items(livroCorintios.size){
                                index ->
                            PadraoCardItemLivro(
                                icone = R.drawable.baseline_notifications_24,
                                descricao = "",
                                textoBotao = livroCorintios.get(index).nome,
                                navController = navController,
                                rota = "livro-screen/${livroCorintios.get(index).nome}")
                        }
                    }
                }


                item {
                    Text(text = "Novo testamento")
                    Button(onClick = {
                        navController.navigate(NavigationHost.Livro.route+"/${textoTopBar}"){
                            popUpTo(NavigationHost.Biblia.route){
                                inclusive = true
                            }
                        }
                    }) {
                        Text(text = "Ja >")
                    }
                }
                }

            }
    )
}

@Composable
fun PadraoCardItemLivro(icone: Int, descricao: String, textoBotao: String, navController: NavController, rota: String){

    Card(elevation = CardDefaults.elevatedCardElevation(
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
        Icon(
            painter = painterResource(id = icone),
            contentDescription = descricao,
            modifier = Modifier
                .padding(5.dp)
                .align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = textoBotao,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            fontSize = AppTypography.labelSmall.fontSize,
            textAlign = TextAlign.Center
        )
    }

}






