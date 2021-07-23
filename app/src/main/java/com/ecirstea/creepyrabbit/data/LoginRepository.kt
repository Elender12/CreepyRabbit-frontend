package com.ecirstea.creepyrabbit.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.liveData
import com.ecirstea.api.UsersApi
import com.ecirstea.api.model.JwtRequest
import com.ecirstea.api.model.JwtResponse
import com.ecirstea.creepyrabbit.data.model.LoggedInUser
import com.ecirstea.creepyrabbit.data.model.UserCredentials
import com.ecirstea.creepyrabbit.network.CRApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import com.ecirstea.creepyrabbit.data.Result
import com.ecirstea.creepyrabbit.data.Result.*
import kotlinx.coroutines.Dispatchers


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */
private const val TAG = "LoginRepository"
class LoginRepository(val dataSource: LoginDataSource) {

/*
    private var apiInterface: UsersApi?=null

    init {
        apiInterface = CRApiClient.client.createService(UsersApi::class.java)
    } */

    // in-memory cache of the loggedInUser object
 var user: LoggedInUser? = null
        private set

    val isLoggedIn: Boolean
        get() = user != null

    init {
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
        user = null
    }

    fun logout() {
        user = null
        //dataSource.logout()
    }

  //  suspend fun login(username: String, password: String) = apiInterface?.authenticate(JwtRequest().username(username).password(password))

    fun login (username: String, password: String): LiveData<Call<JwtResponse>?> {
        val result = dataSource.login(username, password)
        val firstTodo = liveData(Dispatchers.IO) {
            val retrivedTodo = dataSource.login(username, password)


            emit(retrivedTodo)
        }
        if (result is Success<*>) {
            setLoggedInUser(result.data as LoggedInUser)
        }

        Log.d(TAG, "login: no se.."+firstTodo.value)

        return firstTodo
    }



/*
    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        //val result = dataSource.login(username, password)
        val result = MutableLiveData<JwtResponse?>()
        apiInterface?.authenticate(JwtRequest().username(username).password(password))?.enqueue(object : Callback<JwtResponse> {
            override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ?")

                result.value = null
            }
            override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                val res = response.body()

                Log.d(TAG, "onResponse: before if"+ res)

                if (response.code() == 200 && res!=null){
                    result.value = res
                    CRApiClient.client.setBearerToken(result.value.toString())
                    Log.d(TAG, "onResponse: $res")
                }else{
                    result.value = null
                }
            }
        })

        if (result is Result.Success<*>) {
            setLoggedInUser(LoggedInUser(result.data.toString()))
        }

        val name= result.value.toString()
        Log.d(TAG, "login: name es: "+name)
      return Result.Success(LoggedInUser(name))
    }
*/



    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }
}




