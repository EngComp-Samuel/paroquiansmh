package br.com.engcomp.paroquiansmh.presentation.ui.componentes

import androidx.compose.foundation.Image
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.ui.theme.AppTypography
import br.com.engcomp.paroquiansmh.ui.theme.primaryDark
import br.com.engcomp.paroquiansmh.ui.theme.primaryLight


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun TopBarCustom(){
    TopAppBar(
        title = {
            Text(text = "Paróquia João Câmara", fontFamily = AppTypography.displayLarge.fontFamily)
        },
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