package com.yukmangan.consummerapp

import android.database.Cursor

object MappingHelper {

    fun cursorToArrayList(userCursor: Cursor):ArrayList<Favorite>{
        val listFavorite = ArrayList<Favorite>()

        while (userCursor.moveToNext()){
            val username = userCursor.getString(userCursor.getColumnIndexOrThrow("username"))
            val name = userCursor.getString(userCursor.getColumnIndexOrThrow("name"))
            val avatarUrl = userCursor.getString(userCursor.getColumnIndexOrThrow("avatarUrl"))

            val favorite = Favorite(
                username,
                name,
                avatarUrl
            )
            listFavorite.add(favorite)

        }
        return listFavorite
    }
}