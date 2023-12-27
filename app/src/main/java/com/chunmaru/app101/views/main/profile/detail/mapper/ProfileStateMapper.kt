package com.chunmaru.app101.views.main.profile.detail.mapper

import com.chunmaru.app101.model.UserModel
import com.chunmaru.app101.views.main.profile.detail.ProfileDetailState

fun ProfileDetailState.ToUserModel(): UserModel {
    return UserModel(
        userId = userId,
        name = name,
        lastName = lastName,
        email = email,
        image = image
    )
}