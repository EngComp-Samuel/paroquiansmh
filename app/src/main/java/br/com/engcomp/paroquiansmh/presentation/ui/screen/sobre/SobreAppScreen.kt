package br.com.engcomp.paroquiansmh.presentation.ui.screen.sobre

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.add
import androidx.compose.foundation.layout.asPaddingValues
import androidx.compose.foundation.layout.navigationBars
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.HorizontalDivider
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.Dp
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.presentation.navigation.NavigationHost


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SobreAppScreen(navController: NavController){

    val padding: Dp = 30.dp

    val contentPadding: PaddingValues = WindowInsets(
        left = 0.dp,
        top = 10.dp,
        right = 0.dp,
        bottom = padding,
    ).add(WindowInsets.navigationBars).asPaddingValues()

    Scaffold(
        topBar = {
            TopAppBar(
                title = {
                    Text(text = "Sobre o App")
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
                        Icon(painter = painterResource(id = R.drawable.icon_arrow_back), contentDescription = "")
                    }

                })
        },
        content = {
                innerPadding->
            Column(
                modifier = Modifier
                    .padding(innerPadding)
                    .verticalScroll(rememberScrollState()),
                verticalArrangement = Arrangement.spacedBy(16.dp)){

                Text(
                    modifier = Modifier.padding(10.dp),
                    text =
                    """
                    Olá, este App foi desenvolvido em parceria com a paróquia Nossa Senhora Mãe dos Homens, com intuito de ajudar os membros da comunidade a se manterem com a palavra de Deus em mãos.
                    
                    Neste App é possível acessar uma Bíblia, salvar suas leituras, compartilhar com outros fiés a palavra de Deus, é possível conhecer um pouco mais sobre os projetos no qual a paróquia está atuando.
                    
                    Além disso, ainda é possível realizar doações para ajudar a igreja a continuar seus projetos de caridade.
                """.trimIndent(),
                )
                HorizontalDivider()
            }
        }
    )
}






