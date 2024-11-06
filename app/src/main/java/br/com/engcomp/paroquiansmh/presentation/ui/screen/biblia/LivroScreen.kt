package br.com.engcomp.paroquiansmh.presentation.ui.screen.biblia

import android.content.Context
import android.content.Intent
import android.os.Vibrator
import android.os.VibratorManager
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.ModalBottomSheet
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextButton
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.rememberModalBottomSheetState
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.core.content.ContentProviderCompat
import androidx.core.content.ContentProviderCompat.requireContext
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.presentation.navigation.NavigationHost
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.CardBiblia
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.launch
import java.lang.StringBuilder


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun LivroScreen(
    navController: NavController, homeViewModel: HomeViewModel,
    tituloLivro: String = "teste", antigoOuNovoTestamento: Int = 0){

    val listState = rememberLazyListState()
    val livroSelecionado = when(antigoOuNovoTestamento){
        0 -> homeViewModel.bibliaCompletaAntigoTestatmento().get(key = tituloLivro)!!.collectAsState().value
        1 -> homeViewModel.bibliaCompletaNovoTestatmento().get(key = tituloLivro)!!.collectAsState().value
        else -> {homeViewModel.bibliaCompletaAntigoTestatmento().get(key = tituloLivro)!!.collectAsState().value}
    }

    val sheetState = rememberModalBottomSheetState()
    var showBottomSheet by remember { mutableStateOf(false) }
    val scope = rememberCoroutineScope()
    val context = LocalContext.current

    Scaffold(
        snackbarHost = {
            if(showBottomSheet){
                ModalBottomSheet(
                    onDismissRequest = { showBottomSheet = false },
                    sheetState = sheetState,
                    modifier = Modifier.fillMaxWidth()
                ) {
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()){
                        Icon(painter = painterResource(id = R.drawable.icon_info), contentDescription = "icon_info")
                        Text(
                            modifier = Modifier
                                .padding(10.dp),
                            text = "Clique em um dos capítulos para acessar os versículos")
                    }
                    Row(
                        verticalAlignment = Alignment.CenterVertically,
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth()){
                        Icon(painter = painterResource(id = R.drawable.icon_info), contentDescription = "icon_info")
                        Text(
                            modifier = Modifier
                                .padding(10.dp),
                            text = "Clique e pressione um versículo para salvar ou compartilhar o conteúdo")
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
                },
                actions = {
                    IconButton(onClick = {
                        showBottomSheet = true
                    }){
                        Icon(
                            painter = painterResource(id = R.drawable.icon_menu_vert),
                            contentDescription = "icone de menu"
                        )
                    }
                }
            )
        },
        content = {
                innerPadding->
            LazyColumn(state = listState,modifier = Modifier.fillMaxSize(), contentPadding = innerPadding){

                itemsIndexed(items = livroSelecionado){ index, it->

                    Card {
                        CardBiblia(
                            bibliaDTO = it,
                            index = index+1,
                            homeViewModel = homeViewModel,
                            navController = navController,
                            rota = "capitulo-screen",
                            testamento = antigoOuNovoTestamento
                        )
                    }
                    Spacer(modifier = Modifier.height(10.dp))

                }
            }
        }
    )
}