package com.chunmaru.app101.views.main.profile

import androidx.lifecycle.ViewModel
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class ProfileViewModel @Inject constructor():ViewModel() {
    val auth = Firebase.auth
    fun logout(){
        auth.signOut()
    }
}