package com.earl.utils.localDataSource

import androidx.room.Database
import androidx.room.RoomDatabase
import com.earl.utils.localDataSource.enteties.UserLoginDb

@Database(
    entities = [
        UserLoginDb::class
    ],
    version = 2,
    exportSchema = false
)
abstract class AppDatabase : RoomDatabase() {

    abstract fun authDao() : UsersDbDao
}