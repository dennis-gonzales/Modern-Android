package com.dnnsgnzls.modern.framework.constants

import com.dnnsgnzls.modern.domain.model.Game

sealed class ButtonType {
    data class Favourites(val onToggleFavourite: (Game) -> Unit) : ButtonType()
    data class ShowReviewForm(val showReviewForm: (Game) -> Unit) : ButtonType()
}