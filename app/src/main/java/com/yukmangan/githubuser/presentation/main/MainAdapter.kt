package com.yukmangan.githubuser.presentation.main

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yukmangan.githubuser.R
import com.yukmangan.githubuser.network.github.Item
import kotlinx.android.synthetic.main.item_user.view.*

class MainAdapter (val result: List<Item>, val listener: (Item) -> (Unit))
    :RecyclerView.Adapter<MainViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MainViewHolder {
        return MainViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: MainViewHolder, position: Int) {
        holder.bindItem(result[position], listener)

    }

}

class MainViewHolder(view:View):RecyclerView.ViewHolder(view) {

    fun bindItem(result:Item, listener: (Item) -> (Unit)){
        itemView.tv_search_username.text = result.login
        Picasso.get().load(result.avatarUrl).fit().into(itemView.iv_user)
        itemView.setOnClickListener {
            listener(result)
        }
    }

}
