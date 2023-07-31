package com.dnnsgnzls.modern.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dnnsgnzls.modern.domain.model.Review
import com.dnnsgnzls.modern.framework.constants.Constants.REVIEW_TABLE

@Entity(tableName = REVIEW_TABLE)
data class ReviewEntity(
    @ColumnInfo("game_id")
    val gameId: Long,

    val title: String,

    val text: String,

    @PrimaryKey(autoGenerate = true)
    val id: Long = 0,
) {
    companion object {
        fun fromDomainModel(review: Review): ReviewEntity {
            return ReviewEntity(
                gameId = review.gameId,
                title = review.title,
                text = review.text,
                id = review.id
            )
        }
    }

    fun toDomainModel(): Review {
        return Review(
            gameId = this.gameId,
            title = this.title,
            text = this.text,
            id = id
        )
    }
}