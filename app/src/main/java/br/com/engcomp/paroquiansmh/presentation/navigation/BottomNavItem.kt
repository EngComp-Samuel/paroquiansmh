package br.com.engcomp.paroquiansmh.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Call
import androidx.compose.material.icons.filled.Edit
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.filled.Settings
import androidx.compose.ui.graphics.vector.ImageVector

sealed class BottomNavItem(val route: String, val icon: ImageVector, val label: String) {
    object Home: BottomNavItem("home", Icons.Default.Home, "Home")
    object Profile: BottomNavItem("profile", Icons.Default.Person, "Person")
    object Setting: BottomNavItem("setting", Icons.Default.Settings, "Setting")
}