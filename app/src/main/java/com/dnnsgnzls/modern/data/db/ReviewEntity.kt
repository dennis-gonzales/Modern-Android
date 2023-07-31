package com.dnnsgnzls.modern.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.framework.constants.Constants.REVIEW_TABLE

@Entity(tableName = REVIEW_TABLE)
data class ReviewEntity(
    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,

    @ColumnInfo("game_id")
    val gameId: Long,

    val title: String,

    val text: String
) {
    companion object {
        fun fromDomainModel(review: Review): ReviewEntity {
            return ReviewEntity(
                id = review.id,
                gameId = review.gameId,
                title = review.title,
                text = review.text
            )
        }
    }

    fun toDomainModel(): Review {
        return Review(
            gameId = this.gameId,
            title = this.title,
            text = this.text
        )
    }
}