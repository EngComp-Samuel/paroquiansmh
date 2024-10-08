package br.com.engcomp.paroquiansmh.presentation.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

import br.com.engcomp.paroquiansmh.presentation.navigation.SetupNavGraph
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel


//essa lib ainda esta na versao beta
//import androidx.compose.material3.adaptive.navigationsuite

@Composable
fun MainScreen(homeViewModel: HomeViewModel) {
    val navController = rememberNavController()
    Scaffold{
        paddingValues ->
            SetupNavGraph(
                navController = navController,
                paddingValues = paddingValues,
                homeViewModel = homeViewModel)
    }
}





