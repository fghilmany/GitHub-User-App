package com.yukmangan.githubuser.presentation.follower

import com.yukmangan.githubuser.network.github.FollowingItem

interface FollowingView {

    fun showLoading()
    fun hideLoading()
    fun onSuccess(data: List<FollowingItem>)
    fun throwable(throwable: Throwable)
}

