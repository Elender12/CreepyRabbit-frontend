package com.ecirstea.creepyrabbit.network

import com.ecirstea.creepyrabbit.data.model.UserCredentials
import com.ecirstea.creepyrabbit.data.model.JwtResponse
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @Headers(
        "accept: application/json",
        "Content-Type: application/json"
    )
    @POST("authenticate")
    fun authenticate(@Body postModel: UserCredentials):Call<JwtResponse>
}