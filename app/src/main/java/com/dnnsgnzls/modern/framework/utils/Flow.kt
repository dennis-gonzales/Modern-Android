package com.dnnsgnzls.modern.framework.utils

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.catch

// <- Extension function for Flow<Response<T>> to handle exceptions. ->
// <- Emits Response.Error for caught Exceptions; re-throws non-Exception Throwables. ->
fun <T> Flow<Response<T>>.catchException(): Flow<Response<T>> = catch { e ->
    if (e is Exception) emit(Response.Error(e))
    else throw e
}
