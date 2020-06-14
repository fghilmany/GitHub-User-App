package com.yukmangan.consummerapp

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.item_user_consum.view.*

class FavoriteAdapter (private val result: List<Favorite>)
    : RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user_consum, parent, false))
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(result[position])

    }

}

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bindItem(result: Favorite){
        itemView.tv_search_username.text = result.username
        Picasso.get().load(result.avatarUrl.toString()).fit().into(itemView.iv_user_consum)

        Log.e("CEK_PICASSO",result.avatarUrl.toString())
    }

}
