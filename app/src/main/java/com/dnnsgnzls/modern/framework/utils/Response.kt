package com.dnnsgnzls.modern.framework.utils

sealed class Response<out R> {
    data class Success<out T>(val data: T) : Response<T>()
    data class Error(val exception: Exception) : Response<Nothing>()
    object Loading : Response<Nothing>()

    override fun toString(): String {
        return when (this) {
            is Success<*> -> "Success[data=$data]"
            is Error -> "Error[exception=${exception.message}]"
            is Loading -> "Loading"
        }
    }
}

val Response<*>.succeeded
    get() = this is Response.Success && this.data != null
