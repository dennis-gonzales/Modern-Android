package com.dnnsgnzls.modern.framework.persistence

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import androidx.room.Update
import com.dnnsgnzls.modern.data.db.ReviewEntity
import com.dnnsgnzls.modern.framework.constants.Constants.REVIEW_TABLE
import kotlinx.coroutines.flow.Flow

@Dao
interface ReviewDao {
    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insert(reviewEntity: ReviewEntity)

    @Insert
    suspend fun insertAll(reviewList: List<ReviewEntity>)

    @Query("SELECT * FROM $REVIEW_TABLE WHERE id=:reviewId")
    fun get(reviewId: Long): Flow<ReviewEntity?>

    @Query("SELECT * FROM $REVIEW_TABLE ORDER BY id DESC")
    fun getAll(): Flow<List<ReviewEntity>>

    @Query("SELECT * FROM $REVIEW_TABLE WHERE game_id=:gameId ORDER BY id DESC")
    fun getAllByGameId(gameId: Long): Flow<List<ReviewEntity>>

    @Update
    suspend fun update(reviewEntity: ReviewEntity)

    @Delete
    suspend fun delete(reviewEntity: ReviewEntity)

    @Query("DELETE FROM $REVIEW_TABLE WHERE game_id = :gameId")
    suspend fun deleteAllByGameId(gameId: Long)
}