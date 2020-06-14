package com.yukmangan.consummerapp

import android.database.ContentObserver
import android.database.Cursor
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.os.Handler
import android.os.HandlerThread
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.async
import kotlinx.coroutines.launch

class MainActivity : AppCompatActivity() {

    lateinit var adapter: FavoriteAdapter

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        initProvider()
        loaduser()

    }

    private fun initProvider() {
        val handlerThread = HandlerThread("DataObserver")
        handlerThread.start()
        val handler = Handler(handlerThread.looper)

        val myObserver = object : ContentObserver(handler) {
            override fun onChange(selfChange: Boolean) {
                loaduser()
            }
        }

        contentResolver.registerContentObserver(Favorite.CONTENT_URI, true, myObserver)
    }

    private fun loaduser() {
        GlobalScope.launch(Dispatchers.Main) {

            val deferredUser = async(Dispatchers.IO) {
                var userList = ArrayList<Favorite>()
                var cursor: Cursor? = null
                try {

                    cursor =
                        contentResolver.query(Favorite.CONTENT_URI, null, null, null, null) as Cursor
                    userList = MappingHelper.cursorToArrayList(cursor)
                } finally {
                    cursor?.close()
                }

                userList
            }

            val userList = deferredUser.await()
            if (userList.size > 0) {
                adapter = FavoriteAdapter(userList)

                initListUser()

            }
        }

    }

    private fun initListUser() {
        rv_consum_app.layoutManager = LinearLayoutManager(this@MainActivity)
        rv_consum_app.setHasFixedSize(true)
        rv_consum_app.adapter = adapter

    }
}
