package br.com.engcomp.paroquiansmh.presentation.ui

import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.compose.rememberNavController

import br.com.engcomp.paroquiansmh.presentation.navigation.SetupNavGraph


//essa lib ainda esta na versao beta
//import androidx.compose.material3.adaptive.navigationsuite


@OptIn(ExperimentalMaterial3Api::class)
//@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun MainScreen() {
    val navController = rememberNavController()
    Scaffold{
        /*NavHost(navController = navController, startDestination = "splash") {
            composable("splash") { SplashScreen(navController) }
            composable("home") { HomeScreen(navController) }
            composable("profile") { ProfileScreen(navController) }
            composable("setting") { SettingScreen(navController) }
        }*/
        //HomeScreen(navController = navController)
        paddingValues ->  SetupNavGraph(navController = navController, paddingValues = paddingValues)
    }
}





