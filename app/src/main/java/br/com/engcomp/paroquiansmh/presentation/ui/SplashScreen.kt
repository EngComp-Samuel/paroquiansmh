package br.com.engcomp.paroquiansmh.presentation.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.ui.theme.primaryDark
import br.com.engcomp.paroquiansmh.ui.theme.primaryLight
import kotlinx.coroutines.delay


@Composable
fun SplashScreen(navController: NavHostController) {


    LaunchedEffect(key1 = true) {
        delay(3000)
        navController.navigate("home") {
            popUpTo("splash") { inclusive = true }
        }
    }

    Box(
        modifier = Modifier
            .fillMaxSize()
            .background(Color.Gray),
        contentAlignment = Alignment.Center)
    {
        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center){
            Image(
                painter = painterResource(id = R.drawable.paroquia_imagem),
                contentDescription = "Logo da Paróquia",
                contentScale = ContentScale.Crop,
                modifier = Modifier.size(200.dp, 200.dp).clip(shape = CircleShape))
            Text(
                text = "Paróquia Nossa Senhora Mãe dos Homens",
                fontWeight = FontWeight.Bold,
                color = if (isSystemInDarkTheme()) primaryDark else primaryLight
            )
        }
    }


}