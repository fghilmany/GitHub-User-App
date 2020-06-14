package com.yukmangan.githubuser.db

import android.os.Parcelable
import androidx.room.ColumnInfo
import androidx.room.Entity
import androidx.room.PrimaryKey
import com.google.gson.annotations.SerializedName
import kotlinx.android.parcel.Parcelize

@Parcelize
@Entity(tableName = "favorite_user")
data class Favorite(

    /*@PrimaryKey(autoGenerate = false)
    @SerializedName("idUser")
    var idUser : Int? = 0,*/

    //@ColumnInfo(name = "username")
    @PrimaryKey(autoGenerate = false)
    @SerializedName("username")
    var username : String = "",

    @ColumnInfo(name = "name")
    @SerializedName("name")
    var name : String? = "",

    @ColumnInfo(name = "avatarUrl")
    @SerializedName("avatarUrl")
    var avatarUrl : String? = ""

):Parcelable{

    companion object{
        const val AUTHORITY = "com.yukmangan.giyhubuser"
        const val SCHEME = "content"
        const val TABLE_NAME = "favorite_user"


    }

    constructor():this("","","")

}