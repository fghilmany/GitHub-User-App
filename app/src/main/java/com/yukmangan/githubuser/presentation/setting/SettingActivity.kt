package com.yukmangan.githubuser.presentation.setting

import android.os.Bundle
import android.os.PersistableBundle
import android.util.Log
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.widget.Toolbar
import com.yukmangan.githubuser.R

class SettingActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        Log.e("CEK","CK")

        initToolbar()

        supportFragmentManager.beginTransaction().add(R.id.setting_container, SettingFragment()).commit()

    }

    private fun initToolbar() {
        val settingToolbar = findViewById<Toolbar>(R.id.setting_toolbar)
        settingToolbar.title = "Setting"
        setSupportActionBar(settingToolbar)
        supportActionBar!!.setDisplayHomeAsUpEnabled(true)
        supportActionBar!!.setDisplayShowHomeEnabled(true)
        settingToolbar.setNavigationOnClickListener {
            finish()
        }
    }
}