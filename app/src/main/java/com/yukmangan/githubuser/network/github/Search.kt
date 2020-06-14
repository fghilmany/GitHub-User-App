package com.yukmangan.githubuser.network.github


import com.google.gson.annotations.SerializedName

data class Search(
    @SerializedName("incomplete_results")
    val incompleteResults: Boolean = false,
    @SerializedName("items")
    val items: List<Item> = listOf(),
    @SerializedName("total_count")
    val totalCount: Int = 0
)