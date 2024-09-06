package br.com.engcomp.paroquiansmh.presentation.navigation


import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import br.com.engcomp.paroquiansmh.presentation.ui.HomeScreen
import br.com.engcomp.paroquiansmh.presentation.ui.SplashScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.BibliaScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.doacoes.DoacoesScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.leituras.LeiturasScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.ministerios.MinisteriosScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.paroquia.ParoquiaScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.projetos.ProjetoCaritas
import br.com.engcomp.paroquiansmh.presentation.ui.screen.sobre.SobreAppScreen

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(navController: NavHostController, paddingValues: PaddingValues){

    NavHost(navController = navController, startDestination = NavigationHost.Splash.route){

        composable(route = NavigationHost.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = NavigationHost.Home.route){
            HomeScreen(navController = navController)
        }
        composable(route = NavigationHost.Biblia.route){
            BibliaScreen(navController = navController)
        }
        composable(route = NavigationHost.Ministerios.route){
            MinisteriosScreen(navController = navController)
        }
        composable(route = NavigationHost.Leituras.route){
            LeiturasScreen(navController = navController)
        }
        composable(route = NavigationHost.Doacoes.route){
            DoacoesScreen(navController = navController)
        }
        composable(route = NavigationHost.SobreApp.route){
            SobreAppScreen(navController = navController)
        }
        composable(route = NavigationHost.Paroquia.route){
            ParoquiaScreen(navController = navController)
        }

        //rotas dos botoes de projetos
        composable(route = NavigationHost.ProjetoCaritas.route){
            ProjetoCaritas(navController = navController)
        }

    }

}


















