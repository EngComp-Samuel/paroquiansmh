package br.com.engcomp.paroquiansmh.presentation.ui

import android.annotation.SuppressLint
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.wrapContentHeight

import androidx.compose.foundation.pager.HorizontalPager
import androidx.compose.foundation.pager.PagerState
import androidx.compose.foundation.pager.rememberPagerState

import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerBasedShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.LargeTopAppBar
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.ShapeDefaults
import androidx.compose.material3.Shapes
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.geometry.CornerRadius
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.graphics.ColorMatrix
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.ui.theme.ParoquiaNSMHTheme
import br.com.engcomp.paroquiansmh.ui.theme.primaryDark
import br.com.engcomp.paroquiansmh.ui.theme.primaryLight

@ExperimentalMaterial3Api
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@OptIn(ExperimentalMaterial3Api::class, ExperimentalFoundationApi::class)
@Composable
fun HomeScreen(navController: NavController){

    val pagerState = rememberPagerState(pageCount = { 3 })
    
    Scaffold(
        topBar = {
            TopAppBar(
                modifier = Modifier.clip(shape = RoundedCornerShape(bottomStart = 30.dp, bottomEnd = 30.dp)),
                title = {
                    Text(text = "Paróquia João Câmara", fontWeight = FontWeight.Bold,
                        color = if (isSystemInDarkTheme()) primaryDark else primaryLight)
                        },
                colors = TopAppBarDefaults.largeTopAppBarColors(
                    containerColor = if (isSystemInDarkTheme()) primaryLight else primaryDark,
                    titleContentColor = if (isSystemInDarkTheme()) primaryDark else primaryLight,
                ), 
                actions = {
                    IconButton(
                        onClick = {

                        }) {
                        Icon(painter = painterResource(id = R.drawable.baseline_notifications_24),
                            contentDescription = "notificações")
                    }
                }
            )
        }
    ){

        Column(
            modifier = Modifier.verticalScroll(rememberScrollState()),
            horizontalAlignment = Alignment.CenterHorizontally){
            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .clip(RoundedCornerShape(bottomStart = 60.dp, bottomEnd = 60.dp)),
                painter = painterResource(id = R.drawable.paroquia_imagem),
                contentDescription = "Imagem da paroquia de jc",
                alpha = .8f,
                contentScale = ContentScale.Inside
            )

            Row (modifier = Modifier
                    .fillMaxWidth()){
                Text(text = "Seja Bem-Vindo ao App da Paróquia João Câmara, aqui você poderá ler a bíblia, " +
                        " ajudar a igreja, conhecer os projetos sociais no qual a paróquia está envolvida.",
                    modifier = Modifier.padding(10.dp))

            }

            HorizontalDivider()

            

            HorizontalPager(
                state = pagerState,
                modifier = Modifier.fillMaxWidth()) {
                page -> Row(
                modifier = Modifier.fillMaxWidth(),
                verticalAlignment = Alignment.CenterVertically,
                horizontalArrangement = Arrangement.SpaceBetween) {

                Image(
                    painter = painterResource(id = R.drawable.paroquia_imagem),
                    contentScale = ContentScale.Crop,
                    contentDescription = "teste",
                    modifier = Modifier
                        .size(200.dp)
                        .clip(
                            RoundedCornerShape(
                                bottomStart = 30.dp,
                                bottomEnd = 30.dp,
                                topStart = 30.dp,
                                topEnd = 30.dp
                            )
                        ),
                )
                Column(horizontalAlignment = Alignment.CenterHorizontally){
                    Text(text = "teste")
                    Text(text = " teste teste teste teste teste teste teste teste ")
                    Button(onClick = { /*TODO*/ }) {
                        Text(text = "Saiba mais >")
                    }
                }

            }
            }
            Row(
                modifier = Modifier
                    .wrapContentHeight()
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
                    .padding(bottom = 8.dp),
                horizontalArrangement = Arrangement.Center
            ){
                repeat(pagerState.pageCount){
                    indicator -> val color = if (pagerState.currentPage == indicator) Color.DarkGray else Color.LightGray
                    Box(
                        modifier = Modifier
                            .padding(2.dp)
                            .clip(CircleShape)
                            .background(color)
                            .size(10.dp)
                    )
                }
            }
            HorizontalDivider()
    }
}
}



