package com.dnnsgnzls.modern.framework.utils

sealed class SnackbarMessage {
    data class Success(val message: String) : SnackbarMessage()
    data class Error(val message: String) : SnackbarMessage()
}
