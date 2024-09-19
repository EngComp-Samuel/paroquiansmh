package br.com.engcomp.paroquiansmh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.activity.viewModels
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import br.com.engcomp.paroquiansmh.presentation.ui.MainScreen
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel
import br.com.engcomp.paroquiansmh.ui.theme.ParoquiaNSMHTheme

class MainActivity : ComponentActivity() {

    private val homeViewModel: HomeViewModel by viewModels()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {

            val context = LocalContext.current
            homeViewModel.CarregarAlgunsLivros(context = context)
            homeViewModel.SorteandoMensagemInicial()

            ParoquiaNSMHTheme {
                MainScreen(homeViewModel = homeViewModel)
            }
        }
    }
}
