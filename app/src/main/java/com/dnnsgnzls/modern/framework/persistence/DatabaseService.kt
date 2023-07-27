package com.dnnsgnzls.modern.framework.persistence;

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase
import com.dnnsgnzls.modern.data.db.GameEntity
import com.dnnsgnzls.modern.framework.constants.Constants.DB_NAME

@Database(
    entities = [GameEntity::class],
    version = 1,
    exportSchema = false
)
abstract class DatabaseService : RoomDatabase() {

    abstract fun gameDao(): GameDao

    companion object {
        @Volatile
        private var instance: DatabaseService? = null

        operator fun invoke(context: Context): DatabaseService = instance ?: synchronized(this) {
            instance ?: buildDatabase(context).also { instance = it }
        }

        private fun buildDatabase(context: Context) = Room.databaseBuilder(
            context.applicationContext,
            DatabaseService::class.java,
            DB_NAME
        ).fallbackToDestructiveMigration().build()
    }
}