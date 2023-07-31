package com.dnnsgnzls.modern.framework.persistence

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dnnsgnzls.modern.data.db.GameEntity
import com.dnnsgnzls.modern.data.db.ReviewEntity
import com.dnnsgnzls.modern.framework.constants.Constants.DATABASE_NAME

@Database(
    entities = [GameEntity::class, ReviewEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun gameDao(): GameDao

    abstract fun reviewDao(): ReviewDao

    /// <- The database is being instantiated with the help of Hilt. ->
    /// <- See `RepositoryModule.kt` file and look for `provideDatabaseService` ->

    companion object {
        @Volatile
        private var instance: DatabaseService? = null


        operator fun invoke(context: Context): DatabaseService = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DatabaseService::class.java,
            DATABASE_NAME
        ).fallbackToDestructiveMigration().build()
    }
}