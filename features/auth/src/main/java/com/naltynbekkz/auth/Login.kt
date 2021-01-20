package com.naltynbekkz.auth

import androidx.annotation.Keep
import com.google.gson.annotations.SerializedName

@Keep
data class Login(
    @SerializedName("email")
    var email: String,
    @SerializedName("password")
    var password: String,
)