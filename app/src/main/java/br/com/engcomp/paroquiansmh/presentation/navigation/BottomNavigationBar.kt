package br.com.engcomp.paroquiansmh.presentation.navigation


import androidx.compose.foundation.isSystemInDarkTheme

import androidx.compose.foundation.layout.WindowInsets
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.BottomAppBarDefaults.containerColor
import androidx.compose.material3.BottomAppBarDefaults.windowInsets
import androidx.compose.material3.Icon
import androidx.compose.material3.ListItemDefaults.contentColor
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarDefaults
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.NavigationBarItemColors
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import androidx.navigation.compose.currentBackStackEntryAsState
import br.com.engcomp.paroquiansmh.ui.theme.primaryDark
import br.com.engcomp.paroquiansmh.ui.theme.primaryLight

@Composable
fun BottomNavigationBar(navController: NavController){

    val items = listOf(
        BottomNavItem.Home,
        BottomNavItem.Profile,
        BottomNavItem.Setting
    )

    val orientacaoTela = LocalConfiguration.current.orientation
    val larguraDaTela = LocalConfiguration.current.screenWidthDp
    val centroDaTela = larguraDaTela/2
    val trintaPorcentoDaTela = larguraDaTela * 0.2

    val navBackStackEntry = navController.currentBackStackEntryAsState()
    val currentRoute = navBackStackEntry.value?.destination?.route
    NavigationBar(
        modifier =
            if(orientacaoTela == 1) Modifier.padding(bottom = 70.dp, start = trintaPorcentoDaTela.dp, end = trintaPorcentoDaTela.dp)
                .size(height = (larguraDaTela * 0.21).dp, width = (larguraDaTela * 0.6).dp)
                .fillMaxWidth()
                .clip(RoundedCornerShape(topStart = 80.dp, topEnd = 80.dp, bottomEnd = 80.dp, bottomStart = 80.dp))
            else Modifier.padding(bottom = 0.dp, start = 0.dp, end = 0.dp).clip(RoundedCornerShape(topStart = 30.dp, topEnd = 30.dp)),
            containerColor = if (isSystemInDarkTheme()) primaryLight else primaryDark,
            contentColor = if (isSystemInDarkTheme()) Color.White else Color.Black,
            windowInsets = WindowInsets(left = 0, top = 0, right = 0, bottom = 0),

       //tonalElevation = 5.dp
    ){
        items.forEach { item ->
            NavigationBarItem(
                colors = NavigationBarItemColors(
                    selectedIconColor = if (isSystemInDarkTheme()) primaryDark else primaryLight,
                    selectedTextColor = if (isSystemInDarkTheme()) primaryDark else primaryLight,
                    selectedIndicatorColor = Color.White,
                    unselectedIconColor = Color.Gray,
                    unselectedTextColor = Color.Gray,
                    disabledTextColor = Color.Green,
                    disabledIconColor = Color.Green,
                ),
                icon = { Icon(item.icon, contentDescription = item.label) },
                label = { Text(text = item.label) },
                selected = currentRoute == item.route,
                onClick = {
                    navController.navigate(item.route) {
                        popUpTo(navController.graph.startDestinationId){
                            saveState = true
                        }
                        launchSingleTop = true
                        restoreState = true
                    }
                }
            )
        }
    }

}


















