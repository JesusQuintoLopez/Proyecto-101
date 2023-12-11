package com.chunmaru.app101.views.login

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.app101.utils.Resource
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

class LoginViewModel @Inject constructor() : ViewModel() {

    var state by mutableStateOf(LoginState())
        private set

    private val auth = Firebase.auth
    var showAlert by mutableStateOf(false)

    var errorMessage = ""

    var isRegister by mutableStateOf<Resource<Boolean>>(Resource.Loading)

    fun onValueEmail(value: String) {
        state = state.copy(email = value)
    }

    fun onValuePassword(value: String) {
        state = state.copy(password = value)
    }

    init {
        aux()
    }

    fun aux()=viewModelScope.launch(){
        if(!auth.currentUser?.email.isNullOrEmpty()){
            isRegister = Resource.Success(true)
        }else{
            isRegister = Resource.Success(false)
        }
    }

    fun login(email: String, password: String, onSucces: () -> Unit) = viewModelScope.launch {
        try {
            if (validateForm()){
                auth.signInWithEmailAndPassword(email, password).addOnCompleteListener { task ->
                    if (task.isSuccessful) {

                        onSucces()
                    } else {
                        //email y/o password incorrecto
                    }
                }.addOnFailureListener {e->
                    errorMessage = "${e.localizedMessage}"
                    showAlert = true
                }
            }else{
                showAlert = true
            }
        } catch (e: Exception) {
            Log.d("error en la app", "${e.localizedMessage}")
        }
    }

    fun validateForm(): Boolean {
        if (state.email.equals("")) {
            errorMessage = "Ingrese un Correo"
            return false
        } else if (state.password.equals("")) {
            errorMessage = "Ingrese una contraseña"
            return false
        } else if (!Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            errorMessage = "Ingrese un correo valido"
            return false
        }
        return true
    }
}