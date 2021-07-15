package com.ecirstea.creepyrabbit.network

import com.ecirstea.creepyrabbit.data.model.UserCredentials
import com.ecirstea.creepyrabbit.data.model.UserToken
import retrofit2.Call
import retrofit2.http.*

interface ApiInterface {
    @POST("authenticate")
    fun createPost(@Body postModel: UserCredentials):Call<UserToken>
}