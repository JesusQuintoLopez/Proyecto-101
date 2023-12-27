package com.chunmaru.app101.views.register

import android.util.Log
import android.util.Patterns
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.app101.model.UserModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.FirebaseFirestore
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class RegisterViewModel @Inject constructor() : ViewModel() {
    var state by mutableStateOf(RegisterState())
        private set

    val auth = Firebase.auth

    var errorMessage = ""

    var showAlert by mutableStateOf(false)

    fun onValueName(value: String) {
        state = state.copy(nombres = value)
    }

    fun onValueApellidos(value: String) {
        state = state.copy(apellidos = value)
    }

    fun onValueEmail(value: String) {
        state = state.copy(email = value)
    }

    fun onValuePassword(value: String) {
        state = state.copy(password = value)
    }

    fun onValuePassword2(value: String) {
        state = state.copy(confirmPassword = value)
    }

    fun register(onSucces: () -> Unit) = viewModelScope.launch {
        try {
            auth.createUserWithEmailAndPassword(state.email, state.password)
                .addOnCompleteListener { task ->
                    if (task.isSuccessful) {
                        saveUser()
                        onSucces()
                    } else {
                        //email y/o password incorrecto
                        showAlert = true
                    }
                }
        } catch (e: Exception) {
            Log.d("error en la app", "${e.localizedMessage}")
        }
    }

    private fun saveUser() = viewModelScope.launch(Dispatchers.IO) {
        val id = auth.currentUser?.uid
        val email = auth.currentUser?.email

        val user = UserModel(
            userId = id.toString(),
            name = state.nombres,
            lastName = state.apellidos,
            email = email.toString(),
            image = ""
        )

        FirebaseFirestore.getInstance().collection("Users")
            .add(user)
            .addOnSuccessListener { }
            .addOnFailureListener { }
    }

    fun validate() {
        if (state.email.equals("") || state.nombres.equals("") || state.apellidos.equals("") || state.password.equals("") || state.confirmPassword.equals("")){
            errorMessage = "Por favor complete todos los campos"
            showAlert = true
        }else if (!state.password.equals(state.confirmPassword)){
            errorMessage = "Las contraseñas estan diferentes"
            showAlert = true
        }else if (!Patterns.EMAIL_ADDRESS.matcher(state.email).matches()) {
            errorMessage = "El Email no es valido"
            showAlert = true
        }

    }
}