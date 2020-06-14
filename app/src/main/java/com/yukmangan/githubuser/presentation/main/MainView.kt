package com.yukmangan.githubuser.presentation.main

import com.yukmangan.githubuser.network.github.Item

interface MainView {

    fun showLoading()
    fun hideLoading()
    fun onSuccess(data : List<Item>)
    fun throwable(throwable: Throwable)

}