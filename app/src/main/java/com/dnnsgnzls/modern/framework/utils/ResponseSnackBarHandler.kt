package com.dnnsgnzls.modern.framework.utils

import kotlinx.coroutines.flow.MutableSharedFlow

class ResponseSnackBarHandler(
    private val snackBarMessages: MutableSharedFlow<SnackbarMessage>
) {
    suspend fun handleResponse(response: Response<*>, successMessage: String) {
        when (response) {
            is Response.Success -> snackBarMessages.emit(
                SnackbarMessage.Success(successMessage)
            )

            is Response.Error -> snackBarMessages.emit(
                SnackbarMessage.Error(
                    response.exception.message ?: "Unknown error"
                )
            )

            is Response.Loading -> {} // ignore
        }
    }
}
