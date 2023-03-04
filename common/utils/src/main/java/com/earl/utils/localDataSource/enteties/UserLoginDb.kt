package com.earl.utils.localDataSource.enteties

import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.earl.utils.localDataSource.mappers.UserLoginDbToDataMapper

@Entity(tableName = "userLoginData")
data class UserLoginDb(
    @PrimaryKey(autoGenerate = true) val id: Int,
    @ColumnInfo(name = "firstname") val firstName: String,
    @ColumnInfo(name = "lastname") val lastName: String,
    @ColumnInfo(name = "email") val email: String,
)
