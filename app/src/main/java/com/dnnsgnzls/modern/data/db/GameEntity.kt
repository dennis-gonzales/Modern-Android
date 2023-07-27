package com.dnnsgnzls.modern.data.db

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.dnnsgnzls.modern.domain.model.Game
import com.dnnsgnzls.modern.framework.constants.Constants.GAME_TABLE

@Entity(tableName = GAME_TABLE)
data class GameEntity(
    @PrimaryKey
    val id: Long,

    val slug: String,

    val name: String,

    val released: String,

    @ColumnInfo("background_image")
    val backgroundImage: String,

    val rating: Double?
) {
    companion object {
        fun fromGame(game: Game): GameEntity {
            return GameEntity(
                id = game.id,
                slug = game.slug,
                name = game.name,
                released = game.released,
                backgroundImage = game.backgroundImage,
                rating = game.rating
            )
        }
    }
}
