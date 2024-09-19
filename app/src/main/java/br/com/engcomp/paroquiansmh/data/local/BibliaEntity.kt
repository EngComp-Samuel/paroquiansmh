package br.com.engcomp.paroquiansmh.data.local

data class BibliaEntity(
    val abrev: String,
    val chapters: List<List<String>>

)


//TODO: ANALISAR O DTO E O PADRAO A SER UTILIZADO E VER TAMBEM O MAPPER E A QUESTAO DO CLEAN ARQUITETURE
// VERIFICAR COMO ISSO VAI FICAR MODULAR E FACIL DE DAR MANUTENCAO