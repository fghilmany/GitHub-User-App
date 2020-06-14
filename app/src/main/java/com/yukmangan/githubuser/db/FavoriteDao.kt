package com.yukmangan.githubuser.db

import android.database.Cursor
import androidx.room.*

@Dao
interface FavoriteDao {

    @Query("Select * From favorite_user")
    fun loadAllUser():List<Favorite>

    @Query("Select * From favorite_user")
    fun loadAllUserCursor():Cursor

    @Query("SELECT * FROM favorite_user where username=:username")
    fun loadUserByUsername(username: String): Favorite

    @Insert(onConflict = OnConflictStrategy.IGNORE)
    fun insert(favorite: Favorite)

    @Delete
    fun delete(favorite: Favorite)

}