package com.ecirstea.creepyrabbit.data

import com.ecirstea.api.UsersApi
import com.ecirstea.api.model.JwtRequest
import com.ecirstea.api.model.JwtResponse
import com.ecirstea.creepyrabbit.network.CRApiClient
import retrofit2.Call
import java.io.IOException

/**
 * Class that handles authentication w/ login credentials and retrieves user information.
 */
class LoginDataSource {
    private var apiInterface: UsersApi?=null

    init {
        apiInterface = CRApiClient.client.createService(UsersApi::class.java)
    }
    fun login(username: String, password: String): Call<JwtResponse>? {
        try {

       //   val fakeUser = LoggedInUser(java.util.UUID.randomUUID().toString())
          val result =  apiInterface?.authenticate(JwtRequest().username(username).password(password))




          return result

        } catch (e: Throwable) {
            return Result.Error(IOException("Error logging in", e))
        }
    }

    fun logout() {
        // TODO: revoke authentication
    }
}