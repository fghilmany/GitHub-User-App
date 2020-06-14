package com.yukmangan.githubuser.presentation.detail

import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentPagerAdapter
import com.yukmangan.githubuser.presentation.follower.FollowerkFragment
import com.yukmangan.githubuser.presentation.following.FollowingFragment

class StayPagerAdapter (fm: FragmentManager): FragmentPagerAdapter(fm) {

    private val pages = listOf(
        FollowerkFragment(),
        FollowingFragment()
    )

    override fun getItem(position: Int): Fragment {
        return pages[position]
    }

    override fun getCount(): Int {
        return pages.size
    }

    override fun getPageTitle(position: Int): CharSequence? {
        return when(position){
            0 -> "Followers"
            else -> "Following"
        }
    }
}