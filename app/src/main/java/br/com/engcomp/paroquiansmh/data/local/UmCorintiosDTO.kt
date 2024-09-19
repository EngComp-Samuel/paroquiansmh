package br.com.engcomp.paroquiansmh.data.local

import com.google.gson.annotations.SerializedName

data class UmCorintiosDTO(

    @SerializedName("Nome")
    val nome: String,

    @SerializedName("Capitulo")
    val capitulo: List<String>
)