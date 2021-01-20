package com.naltynbekkz.auth

import retrofit2.Response
import retrofit2.http.Body
import retrofit2.http.POST

interface AuthService {

    /**
     * POST http://192.168.7.175:8080/api/v1/auth/login
     */
    @POST("login")
    suspend fun login(@Body login: Login): Response<Token>

}