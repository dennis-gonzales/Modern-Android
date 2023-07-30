package com.dnnsgnzls.modern.framework.utils

import androidx.compose.material3.SnackbarDuration

sealed class UIEvent {
    data class ShowSnackBar(val message: String, val duration: SnackbarDuration) : UIEvent()
    object DismissSnackBar : UIEvent()
}
