package com.yukmangan.githubuser.provider

import android.content.ContentProvider
import android.content.ContentValues
import android.content.Context
import android.content.UriMatcher
import android.database.Cursor
import android.net.Uri
import com.yukmangan.githubuser.db.Favorite
import com.yukmangan.githubuser.db.FavoriteDatabase

class FavoriteContentProvider : ContentProvider() {

    companion object{
        private val uriMatcher = UriMatcher(UriMatcher.NO_MATCH)
        private lateinit var favoriteDatabase: FavoriteDatabase

        init {
            uriMatcher.addURI(Favorite.AUTHORITY, Favorite.TABLE_NAME,1)
        }
    }

    override fun insert(p0: Uri, p1: ContentValues?): Uri? {
        return null
    }

    override fun query(
        p0: Uri,
        p1: Array<out String>?,
        p2: String?,
        p3: Array<out String>?,
        p4: String?
    ): Cursor? {
        val cursor: Cursor?
        cursor = favoriteDatabase.favoriteDao().loadAllUserCursor()
        return cursor
    }

    override fun onCreate(): Boolean {
        favoriteDatabase = FavoriteDatabase.getDatabase(context as Context)
        return true
    }

    override fun update(p0: Uri, p1: ContentValues?, p2: String?, p3: Array<out String>?): Int {
        return 0
    }

    override fun delete(p0: Uri, p1: String?, p2: Array<out String>?): Int {
        return 0
    }

    override fun getType(p0: Uri): String? {
        return null
    }
}