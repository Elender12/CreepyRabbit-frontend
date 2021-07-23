package com.ecirstea.creepyrabbit.data

import com.ecirstea.api.model.JwtResponse
import okhttp3.Request
import okio.Timeout
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
import java.io.IOException

/**
 * A generic class that holds a value with its loading status.
 * @param <T>
 */
sealed class Result<out T : Any> {

    data class Success<out T : Any>(val data: T) : Result<T>()
    data class Error(val exception: IOException) : Result<Nothing>(), Call<JwtResponse> {
        override fun clone(): Call<JwtResponse> {
            TODO("Not yet implemented")
        }

        override fun execute(): Response<JwtResponse> {
            TODO("Not yet implemented")
        }

        override fun enqueue(callback: Callback<JwtResponse>) {
            TODO("Not yet implemented")
        }

        override fun isExecuted(): Boolean {
            TODO("Not yet implemented")
        }

        override fun cancel() {
            TODO("Not yet implemented")
        }

        override fun isCanceled(): Boolean {
            TODO("Not yet implemented")
        }

        override fun request(): Request {
            TODO("Not yet implemented")
        }

        override fun timeout(): Timeout {
            TODO("Not yet implemented")
        }

    }

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=$exception]"
        }
    }
}