package br.com.engcomp.paroquiansmh.presentation.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.engcomp.paroquiansmh.data.local.BibliaDTO
import br.com.engcomp.paroquiansmh.data.local.BibliaEntity
import br.com.engcomp.paroquiansmh.data.local.HomeMensagemInicial
import br.com.engcomp.paroquiansmh.data.local.UmCorintiosDTO
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.io.IOException
import kotlin.random.Random

class HomeViewModel: ViewModel() {

    init {
        viewModelScope.launch {
            //sempre q iniciar - carrega o arquivo json
            //LeituraJson(context)
        }
    }

    //LEITURA DO ARQUIVO JSON
    private var _biblia = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val biblia : StateFlow<List<BibliaDTO>> = _biblia

    fun CarregarAlgunsLivros(context: Context){

        for ((index, valor) in listaComOsLivros().withIndex()){
            print("AQUI: " + valor)
            lateinit var jsonString : String
            try {
                jsonString = context.assets.open("biblia_nvi/${valor}").bufferedReader().use { it.readText() }
            }catch (ioException: IOException){
                Log.d("Erro AQUI", ioException.toString())
            }

            val gson = Gson()
            val bibliaType = object : TypeToken<List<BibliaDTO>>() {}.type
            val livroLeitura: List<BibliaDTO> = gson.fromJson(jsonString, bibliaType)
            livrosParaPreencher()[index].value = livroLeitura
        }

    }


    private var _mensagemInicialAleatoria = MutableStateFlow("")
    val mensagemInicialAleatoria : StateFlow<String> = _mensagemInicialAleatoria
    private var _nomeLivroInicialAleatorio = MutableStateFlow("")
    val nomeLivroInicialAleatorio : StateFlow<String> = _nomeLivroInicialAleatorio

    fun SorteandoMensagemInicial(){
        //TODO: POR ENQUANTO QUE NAO TEM TODOS, PEGAR O QUE TEM
        //aqui seria livro aleatorio, mas vamos usar capitulo
        val livroAleatorio = Random.nextInt(0, livrosParaPreencher().size)
        val capituloAleatorios = Random.nextInt(0, livrosParaPreencher().get(livroAleatorio).value.size)
        val capituloSelecionado = Random.nextInt(0, livrosParaPreencher().get(livroAleatorio).value.get(capituloAleatorios).capitulo.size)
        val mensagemAleatoria =  livrosParaPreencher().get(livroAleatorio).value.get(capituloAleatorios).capitulo.get(capituloSelecionado)
        val nomeLivroAleatorio = livrosParaPreencher().get(livroAleatorio).value.get(capituloAleatorios).nome
        _mensagemInicialAleatoria.value = mensagemAleatoria
        _nomeLivroInicialAleatorio.value = nomeLivroAleatorio
    }

    //TODO: PEGAR ESSA LOGICA AQUI: _mensagemInicial.value = biblia.value.get(capituloDaBiblia).chapters.get(versiculoDoCapitulo).toString()
    //       _nomeLivroInicial.value = biblia.value.get(capituloDaBiblia).name
    // E TENTAR APLICAR ELA NO BIBLIA SCREEN
    // QUANTIDADE DE LIVROS = biblia.value.size
    // QUANTIDADE DE VERSICULOS POR LIVRO = biblia.value.get(capituloDaBiblia).chapters.size



    /**
     * TODO: DESCRIÇÃO DAS SIGLAS:
     *
     * Cada abreviação corresponde a:
     *
     * Nova Versão Internacional (NVI)
     * Almeida Corrigida e Fiel (ACF)
     * Almeida Revisada Imprensa Bíblica (AA)
     *
     */

    private var _UmCorintios = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val umCorintios : StateFlow<List<BibliaDTO>> = _UmCorintios

    private var _UmCronicas = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val umCronicas : StateFlow<List<BibliaDTO>> = _UmCronicas

    private var _UmJoao = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val umJoao : StateFlow<List<BibliaDTO>> = _UmJoao

    private var _UmPedro = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val umPedro : StateFlow<List<BibliaDTO>> = _UmPedro

    private var _UmReis = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val umReis : StateFlow<List<BibliaDTO>> = _UmReis

    private var _UmSamuel = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val umSamuel : StateFlow<List<BibliaDTO>> = _UmSamuel

    private var _UmTessalonicenses = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val umTessalonicenses : StateFlow<List<BibliaDTO>> = _UmTessalonicenses

    private var _UmTimoteo = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val umTimoteo : StateFlow<List<BibliaDTO>> = _UmTimoteo

    private var _DoisCorintios = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val doisCorintios : StateFlow<List<BibliaDTO>> = _DoisCorintios

    private var _DoisCronicas = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val doisCronicas : StateFlow<List<BibliaDTO>> = _DoisCronicas

    private var _DoisJoao = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val doisJoao : StateFlow<List<BibliaDTO>> = _DoisJoao

    private var _DoisPedro = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val doisPedro : StateFlow<List<BibliaDTO>> = _DoisPedro

    private var _DoisReis = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val doisReis : StateFlow<List<BibliaDTO>> = _DoisReis

    private var _DoisSamuel = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val doisSamuel : StateFlow<List<BibliaDTO>> = _DoisSamuel

    private var _DoisTessalonicenses = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val doisTessalonicenses : StateFlow<List<BibliaDTO>> = _DoisTessalonicenses

    private var _DoisTimoteo = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val doisTimoteo : StateFlow<List<BibliaDTO>> = _DoisTimoteo

    private var _TresJoao = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val tresJoao : StateFlow<List<BibliaDTO>> = _TresJoao

    private var _Ageu = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val ageu : StateFlow<List<BibliaDTO>> = _Ageu

    private var _Amos = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val amos : StateFlow<List<BibliaDTO>> = _Amos

    private var _Apocalipse = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val apocalipse : StateFlow<List<BibliaDTO>> = _Apocalipse

    fun listaComOsLivros():List<String>{
        val listaDeLivros = listOf("1_corintios.json", "1_cronicas.json", "1_joao.json", "1_pedro.json",
            "1_reis.json", "1_samuel.json", "1_tessalonicenses.json", "1_timoteo.json", "2_corintios.json",
            "2_cronicas.json", "2_joao.json", "2_pedro.json", "2_reis.json", "2_samuel.json", "2_tessalonicenses.json",
            "2_timoteo.json","3_joao.json","ageu.json","amos.json","apocalipse.json")
        return listaDeLivros
    }

    fun livrosParaPreencher():List<MutableStateFlow<List<BibliaDTO>>>{
        val livrosParaPreencher = listOf(_UmCorintios, _UmCronicas, _UmJoao, _UmPedro, _UmReis, _UmSamuel,
            _UmTessalonicenses, _UmTimoteo, _DoisCorintios, _DoisCronicas, _DoisJoao, _DoisPedro, _DoisReis,
            _DoisSamuel, _DoisTessalonicenses, _DoisTimoteo, _TresJoao, _Ageu, _Amos, _Apocalipse)

        return livrosParaPreencher
    }



    fun bibliaCompletaAntigoTestatmento(): List<StateFlow<List<BibliaDTO>>>{

        return emptyList()
    }

    fun bibliaCompletaNovoTestatmento(): List<StateFlow<List<BibliaDTO>>>{

        return emptyList()
    }


}








