package com.earl.utils.localDataSource

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.OnConflictStrategy
import androidx.room.Query
import com.earl.utils.localDataSource.enteties.UserLoginDb

@Dao
interface UsersDbDao {

    @Insert(onConflict = OnConflictStrategy.ABORT)
    fun insertNewUserIntoLocalDb(user: UserLoginDb)

    @Query("select * from userLoginData where firstname =:usersFirstname and lastname =:userLastname and email =:usersEmail")
    fun checkIsUserAlreadyRegistered(usersFirstname: String, userLastname: String, usersEmail: String) : List<UserLoginDb>

    @Query("select * from userLoginData where firstname =:usersFirstname and email =:usersEmail")
    fun checkIsUserAlreadyRegisteredByLoginValues(usersFirstname: String, usersEmail: String) : List<UserLoginDb>
}