package com.yukmangan.githubuser.presentation.following

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import com.yukmangan.githubuser.R
import com.yukmangan.githubuser.network.github.FollowingItem
import com.yukmangan.githubuser.presentation.detail.DetailActivity
import com.yukmangan.githubuser.presentation.follower.FollowingAdapter
import com.yukmangan.githubuser.presentation.follower.FollowingPresenter
import com.yukmangan.githubuser.presentation.follower.FollowingView
import kotlinx.android.synthetic.main.fragment_following.*

/**
 * A simple [Fragment] subclass.
 */
class FollowingFragment : Fragment(), FollowingView {

    private lateinit var presenter: FollowingPresenter
    private lateinit var adapter: FollowingAdapter
    private lateinit var username : String

    private var  items: MutableList<FollowingItem> = mutableListOf()
    private val TAG = FollowingFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_following, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = FollowingPresenter(this)
        username = activity!!.intent.getStringExtra(DetailActivity.EXTRA_USERNAME)!!
        presenter.getFollower(username)

        adapter = FollowingAdapter(items){}
        rv_following.layoutManager = LinearLayoutManager(activity)
        rv_following.setHasFixedSize(true)
        rv_following.adapter = adapter

    }

    override fun onSuccess(data: List<FollowingItem>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()

    }

    override fun throwable(throwable: Throwable) {
        Snackbar.make(activity!!.window.decorView.rootView,"Data tidak ada", Snackbar.LENGTH_SHORT).show()
        Log.e(TAG,throwable.localizedMessage!!)

    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.INVISIBLE

    }

}
