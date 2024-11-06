package br.com.engcomp.paroquiansmh.presentation.ui

import android.annotation.SuppressLint
import android.content.Intent
import android.net.Uri
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
import androidx.compose.material.icons.filled.Facebook
import androidx.compose.material.icons.filled.YoutubeSearchedFor
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.CardProjeto
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.PadraoCardItem

import br.com.engcomp.paroquiansmh.presentation.ui.textos.TextosPersonalizados
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel
import br.com.engcomp.paroquiansmh.ui.theme.AppTypography


@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun HomeScreen(navController: NavController, homeViewModel: HomeViewModel){

    val linkFacebook = "https://www.facebook.com/paroquiajoaocamara/"
    val intentFacebook = Intent(Intent.ACTION_VIEW, Uri.parse(linkFacebook))

    val linkInstagram = "https://www.instagram.com/paroquiamaedoshomens/"
    val intentInstagram = Intent(Intent.ACTION_VIEW, Uri.parse(linkInstagram))

    val linkYoutube = "https://www.youtube.com/channel/UCvoLk4gQHLWz-3k2drX_53w"
    val intentYoutube = Intent(Intent.ACTION_VIEW, Uri.parse(linkYoutube))

    val context = LocalContext.current


    val padding: Dp = 30.dp

    val contentPadding: PaddingValues = WindowInsets(
        left = 0.dp,
        top = 0.dp,
        right = 0.dp,
        bottom = padding,
    ).add(WindowInsets.navigationBars).asPaddingValues()

    val orientacaoTela = LocalConfiguration.current.orientation
    val listaDeProjetos = homeViewModel.cardsTelaHome()


    LazyColumn(modifier = Modifier.fillMaxSize(), contentPadding = contentPadding) {
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
                                )
                            }
                            Column(horizontalAlignment = Alignment.CenterHorizontally,
                                verticalArrangement = Arrangement.SpaceBetween
                            ) {
                                Text(
                                    text = "Paróquia",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    textAlign = TextAlign.Center)

                                Text(
                                    text = "Nossa Senhora Mãe dos Homens",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    style = MaterialTheme.typography.headlineSmall,
                                    textAlign = TextAlign.Center,
                                    fontWeight = FontWeight.Bold)

                                Text(
                                    text = "João Câmara/RN",
                                    modifier = Modifier
                                        .fillMaxWidth()
                                        .padding(10.dp),
                                    textAlign = TextAlign.Center)
                            }
                        }
                    }
                }

                item{
                    TextosPersonalizados(homeViewModel)
                    HorizontalDivider()
                }

                item {
                    ListaGrid(navController = navController, homeViewModel = homeViewModel)
                }

        item{
            Text(text = "Redes sociais",
                modifier = Modifier
                    .padding(10.dp)
                    .fillMaxWidth(),
                textAlign = TextAlign.Center,
                fontWeight = FontWeight.Bold
            )
            HorizontalDivider()
            Row(horizontalArrangement = Arrangement.Center, modifier = Modifier.fillMaxWidth()){
                IconButton(
                    onClick = {context.startActivity(intentFacebook)}
                ) {
                    Image(painter = painterResource(id = R.drawable.img_facebook), contentDescription = "")
                }
                IconButton(
                    onClick = { context.startActivity(intentInstagram) }
                ) {
                    Image(painter = painterResource(id = R.drawable.icon_instagram), contentDescription = "")
                }
                IconButton(
                    onClick = { context.startActivity(intentYoutube) }
                ) {
                    Image(painter = painterResource(id = R.drawable.icon_youtube), contentDescription = "")
                }
            }
        }

                item{
                    Text(text = "Projetos",
                        modifier = Modifier
                            .padding(10.dp)
                            .fillMaxWidth(),
                        textAlign = TextAlign.Center,
                        fontWeight = FontWeight.Bold
                    )
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
fun ListaGrid(navController: NavController, homeViewModel: HomeViewModel){

    val orientacaoTela = LocalConfiguration.current.orientation
    val listaDeIcones = homeViewModel.botoesTelaHome()

    LazyVerticalGrid(
        columns = if(orientacaoTela == 1 ){GridCells.Fixed(3)}else{GridCells.Fixed(5)},
        contentPadding = PaddingValues(10.dp),
        modifier = Modifier
            .padding(10.dp)
            .height(250.dp)
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
        .size(width = 250.dp, height = 300.dp)){

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

        Text(
            text = textoTituloProjeto,
            modifier = Modifier
                .fillMaxWidth()
                .padding(10.dp),
            textAlign = TextAlign.Center
        )
        Text(
            text = textoResumoProjeto,
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp),
            style = MaterialTheme.typography.labelSmall,
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


