package com.chunmaru.app101.views.main.profile.detail

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.app101.utils.ComposeFileProvider
import com.chunmaru.app101.utils.ResultingActivityHandler
import com.google.firebase.Firebase
import com.google.firebase.auth.auth
import com.google.firebase.firestore.firestore
import com.google.firebase.storage.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileDetailViewModel @Inject constructor(
    @ApplicationContext val context: Context
) : ViewModel() {
    val auth = Firebase.auth
    val firestore = Firebase.firestore
    val storage = Firebase.storage

    var stateProfile by mutableStateOf(ProfileDetailState())
    val resultingActivityHandler = ResultingActivityHandler()
    var file: File? = null

    init {
        getUser()
    }
    fun logout() {
        auth.signOut()
    }


    fun getUser() = viewModelScope.launch {
        val email = auth.currentUser?.email
        firestore.collection("Users").whereEqualTo("email", email!!)
            .addSnapshotListener() { querySnapshot, error ->
                if (error != null) {
                    return@addSnapshotListener
                }
                //val documents = PartidaResponse()
                if (querySnapshot != null) {
                    for (document in querySnapshot) {
                        val myDocument = document.toObject(ProfileDetailState::class.java)
                       // documents.add(myDocument)
                        stateProfile = myDocument
                    }
                }
                //partidaResponse = Resource.Success(documents)

            }
    }
}