package com.yukmangan.githubuser.presentation.favorite

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yukmangan.githubuser.R
import com.yukmangan.githubuser.db.Favorite
import kotlinx.android.synthetic.main.item_user.view.*

class FavoriteAdapter (val result: List<Favorite>, val listener: (Favorite) -> (Unit))
    : RecyclerView.Adapter<FavoriteViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FavoriteViewHolder {
        return FavoriteViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: FavoriteViewHolder, position: Int) {
        holder.bindItem(result[position], listener)

    }

}

class FavoriteViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bindItem(result: Favorite, listener: (Favorite) -> (Unit)){
        itemView.tv_search_username.text = result.username
        Picasso.get().load(result.avatarUrl).fit().into(itemView.iv_user)
        itemView.setOnClickListener {
            listener(result)
        }
    }

}
