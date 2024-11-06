package br.com.engcomp.paroquiansmh.data.remoto

import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.CardProjeto

class SimulacaoRepositorioRemoto(private val dao: SimulacaoRepositorioRemotoDao) {


    val listaDeProjetos = dao.listarCardsProjetos()

}