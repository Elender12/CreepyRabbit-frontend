package com.ecirstea.creepyrabbit.data

import android.util.Log
import androidx.lifecycle.MutableLiveData
import com.ecirstea.api.UsersApi
import com.ecirstea.api.model.JwtRequest
import com.ecirstea.api.model.JwtResponse
import com.ecirstea.creepyrabbit.data.model.UserCredentials
import com.ecirstea.creepyrabbit.network.CRApiClient
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


/**
 * Class that requests authentication and user information from the remote data source and
 * maintains an in-memory cache of login status and user credentials information.
 */

//class LoginRepository(val dataSource: LoginDataSource) {
private const val TAG = "LoginRepository"
class LoginRepository() {
    private var apiInterface: UsersApi?=null

    init {
        apiInterface = CRApiClient.client.createService(UsersApi::class.java)
        //CRApiClient.client.setBearerToken()
    }

   /* fun fetchAllPosts():LiveData<List<PostModel>>{
        val data = MutableLiveData<List<PostModel>>()

        apiInterface?.fetchAllPosts()?.enqueue(object : Callback<List<PostModel>>{

            override fun onFailure(call: Call<List<PostModel>>, t: Throwable) {
                data.value = null
            }

            override fun onResponse(
                call: Call<List<PostModel>>,
                response: Response<List<PostModel>>
            ) {

                val res = response.body()
                if (response.code() == 200 &&  res!=null){
                    data.value = res
                }else{
                    data.value = null
                }

            }
        })

        return data

    }*/

    fun authenticateUser(userCredentials: UserCredentials): MutableLiveData<JwtResponse?> {
        val data = MutableLiveData<JwtResponse?>()
        Log.d(TAG, "authenticateUser: +")
       apiInterface?.authenticate(JwtRequest().username(userCredentials.username).password(userCredentials.password))?.enqueue(object : Callback<JwtResponse> {
            override fun onFailure(call: Call<JwtResponse>, t: Throwable) {
                Log.d(TAG, "onFailure: ?")
                data.value = null
            }
            override fun onResponse(call: Call<JwtResponse>, response: Response<JwtResponse>) {
                val res = response.body()

                    Log.d(TAG, "onResponse: before if"+res)

                if (response.code() == 200 && res!=null){
                    data.value = res
                    Log.d(TAG, "onResponse: $res")
                }else{
                    data.value = null
                }
            }
        })

        return data

    }



/*    fun deletePost(id:Int):LiveData<Boolean>{
        val data = MutableLiveData<Boolean>()

        apiInterface?.deletePost(id)?.enqueue(object : Callback<String>{
            override fun onFailure(call: Call<String>, t: Throwable) {
                data.value = false
            }

            override fun onResponse(call: Call<String>, response: Response<String>) {
                data.value = response.code() == 200
            }
        })

        return data

    }*/











    // in-memory cache of the loggedInUser object
   /* var user: LoggedInUser? = null
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
        dataSource.logout()
    }

    fun login(username: String, password: String): Result<LoggedInUser> {
        // handle login
        val result = dataSource.login(username, password)

        if (result is Result.Success) {
            setLoggedInUser(result.data)
        }

        return result
    }

    private fun setLoggedInUser(loggedInUser: LoggedInUser) {
        this.user = loggedInUser
        // If user credentials will be cached in local storage, it is recommended it be encrypted
        // @see https://developer.android.com/training/articles/keystore
    }*/
}




