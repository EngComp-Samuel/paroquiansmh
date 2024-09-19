package br.com.engcomp.paroquiansmh.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column


import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues


import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid

import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.CardProjeto
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.PadraoBotao
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.PadraoCardItem
import br.com.engcomp.paroquiansmh.presentation.ui.textos.TextoCorpo
import br.com.engcomp.paroquiansmh.presentation.ui.textos.TextoTitulo
import br.com.engcomp.paroquiansmh.presentation.ui.textos.TextosPersonalizados
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel
import br.com.engcomp.paroquiansmh.ui.theme.AppTypography
import br.com.engcomp.paroquiansmh.ui.theme.playFairDisplayFontFamily

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel){
    val listaDeProjetos = listOf(
        CardProjeto(
            id = 0, imagem = R.drawable.caritas_vector_logo,
            descricaoImagem = "imagem da paroquia",
            textoTituloProjeto = "Caritas",
            textoResumoProjeto = """
                A Caritas Internacional é uma confederação de 165 organizações humanitárias da Igreja Católica que atua em mais de duzentos países.
            """.trimIndent(),
            rotaDoProjeto = "projeto_caritas"),
        CardProjeto(
            id = 0, imagem = R.drawable.logo_segue_me,
            descricaoImagem = "imagem da paroquia",
            textoTituloProjeto = "Segue-me",
            textoResumoProjeto = "O Segue-me é um movimento de espiritualidade e formação voltado para jovens e adolescente, a expressão SEGUE-ME é inspirada na vocação de Mateus.",
            rotaDoProjeto = "projeto_caritas"),
        CardProjeto(
            id = 0, imagem = R.drawable.paroquia_imagem,
            descricaoImagem = "imagem da paroquia",
            textoTituloProjeto = "Titulo do projeto",
            textoResumoProjeto = "O projeto faz isso e isso mais",
            rotaDoProjeto = "projeto_caritas")
    )

    val padding: Dp = 30.dp

    val contentPadding: PaddingValues = WindowInsets(
        left = 0.dp,
        top = 0.dp,
        right = 0.dp,
        bottom = padding,
    ).add(WindowInsets.navigationBars).asPaddingValues()

    val orientacaoTela = LocalConfiguration.current.orientation

    LazyColumn(
                modifier = Modifier.
                    fillMaxSize(), contentPadding = contentPadding)
            {
                item{
                    Row {

                        Box(contentAlignment = Alignment.Center){
                            if (orientacaoTela == 1){
                            Image(
                                modifier = Modifier
                                    .fillMaxWidth()
                                    .clip(
                                        RoundedCornerShape(
                                            bottomStart = 80.dp,
                                            bottomEnd = 80.dp
                                        )
                                    ),
                                painter = painterResource(id = R.drawable.paroquia_imagem),
                                contentDescription = "Imagem da paroquia de jc",
                                alpha = .15f,
                                contentScale = ContentScale.Inside
                            )}
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween) {
                                TextoTitulo(texto = "Paróquia", pesoFonte = FontWeight.Medium)
                                TextoTitulo(texto = "Nossa Senhora Mãe dos Homens")
                                TextoCorpo(texto = "João Câmara/RN")
                            }

                        }
                    }
                }

                item{
                    TextosPersonalizados(homeViewModel)
                    HorizontalDivider()
                }


                item {
                    ListaGrid(navController = navController)
                }

                item{
                    TextoCorpo(texto = "Projetos")
                    HorizontalDivider()
                }

                item{
                    LazyRow{
                        items(listaDeProjetos.size) {index->
                            CardProjetosItem(
                                imagem = listaDeProjetos[index].imagem,
                                descricao = listaDeProjetos[index].descricaoImagem,
                                textoTituloProjeto = listaDeProjetos[index].textoTituloProjeto,
                                textoResumoProjeto = listaDeProjetos[index].textoResumoProjeto,
                                rotaDoProjeto = listaDeProjetos[index].rotaDoProjeto,
                                navController = navController
                            )
                        }
                    }
                }
            }
        }


@Composable
fun ListaGrid(navController: NavController){

    val listaDeIcones = listOf(
        PadraoBotao(
            id = 0,
            texto = "Bíblia",
            icone = R.drawable.icon_book,
            descricao = "botao biblia",
            rota = "biblia"
        ),
        PadraoBotao(
            id = 1,
            texto = "Ministérios",
            icone = R.drawable.icon_ministerios,
            descricao = "botao ministerios",
            rota = "ministerios"
        ),
        PadraoBotao(
            id = 2,
            texto = "Leituras",
            icone = R.drawable.icon_leituras,
            descricao = "botao leituras",
            rota = "leituras"
        ),
        PadraoBotao(
            id = 3,
            texto = "Doações",
            icone = R.drawable.icon_doacao,
            descricao = "botao doacoes",
            rota = "doacoes"
        ),
        PadraoBotao(
            id = 4,
            texto = "teste",
            icone = R.drawable.baseline_notifications_24,
            descricao = "teste",
            rota = "teste"
        ),
        PadraoBotao(
            id = 5,
            texto = "teste",
            icone = R.drawable.baseline_notifications_24,
            descricao = "teste",
            rota = "teste"
        ),
        PadraoBotao(
            id = 6,
            texto = "teste",
            icone = R.drawable.baseline_notifications_24,
            descricao = "teste",
            rota = "teste"
        ),
        PadraoBotao(
            id = 7,
            texto = "Paróquia",
            icone = R.drawable.icon_igreja,
            descricao = "teste",
            rota = "paroquia"
        ),
        PadraoBotao(
            id = 8,
            texto = "Sobre o app",
            icone = R.drawable.icon_info,
            descricao = "botao sobre o app",
            rota = "sobre_app"
        )
    )

    LazyVerticalGrid(
        columns = GridCells.Fixed(3),
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .height(280.dp)

    ){
        items(listaDeIcones.size){
                index ->
            PadraoCardItem(
                icone = listaDeIcones[index].icone,
                descricao = listaDeIcones[index].descricao,
                textoBotao = listaDeIcones[index].texto,
                navController = navController,
                rota = listaDeIcones[index].rota)
        }
    }
}

@Composable
fun CardProjetosItem(
    imagem: Int,
    descricao: String,
    textoTituloProjeto: String,
    textoResumoProjeto: String,
    rotaDoProjeto: String,
    navController: NavController
){
    Card(elevation = CardDefaults.elevatedCardElevation(
        defaultElevation = 5.dp,
        pressedElevation = 0.dp
    ),modifier = Modifier
        .padding(8.dp)
        .clip(
            RoundedCornerShape(
                bottomStart = 30.dp,
                bottomEnd = 30.dp,
                topStart = 30.dp,
                topEnd = 30.dp
            )
        )
        .size(width = 350.dp, height = 300.dp)){

        Row(modifier = Modifier.fillMaxWidth()){
            Image(
                painter = painterResource(id = imagem),
                contentScale = ContentScale.FillWidth,
                contentDescription = descricao,
                modifier = Modifier
                    .size(width = 350.dp, height = 130.dp)
                    .clip(
                        RoundedCornerShape(
                            bottomStart = 0.dp,
                            bottomEnd = 0.dp,
                            topStart = 30.dp,
                            topEnd = 30.dp
                        )
                    ),
            )
        }

        TextoCorpo(texto = textoTituloProjeto)
        Text(
            text = textoResumoProjeto,
            fontSize = AppTypography.labelSmall.fontSize,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            lineHeight = AppTypography.labelSmall.lineHeight,
            maxLines = 2,
            overflow = TextOverflow.Ellipsis
        )
        Button(onClick = {
            navController.navigate(rotaDoProjeto){
                popUpTo(rotaDoProjeto){
                    saveState = true
                }
            }
        }, modifier = Modifier.padding(8.dp)) {
            Text(text = "Saiba mais >")
        }
    }
}


