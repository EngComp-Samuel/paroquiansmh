package br.com.engcomp.paroquiansmh.data.remoto

import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.CardProjeto
import kotlinx.coroutines.flow.Flow


interface SimulacaoRepositorioRemotoDao {

    fun listarCardsProjetos(): List<CardProjeto> = listOf(
            CardProjeto(
                id = 0, imagem = R.drawable.caritas_vector_logo,
                descricaoImagem = "imagem da paroquia",
                textoTituloProjeto = "Caritas",
                textoResumoProjeto = """
                A Caritas Internacional é uma confederação de 165 organizações humanitárias da Igreja Católica que atua em mais de duzentos países.
            """.trimIndent(),
                rotaDoProjeto = "projeto_caritas"),
            CardProjeto(
                id = 0, imagem = R.drawable.logo_segue_me,
                descricaoImagem = "imagem da paroquia",
                textoTituloProjeto = "Segue-me",
                textoResumoProjeto = "O Segue-me é um movimento de espiritualidade e formação voltado para jovens e adolescente, a expressão SEGUE-ME é inspirada na vocação de Mateus.",
                rotaDoProjeto = "projeto_caritas"),
            CardProjeto(
                id = 0, imagem = R.drawable.paroquia_imagem,
                descricaoImagem = "imagem da paroquia",
                textoTituloProjeto = "Titulo do projeto",
                textoResumoProjeto = "O projeto faz isso e isso mais",
                rotaDoProjeto = "projeto_caritas")
        )



}