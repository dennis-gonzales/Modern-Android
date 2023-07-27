package com.dnnsgnzls.modern.framework.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dnnsgnzls.modern.data.db.GameEntity
import com.dnnsgnzls.modern.framework.constants.Constants.GAME_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface GameDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(gameEntity: GameEntity)

    @Insert
    suspend fun insertAll(heroList: List<GameEntity>)

    @Query("SELECT * FROM $GAME_TABLE WHERE id=:gameId")
    fun get(gameId: Long): Flow<GameEntity?>

    @Query("SELECT * FROM $GAME_TABLE ORDER BY id ASC")
    fun getAll(): Flow<List<GameEntity>>

    @Update
    suspend fun update(gameEntity: GameEntity)

    @Delete
    suspend fun delete(gameEntity: GameEntity)
}