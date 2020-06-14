package com.yukmangan.githubuser.presentation.follower

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import com.squareup.picasso.Picasso
import com.yukmangan.githubuser.R
import com.yukmangan.githubuser.network.github.FollowersItem
import kotlinx.android.synthetic.main.item_user.view.*

class FollowerAdapter (val result: List<FollowersItem>, val listener: (FollowersItem) -> (Unit))
    : RecyclerView.Adapter<FollowerViewHolder>(){
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FollowerViewHolder {
        return FollowerViewHolder(LayoutInflater.from(parent.context).inflate(R.layout.item_user, parent, false))
    }

    override fun getItemCount(): Int = result.size

    override fun onBindViewHolder(holder: FollowerViewHolder, position: Int) {
        holder.bindItem(result[position], listener)

    }

}

class FollowerViewHolder(view: View): RecyclerView.ViewHolder(view) {

    fun bindItem(result: FollowersItem, listener: (FollowersItem) -> (Unit)){
        itemView.tv_search_username.text = result.login
        Picasso.get().load(result.avatarUrl).fit().into(itemView.iv_user)
        itemView.setOnClickListener {
            listener(result)
        }
    }

}
