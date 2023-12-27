package com.chunmaru.app101.views.main.nuevapartida

import android.app.Activity
import android.content.Context
import android.speech.tts.TextToSpeech
import android.util.Log
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.ui.platform.LocalContext
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.app101.data.JugadorDataSource
import com.chunmaru.app101.data.PartidaDataSource
import com.chunmaru.app101.data.entity.JugadorEntity
import com.chunmaru.app101.data.entity.PartidaEntity
import com.chunmaru.app101.utils.Resource
import com.google.android.gms.common.api.Response
import com.google.firebase.Firebase
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.auth.auth
import com.google.firebase.firestore.SetOptions
import com.google.firebase.firestore.firestore
import com.google.mlkit.nl.translate.TranslateLanguage
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.async
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.delay
import kotlinx.coroutines.flow.collect
import kotlinx.coroutines.launch
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Locale
import javax.inject.Inject

@HiltViewModel
class NuevaPartidaViewModel @Inject constructor(
    val jugadorDataSource: JugadorDataSource,
    val partidaDS: PartidaDataSource
) : ViewModel() {
    var transition by mutableStateOf(0)
    var state by mutableStateOf(NuevaPartidaState())
        private set
    var responseJugadores by mutableStateOf<Resource<List<JugadorEntity>>?>(null)
    var responsePartida by mutableStateOf<Resource<List<PartidaEntity>>?>(null)
    var partidaState by mutableStateOf<PartidaEntity?>(null)
    var errorMessage = ""
    var showAlert by mutableStateOf(false)
    var showInfo by mutableStateOf(false)
    var stateJugadores = mutableStateListOf<JugadorEntity>()
    var listJug1 = mutableStateListOf<String>("0")
    var listJug2 = mutableStateListOf<String>("0")
    var listJug3 = mutableStateListOf<String>("0")
    var listJug4 = mutableStateListOf<String>("0")
    var jugadoresEliminados = mutableStateListOf<JugadorEntity>()

    //firebase
    private val auth: FirebaseAuth = Firebase.auth
    private val firestore = Firebase.firestore

    fun onValueNumJug(value: String) {
        state = state.copy(numJugadores = value)
    }

    fun onValueJug1(value: String) {
        state = state.copy(jugador1 = value)
    }

    fun onValueJug2(value: String) {
        state = state.copy(jugador2 = value)
    }

    fun onValueJug3(value: String) {
        state = state.copy(jugador3 = value)
    }

    fun onValueJug4(value: String) {
        state = state.copy(jugador4 = value)
    }

    fun onValueApuesta(value: String) {
        state = state.copy(apuesta = value)
    }

    fun onValuePunt1(value: String) {
        state = state.copy(punt1 = value)
    }

    fun onValuePunt2(value: String) {
        state = state.copy(punt2 = value)
    }

    fun onValuePunt3(value: String) {
        state = state.copy(punt3 = value)
    }

    fun onValuePunt4(value: String) {
        state = state.copy(punt4 = value)
    }

    init {

        updateReturnGame()
        //if (transition < 3) getJugadores()
    }



    fun getJugadores(fk:Long) = viewModelScope.launch() {
        responseJugadores = Resource.Loading
        jugadorDataSource.getJugadoresByPk(fk).collect() {
            responseJugadores = it
            Log.d("getJugadores", "${stateJugadores.size}")
        }
    }

    fun updateReturnGame()=viewModelScope.launch {
    //    transition = 3
        responsePartida = Resource.Loading
        partidaDS.getPartida().collect(){
                responsePartida = it
            }
    }

    fun updateApuAndJug(partida:PartidaEntity,fk:Long){
        state = state.copy(apuesta= partida.apuesta.toString())
        partidaState = partida
        getJugadores(fk)
        Log.d("partida","$partidaState")
    }


    fun validateNumJug():Boolean{
        if (state.numJugadores == ""){
            errorMessage = "ingrese un numero"
            return false
        }else if (state.numJugadores.toIntOrNull() == null) {
            errorMessage = "ingrese un numero valido"
            return false
        }else if (state.numJugadores.toInt()>4 || state.numJugadores.toInt() < 2){
            errorMessage = "ingrese un numero entre 2-4"
            return false
        }
        return true
    }

    fun Any.toNull() = this ?: null
    fun validateForm(): Boolean {
        if (!validateNumJug()) {
            errorMessage = "ingrese un numero entre 2 y 4"
            return false
        } else if (state.numJugadores == "2" && (state.jugador1.equals("") || state.jugador2.equals(""))) {
            errorMessage = "rellene todos los campos"
            return false
        } else if (state.numJugadores == "3" && (state.jugador1.equals("") || state.jugador2.equals("")
                    || state.jugador3.equals("") )){
            errorMessage = "rellene todos los campos"
            return false

        } else if (state.numJugadores == "4" && (state.jugador1.equals("") || state.jugador2.equals("")
                    || state.jugador3.equals("") || state.jugador4.equals(""))){
            errorMessage = "rellene todos los campos"
            return false
        } else if (state.apuesta.equals("")){
            errorMessage = "rellene todos los campos"
            return false
        } else if(state.apuesta.toIntOrNull() == null){
            errorMessage = "ingrese un numero en la casilla apuesta"
            return false
        }
        return true
    }

    fun iniciarPartida() = viewModelScope.launch(Dispatchers.IO) {
        partidaState = PartidaEntity(numJug = state.numJugadores.toInt(), apuesta = state.apuesta.toInt())
        val pkPart:Long? = partidaDS.insertPartida(
            partidaState!!
        )
        partidaState = partidaState!!.copy(pk= pkPart!!)
        for (i in 1..state.numJugadores.toInt()) {
            if (i == 1) {
                stateJugadores.add(
                    JugadorEntity(
                        id = "0", name = state.jugador1,
                        deuda = state.apuesta.toInt(), partida_pk = pkPart ?:5
                    )
                )
            }

            if (i == 2) {
                stateJugadores.add(
                    JugadorEntity(
                        id = "1",
                        name = state.jugador2,
                        deuda = state.apuesta.toInt(),
                        partida_pk = pkPart ?:5
                    )
                )
            }
            if (i == 3) {
                stateJugadores.add(
                    JugadorEntity(
                        id = "2",
                        name = state.jugador3,
                        deuda = state.apuesta.toInt(),
                        partida_pk = pkPart ?:5
                    )
                )
            }

            if (i == 4) {
                stateJugadores.add(
                    JugadorEntity(
                        id = "3",
                        name = state.jugador4,
                        deuda = state.apuesta.toInt(),
                        partida_pk = pkPart ?:5
                    )
                )
            }
        }

        jugadorDataSource.insertAll(stateJugadores)
        Log.d("inic", stateJugadores.size.toString())
        transition = 3
    }

    suspend fun algo(): Long {
        delay(2000)
        return 2500
    }


    //accion cuando registra nueva puntuacion de los jugadores en 1 ronda
    fun RegistrarPuntos() = viewModelScope.launch {
        listJug1.add(state.punt1)
        listJug2.add(state.punt2)
        listJug3.add(state.punt3)
        listJug4.add(state.punt4)

        suma(stateJugadores.size.toString())

        regularizarSobrepaso(0, stateJugadores.get(0).puntaje)
        regularizarSobrepaso(1, stateJugadores.get(1).puntaje)

        if (stateJugadores.size > 2) regularizarSobrepaso(2, stateJugadores.get(2).puntaje)
        if (stateJugadores.size > 3) regularizarSobrepaso(3, stateJugadores.get(3).puntaje)

        stateJugadores.map { jug ->
            jugadorDataSource.update(
                jug.id,
                jug.name,
                jug.puntaje,
                jug.numElim,
                jug.deuda,
                jug.estado
            )
        }
    }

    //suma de todos los puntajes del jugador
    private fun suma(nJug: String) {
        if (nJug.equals("1") || nJug.equals("2") || nJug.equals("3") || nJug.equals("4")) {
            val aux = stateJugadores.get(0)
            stateJugadores.set(0, aux.copy(puntaje = aux.puntaje + state.punt1.toInt()))
        }
        if (nJug.equals("2") || nJug.equals("3") || nJug.equals("4")) {
            val aux = stateJugadores.get(1)
            stateJugadores.set(1, aux.copy(puntaje = aux.puntaje + state.punt2.toInt()))
        }
        if (nJug.equals("3") || nJug.equals("4")) {
            val aux = stateJugadores.get(2)
            stateJugadores.set(2, aux.copy(puntaje = aux.puntaje + state.punt3.toInt()))
        }
        if (nJug.equals("4")) {
            val aux = stateJugadores.get(3)
            stateJugadores.set(3, aux.copy(puntaje = aux.puntaje + state.punt4.toInt()))
        }
    }

    //accion si un jugador sobrepasa 101
    fun regularizarSobrepaso(jug: Int, ptj: Int) {
        if (ptj > 100) {
            val aux = stateJugadores.get(jug)
            stateJugadores.set(
                jug,
                aux.copy(
                    puntaje = 0,
                    numElim = aux.numElim + 1,
                    deuda = (aux.deuda + state.apuesta.toInt()),
                    estado = false
                )
            )
            jugadoresEliminados.add(aux)
        }
    }


    //show alert para eliminar del juego totalmente a un jugador
    fun AceptarShowAlertElim() = viewModelScope.launch {
        jugadoresEliminados.map { jug ->
            if (stateJugadores.get(jug.id.toInt()).estado == false) {
                regularizarEliminado(jug.id.toInt(), jug.puntaje)
            } else {
                regularizarSpartanos(jug.id.toInt(), jug.puntaje)
            }
        }
        jugadoresEliminados.clear()
        stateJugadores.map { jug ->
            jugadorDataSource.update(
                jug.id,
                jug.name,
                jug.puntaje,
                jug.numElim,
                jug.deuda,
                jug.estado
            )
        }
    }

    //accion para eliminar del juego totalmente a un jugador
    fun regularizarEliminado(jug: Int, ptj: Int) {
        val aux = stateJugadores.get(jug)
        stateJugadores.set(
            jug,
            aux.copy(
                //puntaje = 0,
                numElim = aux.numElim,
                deuda = (aux.deuda - state.apuesta.toInt()),
                estado = false
            )
        )
        when (jug) {
            0 -> {
                state = state.copy(punt1 = "0")
            }

            1 -> {
                state = state.copy(punt2 = "0")
            }

            2 -> {
                state = state.copy(punt3 = "0")
            }

            3 -> {
                state = state.copy(punt4 = "0")
            }
        }
    }

    fun regularizarSpartanos(jug: Int, ptj: Int) {
        val aux = stateJugadores.get(jug)
        stateJugadores.set(
            jug, aux.copy(puntaje = mayorPtj())
        )
    }

    fun mayorPtj(): Int {
        var may = 0
        stateJugadores.map { jug ->
            if (jug.estado) {
                if (jug.puntaje > may) may = jug.puntaje
            }
        }
        return may
    }

    fun end() = viewModelScope.launch(Dispatchers.IO) {
        partidaDS.updatePartida(partidaState!!.pk,false,partidaState!!.ganador)
        partidaState = partidaState!!.copy(estado = false )
        state = state.copy(
            numJugadores = "4",
            jugador1 = "",
            jugador2 = "",
            jugador3 = "",
            jugador4 = "",
            apuesta = "3",
            punt1 = "0",
            punt2 = "0",
            punt3 = "0",
            punt4 = "0"
        )
        transition = 0
        stateJugadores.clear()
        listJug1.clear()
        listJug2.clear()
        listJug3.clear()
        listJug4.clear()
    }

    fun bntMantenerApuesta(isAcep:Boolean)=viewModelScope.launch(Dispatchers.IO){
        jugadoresEliminados.map {
            if (isAcep){
                todosElim(it.id.toInt(),true,-partidaState!!.apuesta)
            }else{
                todosElim(it.id.toInt(),true,0)
            }
        }
        jugadoresEliminados.clear()

        stateJugadores.map { jug ->
            jugadorDataSource.update(
                jug.id,
                jug.name,
                jug.puntaje,
                jug.numElim,
                jug.deuda,
                jug.estado
            )
        }

    }

    fun todosElim(jug: Int,estado:Boolean, apuesta:Int) {
        val aux = stateJugadores.get(jug)
        stateJugadores.set(
            jug, aux.copy(puntaje = 0, estado = estado, deuda = aux.deuda+(apuesta))
        )
    }

    fun definirGanador()=viewModelScope.launch{
        val ganador = stateJugadores.filter { it.estado==true }
        if (!ganador.isNullOrEmpty()){
            partidaDS.updatePartida(partidaState!!.pk,partidaState!!.estado, ganador = ganador.get(0).name)
        }
    }


    fun saveNewPartidaFirebase(onSuccess: () -> Unit) {
        val email = auth.currentUser?.email
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val newPartida = partidaState.let {
                        hashMapOf(
                            "estado" to partidaState!!.estado,
                            "numJug" to partidaState!!.numJug,
                            "apuesta" to partidaState!!.apuesta,
                            "ganador" to partidaState!!.ganador,
                            "email" to email,
                            "date" to formatDate()
                        )
                }
                val uiDoc =  partidaState!!.pk.toString()+"-$email"

                firestore.collection("partida").document(uiDoc)
                    .set(newPartida, SetOptions.merge())
                    .addOnSuccessListener {
                        stateJugadores.map {
                            firestore.collection("partida").document(uiDoc)
                                .collection("jugador").document(it.id).set(it).addOnSuccessListener {

                                }
                        }
                        onSuccess()
                    }

            } catch (e: Exception) {
                Log.d("error NotesViewModel", "saveNewNote: ${e.localizedMessage}")
            }
        }
    }


    private var textToSpeech: TextToSpeech? = null

    fun textToSpeech(context: Context){

        var speek = "La puntuación es la siguiente: "
        stateJugadores.map { speek = speek+" ${it.name} tiene ${it.puntaje}. " }

        textToSpeech = TextToSpeech(
            context
        ){
            if (it == TextToSpeech.SUCCESS){
                textToSpeech?.let { txtToSpeech ->
                    txtToSpeech.language = Locale.ROOT
                    txtToSpeech.setSpeechRate(1.0f)
                    txtToSpeech.speak(
                        speek,
                        TextToSpeech.QUEUE_ADD,
                        null,
                        null
                    )
                }
            }
        }
    }

    private fun formatDate(): String {
        val currentDate = Calendar.getInstance().time
        val res = SimpleDateFormat("dd/MM/yyyy", Locale.getDefault())
        return res.format(currentDate)
    }

}


