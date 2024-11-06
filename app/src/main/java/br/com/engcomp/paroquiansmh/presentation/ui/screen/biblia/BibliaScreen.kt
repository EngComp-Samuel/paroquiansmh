package br.com.engcomp.paroquiansmh.presentation.ui.screen.biblia

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Tab
import androidx.compose.material3.TabRow
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.data.local.BibliaDTO
import br.com.engcomp.paroquiansmh.presentation.navigation.NavigationHost
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel
import br.com.engcomp.paroquiansmh.ui.theme.AppTypography
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun BibliaScreen(navController: NavController, homeViewModel: HomeViewModel){

    val antigoTestamento = homeViewModel.bibliaCompletaAntigoTestatmento()
    val novoTestamento = homeViewModel.bibliaCompletaNovoTestatmento()


    var tabIndex by remember { mutableStateOf(0) }
    val tabs = listOf("Antigo Testamento", "Novo Testamento")

    val sheetState = rememberModalBottomSheetState()
    val sheetStateFilter = rememberModalBottomSheetState()
    val scope = rememberCoroutineScope()
    var showBottomSheet by remember { mutableStateOf(false) }
    var showBottomSheetFilter by remember { mutableStateOf(false) }

    Scaffold(
        snackbarHost = {
            //essa mesma logica pode ser utilizada para realizar os filtros
            if(showBottomSheet){
                    ModalBottomSheet(
                        onDismissRequest = { showBottomSheet = false },
                        sheetState = sheetState,
                        modifier = Modifier.fillMaxWidth()
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp).fillMaxWidth()){
                            Icon(painter = painterResource(id = R.drawable.icon_info), contentDescription = "icon_info")
                            Text(
                                modifier = Modifier
                                    .padding(10.dp),
                                text = "Selecione um dos livros da Bíblia Sagrada para realizar sua leitura",
                                fontSize = AppTypography.labelSmall.fontSize)
                        }
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            modifier = Modifier.padding(10.dp).fillMaxWidth()){
                            Icon(painter = painterResource(id = R.drawable.icon_info), contentDescription = "icon_info")
                            Text(
                                modifier = Modifier
                                    .padding(10.dp),
                                text = "Após selecionar o livro, você pode clicar e pressionar um versículo para salvar ou compartilhar")
                        }

                        HorizontalDivider()
                        TextButton(
                            onClick = {
                                scope.launch {
                                    sheetState.hide()
                                }.invokeOnCompletion {
                                    if (!sheetState.isVisible) {
                                        showBottomSheet = false
                                    }
                                }
                            },
                            modifier = Modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Fechar")
                        }
                    }
                }
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
            Column(
                Modifier
                    .fillMaxSize()
                    .padding(innerPadding)){

                Column(modifier = Modifier.fillMaxWidth()){
                    TabRow(selectedTabIndex = tabIndex) {
                        tabs.forEachIndexed { index, s ->
                            Tab(
                                text = { Text(text = s)},
                                selected = tabIndex == index,
                                onClick = { tabIndex = index }
                            )
                        }
                    }
                    when(tabIndex){
                        0 -> ListaAntigoTestamento(antigoTestamento, navController)
                        1 -> ListaNovoTestamento(novoTestamento, navController, homeViewModel = homeViewModel)
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
        Image(
            painter = painterResource(id = icone),
            contentDescription = descricao,
            contentScale = ContentScale.FillWidth,
            modifier = Modifier.fillMaxWidth()
                //.padding(5.dp)
                //.align(alignment = Alignment.CenterHorizontally)
        )
        Text(
            text = textoBotao,
            modifier = Modifier
                .padding(5.dp)
                .fillMaxWidth(),
            fontSize = AppTypography.labelSmall.fontSize,
            textAlign = TextAlign.Center,
            maxLines = 1,
            overflow = TextOverflow.Ellipsis
        )
    }

}


@Composable
fun ListaAntigoTestamento(antigoTestamento: HashMap<String, StateFlow<List<BibliaDTO>>>, navController: NavController){
    val orientacaoTela = LocalConfiguration.current.orientation
    LazyVerticalGrid(
        columns = if(orientacaoTela == 1 )GridCells.Fixed(3)else GridCells.Fixed(5),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .height(800.dp)
            .padding(10.dp)
    ){
        items(antigoTestamento.size){
                index ->
            PadraoCardItemLivro(
                icone = if(index % 2 == 0) R.drawable.img_hebreus else R.drawable.img_genesis,
                descricao = "",
                textoBotao = antigoTestamento.keys.elementAt(index),
                navController = navController,
                rota = "livro-screen/${antigoTestamento.keys.elementAt(index)}/${0}")
        }
    }
}

@Composable
fun ListaNovoTestamento(novoTestamento: HashMap<String, StateFlow<List<BibliaDTO>>>, navController: NavController, homeViewModel: HomeViewModel){
    val orientacaoTela = LocalConfiguration.current.orientation
    val imagens = homeViewModel.vetorImagensLivros()
    LazyVerticalGrid(
        columns = if(orientacaoTela == 1 )GridCells.Fixed(3)else GridCells.Fixed(5),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .height(800.dp)
            .padding(10.dp)
    ){
        items(novoTestamento.size){
                index ->
            PadraoCardItemLivro(
                icone = imagens[index],
                descricao = "",
                textoBotao = novoTestamento.keys.elementAt(index),
                navController = navController,
                rota = "livro-screen/${novoTestamento.keys.elementAt(index)}/${1}")
        }
    }
}






