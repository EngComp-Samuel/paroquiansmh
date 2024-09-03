package br.com.engcomp.paroquiansmh

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.ui.Modifier
import br.com.engcomp.paroquiansmh.presentation.ui.MainScreen
import br.com.engcomp.paroquiansmh.ui.theme.ParoquiaNSMHTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        enableEdgeToEdge()
        setContent {
            ParoquiaNSMHTheme {
                MainScreen()
            }
        }
    }
}
