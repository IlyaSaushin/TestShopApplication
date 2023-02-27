package com.earl.testshopapplication.di

import android.app.Application
import androidx.room.Room
import com.earl.utils.localDataSource.AppDatabase
import com.earl.utils.localDataSource.UsersDbDao
import dagger.Module
import dagger.Provides
import javax.inject.Singleton

@Module
class DatabaseModule {

    @Singleton
    @Provides
    fun provideAppDb(app: Application) : AppDatabase {
        return Room.databaseBuilder(app, AppDatabase::class.java, "db")
            .allowMainThreadQueries()
            .fallbackToDestructiveMigration()
            .build()
    }

    @Singleton
    @Provides
    fun provideAuthDao(db: AppDatabase) : UsersDbDao = db.authDao()
}

