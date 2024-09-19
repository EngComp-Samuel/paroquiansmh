package br.com.engcomp.paroquiansmh.presentation.ui.componentes

import androidx.compose.runtime.Composable

data class CardProjeto(
    val id: Int,
    val imagem: Int,
    val descricaoImagem: String,
    val textoTituloProjeto: String,
    val textoResumoProjeto: String,
    val rotaDoProjeto: String,
)
