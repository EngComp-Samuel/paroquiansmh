package br.com.engcomp.paroquiansmh.presentation.ui.screen.paroquia

import androidx.compose.foundation.layout.Arrangement
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
fun ParoquiaScreen(navController: NavController){

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
                    Text(text = "Paróquia")
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
                verticalArrangement = Arrangement.spacedBy(16.dp)
            ){
                Text(
                    modifier = Modifier.padding(8.dp),
                    text =
                    """
                    Praça Mons. Freitas, 188 

Centro - 59550-000 - João Câmara - RN
Telefone: (84) 99653-2647
E-mail: pnsmhomens01@gmail.com


Pároco: Pe. Josino Raimundo da Silva

Vigários Paroquiais:  Pe. Pedro da Cunha Júnio, Pe. Wagner Martins e Pe. João Eudes de Souza Lopes


Redes sociais

Instagram - @paroquiamaedoshomens

www.facebook.com/paroquiajoaocamara/

Youtube – TV Mãe dos Homens


Horários de Missas
Igreja Matriz de Nossa Senhora Mãe dos Homens
Domingo - 7h, 10h e 19h

Terça a sexta - 17h30

Sábado - 10h (missa dos feirantes)
 1ª sexta do mês - 7h

3ª quinta do mês - 19h

 Igreja de São João Batista - Jardim de Angicos
 Domingo - 19h

1º sexta do mês - 19h


Municípios que abrange: João Câmara, Bento Fernandes e Jardim de Angicos.

Data de criação: 13/11/1929
                """.trimIndent(),
                )
            }
        }
    )
}

