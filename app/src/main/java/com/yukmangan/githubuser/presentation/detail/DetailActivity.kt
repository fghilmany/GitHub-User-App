package com.yukmangan.githubuser.presentation.detail

import android.annotation.SuppressLint
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import com.google.android.material.snackbar.Snackbar
import com.squareup.picasso.Picasso
import com.yukmangan.githubuser.R
import com.yukmangan.githubuser.db.Favorite
import com.yukmangan.githubuser.db.FavoriteDao
import com.yukmangan.githubuser.db.FavoriteDatabase
import com.yukmangan.githubuser.network.github.DetailUser
import kotlinx.android.synthetic.main.activity_detail.*

class DetailActivity : AppCompatActivity(), DetailView {

    private lateinit var username : String
    private lateinit var presenter: DetailPresenter
    private lateinit var dao : FavoriteDao
    private lateinit var db : FavoriteDatabase


    private val TAG = DetailActivity::class.java.simpleName

    companion object{
        const val EXTRA_USERNAME = "extra_username"
    }

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_detail)

        db = FavoriteDatabase.getDatabase(applicationContext)
        dao = db.favoriteDao()

        username = intent.getStringExtra(EXTRA_USERNAME)!!
        presenter = DetailPresenter(this)
        presenter.getDataDetail(username)

        viewpager_main.adapter = StayPagerAdapter(supportFragmentManager)
        tabs_main.setupWithViewPager(viewpager_main)

    }

    @SuppressLint("SetTextI18n")
    override fun onSuccess(data: DetailUser) {

        Picasso.get().load(data.avatarUrl).fit().into(iv_user_detail)
        tv_name_detail.text = data.name
        tv_username_detail.text = data.login
        tv_followers_detail.text = "Followers : "+data.followers.toString()
        tv_following_detail.text = "Following : "+data.following.toString()

        if (dao.loadUserByUsername(username) != null){
            fab_favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_white_full))
            fab_favorite.setOnClickListener {
                delete(Favorite(username = username, name = data.name, avatarUrl = data.avatarUrl ))
                fab_favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_favorite_border))
                Snackbar.make(window.decorView.rootView, "Berhasil Menghapus Favorite",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }else{
            fab_favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_favorite_border))
            fab_favorite.setOnClickListener {
                insert(Favorite(username = username, name = data.name, avatarUrl = data.avatarUrl ))
                fab_favorite.setImageDrawable(resources.getDrawable(R.drawable.ic_favorite_white_full))
                Snackbar.make(window.decorView.rootView, "Berhasil Menambah Favorite",Snackbar.LENGTH_SHORT).show()
                return@setOnClickListener
            }
        }

    }

    override fun throwable(throwable: Throwable) {

        Snackbar.make(window.decorView.rootView,"Data tidak ada", Snackbar.LENGTH_SHORT).show()
        Log.e(TAG,throwable.localizedMessage!!)

    }

    private fun delete(user: Favorite) {
        dao.delete(user)
    }

    private fun insert(user: Favorite) {
        dao.insert(user)
    }
}
