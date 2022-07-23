package com.example.retrofit.retrofit.interfase

import com.example.retrofit.retrofit.models.UserModel
import retrofit2.Call
import retrofit2.http.GET

interface ApiInterfase {
    @GET("users")
    fun getUser(): Call<List<UserModel>>
}