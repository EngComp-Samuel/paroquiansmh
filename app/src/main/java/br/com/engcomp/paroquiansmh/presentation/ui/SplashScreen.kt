package br.com.engcomp.paroquiansmh.presentation.ui

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.core.Animatable
import androidx.compose.animation.core.EaseInElastic
import androidx.compose.animation.core.EaseInOutExpo
import androidx.compose.animation.core.Easing
import androidx.compose.animation.core.FastOutSlowInEasing
import androidx.compose.animation.core.Spring
import androidx.compose.animation.core.spring
import androidx.compose.animation.core.tween
import androidx.compose.animation.expandVertically
import androidx.compose.animation.fadeIn
import androidx.compose.animation.fadeOut
import androidx.compose.animation.shrinkVertically
import androidx.compose.animation.slideInHorizontally
import androidx.compose.animation.slideInVertically
import androidx.compose.animation.slideOutHorizontally
import androidx.compose.animation.slideOutVertically
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.alpha
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.ui.theme.AppTypography
import br.com.engcomp.paroquiansmh.ui.theme.corBackGroundSplashScreen
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
            .background(corBackGroundSplashScreen),
        contentAlignment = Alignment.Center)
    {

        Column(
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.Center,
            modifier = Modifier.fillMaxWidth()){
            Text(
                text = "Paróquia",
                fontWeight = FontWeight.Bold,
                fontSize = AppTypography.bodyLarge.fontSize,
                textAlign = TextAlign.Center
            )

            Image(
                painter = painterResource(id = R.drawable.paroquia_imagem),
                contentDescription = "Logo da Paróquia",
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(200.dp, 200.dp)
                    .clip(shape = CircleShape))

            Text(
                text = "Nossa Senhora Mãe dos Homens",
                fontWeight = FontWeight.Bold,
                fontSize = AppTypography.bodyLarge.fontSize,
                textAlign = TextAlign.Center
            )
        }
    }
}