package com.chunmaru.app101.views.main.profile.update

import android.content.Context
import android.net.Uri
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.chunmaru.app101.model.UserModel
import com.chunmaru.app101.utils.ComposeFileProvider
import com.chunmaru.app101.utils.ResultingActivityHandler
import com.chunmaru.app101.views.main.profile.detail.ProfileDetailState
import com.google.firebase.Firebase
import com.google.firebase.storage.storage
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileUpdateViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    @ApplicationContext private val context:Context
) :ViewModel() {
    val param = savedStateHandle.get<String>("user")
    val storage = Firebase.storage
    var user = UserModel.FromJson(param!!)

    var stateProfile by mutableStateOf(ProfileDetailState())
    val resultingActivityHandler = ResultingActivityHandler()
    var file: File? = null
    init {
        Log.d("updateProfile","$param")
        stateProfile = stateProfile.copy(
            name = user.name,
            lastName = user.lastName,
            email = user.email,
            image = user.image
        )
    }

    fun onValueName(value:String){
        stateProfile = stateProfile.copy(name = value)
    }
    fun onValueLastName(value:String){
        stateProfile = stateProfile.copy(lastName =  value)
    }
    fun onValueEmail(value:String){
        stateProfile = stateProfile.copy(email =  value)
    }


    fun onUpdate() {
        if (file != null) { // SI SELECCIONO UNA IMAGEN
            updateWithImage()
        } else {
            Log.d("subido", "no se esta guardando la imagen")
        }
    }

    private fun updateWithImage() {
        // Create a storage reference from our app
        val storageRef = storage.reference
        val mountainsRef = storageRef.child("mountains.jpg")
        val mountainImagesRef = storageRef.child("images/mountains.jpg")
        mountainsRef.name == mountainImagesRef.name // true
        mountainsRef.path == mountainImagesRef.path // false

        var file = Uri.fromFile(file)
        val riversRef = storageRef.child("images/${file.lastPathSegment}")
        val uploadTask = riversRef.putFile(file)

// Register observers to listen for when the download is done or if it fails
        uploadTask.addOnFailureListener {
            Log.d("subido", "no success")
        }.addOnSuccessListener { taskSnapshot ->
            Log.d("subido", "success")
        }

    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*") // URI
        if (result != null) {
            file = ComposeFileProvider.createFileFromUri(context, result)
            stateProfile = stateProfile.copy(image = result.toString())
        }
    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if (result != null) {
            stateProfile = stateProfile.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(stateProfile.image)
        }
    }
}