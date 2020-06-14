package com.yukmangan.githubuser.db

import android.content.Context
import androidx.room.Database
import androidx.room.Room
import androidx.room.RoomDatabase

@Database(entities = [Favorite::class], version = 1, exportSchema = false)
abstract class FavoriteDatabase : RoomDatabase(){

    abstract fun favoriteDao():FavoriteDao

    companion object{
        @Volatile
        private var INSTANCE: FavoriteDatabase?=null

        fun getDatabase(context : Context):FavoriteDatabase{
            return INSTANCE?: synchronized(this){

                //buat DB
                val tempInstace = INSTANCE
                if (tempInstace != null){
                    return tempInstace
                }

                synchronized(this){
                    val instance = Room.databaseBuilder(
                        context.applicationContext,
                        FavoriteDatabase::class.java,
                        "favorite_db"
                    )
                        .allowMainThreadQueries()
                        .fallbackToDestructiveMigration()
                        .build()

                    INSTANCE = instance
                    return instance
                }
            }
        }
    }
}