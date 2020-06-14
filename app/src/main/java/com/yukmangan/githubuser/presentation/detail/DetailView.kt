package com.yukmangan.githubuser.presentation.detail

import com.yukmangan.githubuser.network.github.DetailUser

interface DetailView {

    fun onSuccess(data : DetailUser)
    fun throwable(throwable: Throwable)
}