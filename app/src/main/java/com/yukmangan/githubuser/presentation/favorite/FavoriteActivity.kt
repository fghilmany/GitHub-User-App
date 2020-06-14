package com.yukmangan.githubuser.presentation.favorite

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.app.ActionBar
import androidx.appcompat.widget.Toolbar
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.appbar.MaterialToolbar
import com.yukmangan.githubuser.R
import com.yukmangan.githubuser.db.Favorite
import com.yukmangan.githubuser.db.FavoriteDatabase
import com.yukmangan.githubuser.presentation.detail.DetailActivity
import kotlinx.android.synthetic.main.activity_favorite.*
import java.util.ArrayList

class FavoriteActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_favorite)

        initToolbar()

        getDataFavorite()
    }


    private fun getDataFavorite() {
        val db = FavoriteDatabase.getDatabase(applicationContext)
        val dao = db.favoriteDao()
        val listItem = arrayListOf<Favorite>()

        listItem.addAll(dao.loadAllUser())
        setupRecyclerview(listItem)
        

    }

    private fun setupRecyclerview(listItem: ArrayList<Favorite>) {
        rv_favorite.apply {
            adapter = FavoriteAdapter(listItem){
                val i = Intent(this@FavoriteActivity, DetailActivity::class.java)
                i.putExtra(DetailActivity.EXTRA_USERNAME, it.username)
                startActivity(i)
            }
            layoutManager = LinearLayoutManager(this@FavoriteActivity)
        }
    }

    private fun initToolbar() {
        val favoriteToolbar = findViewById<Toolbar>(R.id.favorite_toolbar)
        setSupportActionBar(favoriteToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        favoriteToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}
