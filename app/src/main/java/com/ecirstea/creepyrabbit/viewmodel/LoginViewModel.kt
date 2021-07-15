package com.ecirstea.creepyrabbit.viewmodel

import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import com.ecirstea.creepyrabbit.data.LoginRepository
import com.ecirstea.creepyrabbit.data.model.UserCredentials
import com.ecirstea.creepyrabbit.data.model.UserToken

class LoginViewModel {
    private var loginRepository: LoginRepository?=null
    var userCredentialsListLiveData : LiveData<List<UserCredentials>>?=null
    var authenticateUserLiveData:LiveData<UserToken?>?=null
    var deletePostLiveData:LiveData<Boolean>?=null

    init {
        loginRepository = LoginRepository()
        userCredentialsListLiveData = MutableLiveData()
        authenticateUserLiveData = MutableLiveData()
        deletePostLiveData = MutableLiveData()
    }

   /* fun fetchAllPosts(){
        postModelListLiveData = homeRepository?.fetchAllPosts()
    }
*/
    fun authenticateUser(userCredentials: UserCredentials){
       authenticateUserLiveData = loginRepository?.authenticateUser(userCredentials)
    }

  /*  fun deletePost(id:Int){
        deletePostLiveData = loginRepository?.deletePost(id)
    }*/

}