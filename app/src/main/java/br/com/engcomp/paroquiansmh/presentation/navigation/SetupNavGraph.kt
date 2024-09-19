package br.com.engcomp.paroquiansmh.presentation.navigation


import androidx.compose.foundation.layout.PaddingValues

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import br.com.engcomp.paroquiansmh.presentation.ui.HomeScreen
import br.com.engcomp.paroquiansmh.presentation.ui.SplashScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.biblia.BibliaScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.biblia.LivroScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.doacoes.DoacoesScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.leituras.LeiturasScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.ministerios.MinisteriosScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.paroquia.ParoquiaScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.projetos.ProjetoCaritasScreen
import br.com.engcomp.paroquiansmh.presentation.ui.screen.sobre.SobreAppScreen
import br.com.engcomp.paroquiansmh.presentation.viewmodel.HomeViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SetupNavGraph(navController: NavHostController, paddingValues: PaddingValues,homeViewModel: HomeViewModel){

    NavHost(navController = navController, startDestination = NavigationHost.Splash.route){

        composable(route = NavigationHost.Splash.route){
            SplashScreen(navController = navController)
        }
        composable(route = NavigationHost.Home.route){
            HomeScreen(navController = navController, homeViewModel = homeViewModel)
        }
        composable(route = NavigationHost.Biblia.route){
            BibliaScreen(navController = navController, homeViewModel = homeViewModel)
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

        //rotas dos livros da biblia
        //TODO: VER UMA FORMA DE ENVIAR ARGUMENTOS OU FLAGS
        composable(route = NavigationHost.Livro.route+"/{nomeLivro}", arguments = listOf(
            navArgument("nomeLivro"){
                type = NavType.StringType
                defaultValue = ""
                nullable = true
            }
        )){
            LivroScreen(
                navController = navController,
                homeViewModel = homeViewModel,
            tituloLivro = it.arguments?.getString("nomeLivro")?:"")
        }

        //rotas dos botoes de projetos
        composable(route = NavigationHost.ProjetoCaritas.route){
            ProjetoCaritasScreen(navController = navController)
        }

    }

}


















