package br.com.engcomp.paroquiansmh.presentation.ui.screen.biblia

import android.content.Context
import android.content.Intent
import android.os.Build
import android.os.VibrationEffect
import android.os.Vibrator
import androidx.annotation.RequiresApi
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.clickable
import androidx.compose.foundation.combinedClickable
import androidx.compose.ui.Alignment
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column

import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.lazy.rememberLazyListState
import androidx.compose.foundation.selection.toggleable
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.rememberCoroutineScope
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue

import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.semantics.Role
import androidx.compose.ui.text.style.LineHeightStyle
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.data.local.BibliaDTO
import br.com.engcomp.paroquiansmh.data.local.EntidadeShared
import br.com.engcomp.paroquiansmh.presentation.navigation.NavigationHost
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.CardBiblia
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch
import java.io.Serializable


@RequiresApi(Build.VERSION_CODES.Q)
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun CapituloScreen(
    capituloTitulo: String,
    capitulo: Int,
    testamento: Int,
    navController: NavController,
    homeViewModel: HomeViewModel
){
    val context = LocalContext.current
    val vibrator = context.getSystemService(Context.VIBRATOR_SERVICE) as Vibrator
    val valores = homeViewModel.listaCapituloVersiculos.collectAsState().value

    var versiculosParaCompartilhar by rememberSaveable {
        mutableStateOf(
            valores.map{
                EntidadeShared(
                    titulo = capituloTitulo,
                    mensagem = listOf(it),
                    isSelect = false
                )
            }
        )
    }
    val listinhaTemp = rememberSaveable{ mutableStateOf("") }
    val sendIntent: Intent = Intent().apply {
        action = Intent.ACTION_SEND
        putExtra(Intent.EXTRA_TEXT, listinhaTemp.value)
        type = "text/plain"
    }
    val scope = rememberCoroutineScope()
    var controlar by rememberSaveable { mutableStateOf(false) }

    val shareIntent = Intent.createChooser(sendIntent, "share")
    val listState = rememberLazyListState()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = capituloTitulo)
                },
                navigationIcon = {
                    IconButton(
                        onClick = {
                            navController.popBackStack()
                        }
                    ) {
                        Icon(
                            painter = painterResource(id = R.drawable.icon_arrow_back),
                            contentDescription = "icone de voltar"
                        )
                    }
                },
                actions = {
                    if(controlar){
                        IconButton(
                            onClick = {
                                homeViewModel.SorteandoMensagemInicial()
                                context.startActivity(shareIntent)
                            }
                        ){
                            Row {
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_share),
                                    contentDescription = "icone de refresh"
                                )
                            }
                        }
                    }
                }
            )
        },
        content = {
                innerPadding->
            LazyColumn(state = listState,modifier = Modifier.fillMaxSize(), contentPadding = innerPadding){

                items(versiculosParaCompartilhar.size){ i ->
                    Row(modifier = Modifier
                        .fillMaxWidth()
                        .combinedClickable(onLongClick = {
                            versiculosParaCompartilhar =
                                versiculosParaCompartilhar.mapIndexed { j, item ->
                                    if (i == j) {
                                        item.copy(isSelect = !item.isSelect)
                                    } else {
                                        item
                                    }
                                }

                            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
                                val vibrationEffect: VibrationEffect =
                                    VibrationEffect.createPredefined(
                                        VibrationEffect.EFFECT_CLICK
                                    )
                                vibrator.cancel()
                                vibrator.vibrate(vibrationEffect)
                            }
                            val cap = versiculosParaCompartilhar[i].titulo

                            if (versiculosParaCompartilhar[i].isSelect) {
                                listinhaTemp.value += cap
                                listinhaTemp.value += "\n \n"
                                versiculosParaCompartilhar[i].mensagem.forEach {
                                    listinhaTemp.value += "${i + 1}" + " - " + it
                                    listinhaTemp.value += "\n \n"
                                }
                            }
                            scope.launch {
                                controlar = false
                                versiculosParaCompartilhar.forEach {
                                    if (it.isSelect) {
                                        controlar = true
                                    }
                                }
                                if (!controlar) {
                                    delay(1000)
                                    listinhaTemp.value = ""
                                }
                            }

                        }, onClick = {})){

                        Column {
                            Text(
                                text = "${i+1} - "+"${versiculosParaCompartilhar[i].mensagem}".removePrefix("[").removeSuffix("]"),
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .padding(10.dp),
                                color = if (versiculosParaCompartilhar[i].isSelect) Color.Gray else Color.Unspecified)

                            if (versiculosParaCompartilhar[i].isSelect){
                                Icon(
                                    painter = painterResource(id = R.drawable.icon_check),
                                    contentDescription = "check",
                                    modifier = Modifier
                                        .padding(10.dp)
                                        .align(alignment = Alignment.End)
                                )
                            }
                            HorizontalDivider(
                                modifier = Modifier
                                    .fillMaxWidth(0.60f)
                                    .align(alignment = Alignment.CenterHorizontally)
                            )
                        }
                    }
                }
        }
        }
    )
}