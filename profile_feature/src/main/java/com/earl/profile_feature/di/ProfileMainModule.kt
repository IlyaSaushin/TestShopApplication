package com.earl.profile_feature.di

import com.earl.profile_feature.data.BaseRepository
import com.earl.profile_feature.domain.Interactor
import com.earl.profile_feature.domain.Repository
import com.earl.utils.localDataSource.UsersDbDao
import dagger.Module
import dagger.Provides

@Module
class ProfileMainModule {

    @ProfileScope
    @Provides
    fun provideInteractor(
        repository: Repository
    ) : Interactor {
        return Interactor.Base(
            repository
        )
    }

    @ProfileScope
    @Provides
    fun provideRepository(
        usersDbDao: UsersDbDao
    ) : Repository {
        return BaseRepository(
            usersDbDao
        )
    }
}