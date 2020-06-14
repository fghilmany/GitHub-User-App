package com.yukmangan.githubuser.presentation.main

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import androidx.core.widget.doOnTextChanged
import androidx.recyclerview.widget.LinearLayoutManager
import com.google.android.material.snackbar.Snackbar
import com.yukmangan.githubuser.R
import com.yukmangan.githubuser.network.github.Item
import com.yukmangan.githubuser.presentation.detail.DetailActivity
import com.yukmangan.githubuser.presentation.favorite.FavoriteActivity
import com.yukmangan.githubuser.presentation.setting.SettingActivity
import com.yukmangan.githubuser.presentation.setting.SettingFragment
import kotlinx.android.synthetic.main.activity_main.*

class MainActivity : AppCompatActivity(), MainView {

    private lateinit var presenter: MainPresenter
    private lateinit var adapter: MainAdapter

    private val TAG = MainActivity::class.java.simpleName
    private var items : MutableList<Item> = mutableListOf()

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val mainToolbar = findViewById<androidx.appcompat.widget.Toolbar>(R.id.main_toolbar)
        setSupportActionBar(mainToolbar)

        val inputText = outlinedTextField.editText?.text.toString()

        outlinedTextField.editText?.doOnTextChanged { inputText, _, _, _ ->
            // Respond to input text change
            presenter = MainPresenter(this)
            presenter.getSearchData(inputText.toString())

        }

        adapter = MainAdapter(items){
            val i = Intent(this, DetailActivity::class.java)
            i.putExtra(DetailActivity.EXTRA_USERNAME, it.login)
            startActivity(i)
        }
        rv_list_user.layoutManager = LinearLayoutManager(this)
        rv_list_user.setHasFixedSize(true)
        rv_list_user.adapter = adapter
    }

    override fun onCreateOptionsMenu(menu: Menu): Boolean {
        menuInflater.inflate(R.menu.main_menu, menu)
        return super.onCreateOptionsMenu(menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        when(item.itemId){
            R.id.menu_favorite -> {
                val i = Intent(this,FavoriteActivity::class.java)
                startActivity(i)
            }

            R.id.menu_setting ->{
                val i = Intent(this,SettingActivity::class.java)
                startActivity(i)

            }
        }
        return super.onOptionsItemSelected(item)
    }

    override fun showLoading() {
        progress_bar.visibility = View.VISIBLE

    }

    override fun hideLoading() {
        progress_bar.visibility = View.INVISIBLE

    }

    override fun onSuccess(data: List<Item>) {
        tv_data_kosong.visibility = View.INVISIBLE
        items.clear()
        items.addAll(data)
        adapter.notifyDataSetChanged()
        rv_list_user.visibility = View.VISIBLE

    }

    override fun throwable(throwable: Throwable) {
        items.clear()
        tv_data_kosong.visibility = View.VISIBLE
        Snackbar.make(window.decorView.rootView,"Data tidak ada", Snackbar.LENGTH_SHORT).show()
        Log.e(TAG,throwable.localizedMessage)
    }

}
