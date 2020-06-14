package com.yukmangan.consummerapp

import android.net.Uri
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class Favorite(


    var username : String = "",

    var name : String? = "",

    var avatarUrl : String? = ""

):Parcelable{

    companion object{
        const val AUTHORITY = "com.yukmangan.githubuser"
        const val SCHEME = "content"
        const val TABLE_NAME = "favorite_user"

        val CONTENT_URI: Uri = Uri.Builder().scheme(SCHEME)
            .authority(AUTHORITY)
            .appendPath(TABLE_NAME)
            .build()


    }

}