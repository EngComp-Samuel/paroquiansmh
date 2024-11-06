package br.com.engcomp.paroquiansmh.presentation.viewmodel

import android.app.Application
import android.content.Context
import android.util.Log
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateMapOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.AndroidViewModel
//import androidx.compose.ui.window.application
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import br.com.engcomp.paroquiansmh.R
import br.com.engcomp.paroquiansmh.data.local.BibliaDTO
import br.com.engcomp.paroquiansmh.data.remoto.SimulacaoRepositorioRemoto
import br.com.engcomp.paroquiansmh.data.remoto.SimulacaoRepositorioRemotoDao
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.CardProjeto
import br.com.engcomp.paroquiansmh.presentation.ui.componentes.PadraoBotao
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.SharingStarted
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.emptyFlow
import kotlinx.coroutines.flow.onStart
import kotlinx.coroutines.flow.stateIn
import kotlinx.coroutines.launch
import java.io.IOException
import java.lang.StringBuilder
import java.util.Arrays
import kotlin.random.Random

class HomeViewModel: ViewModel() {


    private val _isLoading = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()/*
        .onStart { carregarOsDados() }
        .stateIn(
            viewModelScope,
            SharingStarted.WhileSubscribed(5000L),
            false)*/

    fun carregarOsDados(){
        println("###### CARREGANDO DADOS INI............ ")
        viewModelScope.launch {
            _isLoading.value = true
            delay(3000L)
            _isLoading.value = false
        }
    }

    fun cardsTelaHome(): List<CardProjeto>{
        val listaDeProjetos = listOf(
            CardProjeto(id = 0, imagem = R.drawable.caritas_vector_logo, descricaoImagem = "imagem da paroquia", textoTituloProjeto = "Caritas",
                textoResumoProjeto = """
                A Caritas Internacional é uma confederação de 165 organizações humanitárias da Igreja Católica que atua em mais de duzentos países.
            """.trimIndent(), rotaDoProjeto = "projeto_caritas"),
            CardProjeto(id = 0, imagem = R.drawable.logo_segue_me, descricaoImagem = "imagem da paroquia", textoTituloProjeto = "Segue-me",
                textoResumoProjeto = "O Segue-me é um movimento de espiritualidade e formação voltado para jovens e adolescente, a expressão SEGUE-ME é inspirada na vocação de Mateus.",
                rotaDoProjeto = "projeto_caritas"),
            CardProjeto(id = 0, imagem = R.drawable.paroquia_imagem, descricaoImagem = "imagem da paroquia", textoTituloProjeto = "Titulo do projeto",
                textoResumoProjeto = "O projeto faz isso e isso mais", rotaDoProjeto = "projeto_caritas")
        )
        return listaDeProjetos
    }

    fun botoesTelaHome(): List<PadraoBotao> {
        val listaDeIcones = listOf(
            PadraoBotao(id = 0, texto = "Bíblia", icone = R.drawable.icon_biblia, descricao = "botao biblia", rota = "biblia"),
            PadraoBotao(id = 1, texto = "Ministérios", icone = R.drawable.icon_ministerios_home, descricao = "botao ministerios", rota = "ministerios"),
            PadraoBotao(id = 2, texto = "Leituras", icone = R.drawable.icon_leituras_home, descricao = "botao leituras", rota = "leituras"),
            PadraoBotao(id = 3, texto = "Doações", icone = R.drawable.icon_doacoes, descricao = "botao doacoes", rota = "doacoes"),
            PadraoBotao(id = 7, texto = "Paróquia", icone = R.drawable.paroquia_imagem, descricao = "teste", rota = "paroquia"),
            PadraoBotao(id = 8, texto = "Sobre o app", icone = R.drawable.icon_info_app_home, descricao = "botao sobre o app", rota = "sobre_app")
        )
        return listaDeIcones
    }

    fun vetorImagensLivros(): List<Int>{
        val listaDeImagens = listOf(
            R.drawable.img_hebreus,
            R.drawable.img_timoteo_i,
            R.drawable.img_timoteo_ii,
            R.drawable.img_tessalonicenses_i,
            R.drawable.img_tessalonicenses_ii,
            R.drawable.img_judas,

            R.drawable.img_hebreus,
            R.drawable.img_timoteo_i,
            R.drawable.img_timoteo_ii,
            R.drawable.img_tessalonicenses_i,
            R.drawable.img_tessalonicenses_ii,
            R.drawable.img_judas,

            R.drawable.img_hebreus,
            R.drawable.img_timoteo_i,
            R.drawable.img_timoteo_ii,
            R.drawable.img_tessalonicenses_i,
            R.drawable.img_tessalonicenses_ii,
            R.drawable.img_judas,

            R.drawable.img_hebreus,
            R.drawable.img_timoteo_i,
            R.drawable.img_timoteo_ii,
            R.drawable.img_tessalonicenses_i,
            R.drawable.img_tessalonicenses_ii,
            R.drawable.img_judas,

            R.drawable.img_tessalonicenses_i,
            R.drawable.img_tessalonicenses_ii,
            R.drawable.img_judas
        )


        return listaDeImagens
    }


    private var _listaCapituloVersiculos: MutableStateFlow<List<String>> = MutableStateFlow(emptyList())
    val listaCapituloVersiculos = _listaCapituloVersiculos

    fun pesquisaCapituloPraEncontrarOsVersiculos(livro: String, capitulo: Int, testamento: Int){
        val versiculos = when(testamento){
            0 -> bibliaCompletaAntigoTestatmento()
            1 -> bibliaCompletaNovoTestatmento()
            else -> {bibliaCompletaAntigoTestatmento()}
        }
        _listaCapituloVersiculos.value = versiculos.get(livro)?.value?.get(capitulo)?.capitulo!!
    }

    //LEITURA DO ARQUIVO JSON
    fun CarregarAlgunsLivros(context: Context){
        println("####### INICIO - CARREGANDO LIVROS ORIGINAL #######")
        for ((index, valor) in listaComOsLivros().withIndex()){
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
        println("####### FIM - CARREGANDO LIVROS ORIGINAL #######")
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

    //TODO: ACHO QUE SERIA MELHOR UTILIZAR UM HASHMAP PARA FAZER O MAPEAMENTO
    //TODO:COMO ESSE METODO SERA UTILIZADO APENAS PARA APARECER NOS BOTOES, BASTARIA APENAS O NOME DO LIVRO
    fun bibliaCompletaAntigoTestatmento(): HashMap<String, StateFlow<List<BibliaDTO>>> {
        val antigoTestamento : HashMap<String,StateFlow<List<BibliaDTO>>> = HashMap()

        antigoTestamento.put("Gênesis", genesis)
        antigoTestamento.put("Êxodo", exodo)
        antigoTestamento.put("Levitico", levitico)
        antigoTestamento.put("Números", numeros)
        antigoTestamento.put("Deuteronomio", deuteronomio)
        antigoTestamento.put("Josue", josue)
        antigoTestamento.put("Juizes", juizes)
        antigoTestamento.put("Rute", rute)
        antigoTestamento.put("I Samuel", umSamuel)

        antigoTestamento.put("II Samuel", doisSamuel)
        antigoTestamento.put("I Reis", umReis)
        antigoTestamento.put("II Reis", doisReis)
        antigoTestamento.put("I Crônicas", umCronicas)
        antigoTestamento.put("II Crônicas", doisCronicas)
        antigoTestamento.put("Esdras", esdras)
        antigoTestamento.put("Neemias", neemias)
        antigoTestamento.put("Ester", ester)
        antigoTestamento.put("Jô", jo)
        antigoTestamento.put("Salmos", salmos)

        antigoTestamento.put("Provérbios", proverbios)
        antigoTestamento.put("Eclesiastes", eclesiastes)
        antigoTestamento.put("Cânticos", canticos)
        antigoTestamento.put("Isaias", isaias)
        antigoTestamento.put("Jeremias", jeremias)
        antigoTestamento.put("Lamentações de Jeremias", lamentacoesDeJeremias)
        antigoTestamento.put("Ezequiel", ezequiel)
        antigoTestamento.put("Daniel", daniel)
        antigoTestamento.put("Oséias", oseias)
        antigoTestamento.put("Joel", joel)

        antigoTestamento.put("Amos", amos)
        antigoTestamento.put("Obadias", obadias)
        antigoTestamento.put("Jonas", jonas)
        antigoTestamento.put("Miquéias", miqueias)
        antigoTestamento.put("Naum", naum)
        antigoTestamento.put("Habacuque", habacuque)
        antigoTestamento.put("Sofonias", sofonias)
        antigoTestamento.put("Ageu", ageu)
        antigoTestamento.put("Malaquias", malaquias)
        antigoTestamento.put("Zacarias", zacarias)

        return antigoTestamento
    }

    fun bibliaCompletaNovoTestatmento(): HashMap<String, StateFlow<List<BibliaDTO>>> {

        val novoTestamento : HashMap<String,StateFlow<List<BibliaDTO>>> = HashMap()

        novoTestamento.put("Mateus", mateus)
        novoTestamento.put("Marcos", marcos)
        novoTestamento.put("Lucas", lucas)
        novoTestamento.put("João", joao)
        novoTestamento.put("Atos", atos)
        novoTestamento.put("Romanos", romanos)
        novoTestamento.put("I Coríntios", umCorintios)
        novoTestamento.put("II Coríntios", doisCorintios)
        novoTestamento.put("Galatas", galatas)
        novoTestamento.put("Efésios", efesios)

        novoTestamento.put("Filipenses", filipenses)
        novoTestamento.put("Colossenses", colossenses)
        novoTestamento.put("I Tessalonicenses", umTessalonicenses)
        novoTestamento.put("II Tessalonicenses", doisTessalonicenses)
        novoTestamento.put("I Timóteo", umTimoteo)
        novoTestamento.put("II Timóteo", doisTimoteo)
        novoTestamento.put("Tito", tito)
        novoTestamento.put("Filemom", filemom)
        novoTestamento.put("Hebreus", hebreus)
        novoTestamento.put("Tiago", tiago)

        novoTestamento.put("I Pedro", umPedro)
        novoTestamento.put("II Pedro", doisPedro)
        novoTestamento.put("I João", umJoao)
        novoTestamento.put("II João", doisJoao)
        novoTestamento.put("III João", tresJoao)
        novoTestamento.put("Judas", judas)
        novoTestamento.put("Apocalipse", apocalipse)

        return novoTestamento
    }


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
    private var _Atos = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val atos : StateFlow<List<BibliaDTO>> = _Atos
    private var _Canticos = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val canticos : StateFlow<List<BibliaDTO>> = _Canticos
    private var _Colossenses = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val colossenses : StateFlow<List<BibliaDTO>> = _Colossenses
    private var _Daniel = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val daniel : StateFlow<List<BibliaDTO>> = _Daniel
    private var _Deuteronomio = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val deuteronomio : StateFlow<List<BibliaDTO>> = _Deuteronomio
    private var _Eclesiastes = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val eclesiastes : StateFlow<List<BibliaDTO>> = _Eclesiastes
    private var _Efesios = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val efesios : StateFlow<List<BibliaDTO>> = _Efesios
    private var _Esdras = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val esdras : StateFlow<List<BibliaDTO>> = _Esdras
    private var _Ester = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val ester : StateFlow<List<BibliaDTO>> = _Ester
    private var _Exodo = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val exodo : StateFlow<List<BibliaDTO>> = _Exodo
    private var _Ezequiel = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val ezequiel : StateFlow<List<BibliaDTO>> = _Ezequiel
    private var _Filemom = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val filemom : StateFlow<List<BibliaDTO>> = _Filemom
    private var _Filipenses = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val filipenses : StateFlow<List<BibliaDTO>> = _Filipenses
    private var _Galatas = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val galatas : StateFlow<List<BibliaDTO>> = _Galatas
    private var _Genesis = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val genesis : StateFlow<List<BibliaDTO>> = _Genesis
    private var _Habacuque = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val habacuque : StateFlow<List<BibliaDTO>> = _Habacuque
    private var _Hebreus = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val hebreus : StateFlow<List<BibliaDTO>> = _Hebreus
    private var _Isaias = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val isaias : StateFlow<List<BibliaDTO>> = _Isaias
    private var _jeremias = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val jeremias : StateFlow<List<BibliaDTO>> = _jeremias
    private var _Jo = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val jo : StateFlow<List<BibliaDTO>> = _Jo
    private var _Joao = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val joao : StateFlow<List<BibliaDTO>> = _Joao
    private var _Joel = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val joel : StateFlow<List<BibliaDTO>> = _Joel
    private var _Jonas = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val jonas : StateFlow<List<BibliaDTO>> = _Jonas
    private var _Josue = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val josue : StateFlow<List<BibliaDTO>> = _Josue
    private var _Judas = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val judas : StateFlow<List<BibliaDTO>> = _Judas
    private var _Juizes = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val juizes : StateFlow<List<BibliaDTO>> = _Juizes
    private var _LamentacoesDeJeremias = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val lamentacoesDeJeremias : StateFlow<List<BibliaDTO>> = _LamentacoesDeJeremias
    private var _Levitico = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val levitico : StateFlow<List<BibliaDTO>> = _Levitico
    private var _Lucas = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val lucas : StateFlow<List<BibliaDTO>> = _Lucas
    private var _Malaquias = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val malaquias : StateFlow<List<BibliaDTO>> = _Malaquias
    private var _Marcos = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val marcos : StateFlow<List<BibliaDTO>> = _Marcos
    private var _Mateus = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val mateus : StateFlow<List<BibliaDTO>> = _Mateus
    private var _Miqueias = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val miqueias : StateFlow<List<BibliaDTO>> = _Miqueias
    private var _Naum = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val naum : StateFlow<List<BibliaDTO>> = _Naum
    private var _Neemias = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val neemias : StateFlow<List<BibliaDTO>> = _Neemias
    private var _Numeros = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val numeros : StateFlow<List<BibliaDTO>> = _Numeros
    private var _Obadias = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val obadias : StateFlow<List<BibliaDTO>> = _Obadias
    private var _Oseias = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val oseias : StateFlow<List<BibliaDTO>> = _Oseias
    private var _Proverbios = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val proverbios : StateFlow<List<BibliaDTO>> = _Proverbios
    private var _Romanos = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val romanos : StateFlow<List<BibliaDTO>> = _Romanos
    private var _Rute = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val rute : StateFlow<List<BibliaDTO>> = _Rute
    private var _Salmos = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val salmos : StateFlow<List<BibliaDTO>> = _Salmos
    private var _Sofonias = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val sofonias : StateFlow<List<BibliaDTO>> = _Sofonias
    private var _Tiago = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val tiago : StateFlow<List<BibliaDTO>> = _Tiago
    private var _Tito = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val tito : StateFlow<List<BibliaDTO>> = _Tito
    private var _Zacarias = MutableStateFlow<List<BibliaDTO>>(emptyList())
    val zacarias : StateFlow<List<BibliaDTO>> = _Zacarias

    fun listaComOsLivros():List<String>{
        val listaDeLivros = listOf(
            "1_corintios.json",
            "1_cronicas.json",
            "1_joao.json",
            "1_pedro.json",
            "1_reis.json",
            "1_samuel.json",
            "1_tessalonicenses.json",
            "1_timoteo.json",
            "2_corintios.json",
            "2_cronicas.json",
            "2_joao.json",
            "2_pedro.json",
            "2_reis.json",
            "2_samuel.json",
            "2_tessalonicenses.json",
            "2_timoteo.json",
            "3_joao.json",
            "ageu.json",
            "amos.json",
            "apocalipse.json",
            "atos.json",
            "canticos.json",
            "colossenses.json",
            "daniel.json",
            "deuteronomio.json",
            "eclesiastes.json",
            "efesios.json",
            "esdras.json",
            "ester.json",
            "exodo.json",
            "ezequiel.json",
            "filemom.json",
            "filipenses.json",
            "galatas.json",
            "genesis.json",
            "habacuque.json",
            "hebreus.json",
            "isaias.json",
            "jeremias.json",
            "jo.json",
            "joao.json",
            "joel.json",
            "jonas.json",
            "josue.json",
            "judas.json",
            "juizes.json",
            "lamentacoes_de_jeremias.json",
            "levitico.json",
            "lucas.json",
            "malaquias.json",
            "marcos.json",
            "mateus.json",
            "miqueias.json",
            "naum.json",
            "neemias.json",
            "numeros.json",
            "obadias.json",
            "oseias.json",
            "proverbios.json",
            "romanos.json",
            "rute.json",
            "salmos.json",
            "sofonias.json",
            "tiago.json",
            "tito.json",
            "zacarias.json"
        )
        return listaDeLivros
    }

    fun livrosParaPreencher():List<MutableStateFlow<List<BibliaDTO>>>{
        val livrosParaPreencher = listOf(
            _UmCorintios,
            _UmCronicas,
            _UmJoao,
            _UmPedro,
            _UmReis,
            _UmSamuel,
            _UmTessalonicenses,
            _UmTimoteo,
            _DoisCorintios,
            _DoisCronicas,
            _DoisJoao,
            _DoisPedro,
            _DoisReis,
            _DoisSamuel,
            _DoisTessalonicenses,
            _DoisTimoteo,
            _TresJoao,
            _Ageu,
            _Amos,
            _Apocalipse,
            _Atos,
            _Canticos,
            _Colossenses,
            _Daniel,
            _Deuteronomio,
            _Eclesiastes,
            _Efesios,
            _Esdras,
            _Ester,
            _Exodo,
            _Ezequiel,
            _Filemom,
            _Filipenses,
            _Galatas,
            _Genesis,
            _Habacuque,
            _Hebreus,
            _Isaias,
            _jeremias,
            _Jo,
            _Joao,
            _Joel,
            _Jonas,
            _Josue,
            _Judas,
            _Juizes,
            _LamentacoesDeJeremias,
            _Levitico,
            _Lucas,
            _Malaquias,
            _Marcos,
            _Mateus,
            _Miqueias,
            _Naum,
            _Neemias,
            _Numeros,
            _Obadias,
            _Oseias,
            _Proverbios,
            _Romanos,
            _Rute,
            _Salmos,
            _Sofonias,
            _Tiago,
            _Tito,
            _Zacarias)

        return livrosParaPreencher
    }


}








