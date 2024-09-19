package br.com.engcomp.paroquiansmh.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Notifications
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class NavigationHost(val route: String) {

    object Splash: NavigationHost("splash")
    object Home: NavigationHost("home")

    object Profile: NavigationHost("profile")
    object Setting: NavigationHost("setting")
    object Notifications: NavigationHost("notifications")

    //rotas dos botoes da tela de home
    object Biblia: NavigationHost("biblia")
    object Ministerios: NavigationHost("ministerios")
    object Leituras: NavigationHost("leituras")
    object Doacoes: NavigationHost("doacoes")
    object SobreApp: NavigationHost("sobre_app")
    object Paroquia: NavigationHost("paroquia")

    object Livro: NavigationHost("livro-screen")

    //rotas dos botoes de projetos
    object ProjetoCaritas: NavigationHost("projeto_caritas")
}