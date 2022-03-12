package com.example.techastral_loc

import retrofit2.Call
import retrofit2.http.Body
import retrofit2.http.GET
import retrofit2.http.POST

interface RetrofitApi {

    @POST("auth/login/")
    fun login(
        @Body params: logData
    ): Call<logData>

    @POST("auth/register/")
    fun register(
        @Body params: registerData
    ):Call<registerData>

}