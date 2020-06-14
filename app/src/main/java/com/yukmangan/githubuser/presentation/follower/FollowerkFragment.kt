package com.yukmangan.githubuser.presentation.follower

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar

import com.yukmangan.githubuser.R
import com.yukmangan.githubuser.network.github.FollowersItem
import com.yukmangan.githubuser.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.fragment_followerk.*


class FollowerkFragment : Fragment(), FollowerView {

    private lateinit var presenter: FollowerPresenter
    private lateinit var adapter: FollowerAdapter
    private lateinit var username : String

    private var  items: MutableList<FollowersItem> = mutableListOf()
    private val TAG = FollowerkFragment::class.java.simpleName

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        // Inflate the layout for this fragment
        return inflater.inflate(R.layout.fragment_followerk, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)

        presenter = FollowerPresenter(this)
        username = activity!!.intent.getStringExtra(DetailActivity.EXTRA_USERNAME)!!
        presenter.getFollower(username)

        adapter = FollowerAdapter(items){}
        rv_follower.layoutManager = LinearLayoutManager(activity)
        rv_follower.setHasFixedSize(true)
        rv_follower.adapter = adapter

    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE
    }

    override fun hideLoading() {
        progress_bar.visibility = View.INVISIBLE

    }

    override fun onSuccess(data: List<FollowersItem>) {
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()

    }

    override fun throwable(throwable: Throwable) {
        Snackbar.make(activity!!.window.decorView.rootView,"Data tidak ada", Snackbar.LENGTH_SHORT).show()
        Log.e(TAG,throwable.localizedMessage!!)
    }

}
