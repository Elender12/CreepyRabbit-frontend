package com.ecirstea.creepyrabbit.data

import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ecirstea.creepyrabbit.data.model.LoggedInUser
import com.ecirstea.creepyrabbit.data.model.UserCredentials
import com.ecirstea.creepyrabbit.data.model.UserToken
import com.ecirstea.creepyrabbit.network.ApiClient
import com.ecirstea.creepyrabbit.network.ApiInterface
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
    private var apiInterface: ApiInterface?=null

    init {
        apiInterface = ApiClient.getApiClient().create(ApiInterface::class.java)
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

    fun authenticateUser(userCredentials: UserCredentials): MutableLiveData<UserToken?> {
        val data = MutableLiveData<UserToken?>()
        Log.d(TAG, "authenticateUser: +")
        apiInterface?.createPost(userCredentials)?.enqueue(object : Callback<UserToken> {
            override fun onFailure(call: Call<UserToken>, t: Throwable) {
                Log.d(TAG, "onFailure: ?")
                data.value = null
            }

            override fun onResponse(call: Call<UserToken>, response: Response<UserToken>) {
                val res = response.body()
                Log.d(TAG, "onResponse: before if")
                if (response.code() == 201 && res!=null){
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