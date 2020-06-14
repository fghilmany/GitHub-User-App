package com.yukmangan.githubuser.presentation.follower

import com.yukmangan.githubuser.network.github.FollowersItem

interface FollowerView {

    fun showLoading()
    fun hideLoading()
    fun onSuccess(data: List<FollowersItem>)
    fun throwable(throwable: Throwable)
}