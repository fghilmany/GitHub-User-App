package com.yukmangan.githubuser.presentation.setting

import android.content.SharedPreferences
import android.os.Bundle
import androidx.preference.PreferenceFragmentCompat
import androidx.preference.SwitchPreference
import com.yukmangan.githubuser.R

class SettingFragment : PreferenceFragmentCompat(),
    SharedPreferences.OnSharedPreferenceChangeListener {

    private lateinit var alarmReceiver: AlarmReciver
    private lateinit var REMINDER: String
    private lateinit var reminderPreference: SwitchPreference

    companion object {
        val NOTIFICATION_ID = 1
        var CHANNEL_ID = "channel_01"
        var CHANNEL_NAME: CharSequence = "github channel"

        private const val DEFAULT_VALUE = "Tidak Ada"
    }

    override fun onCreatePreferences(savedInstanceState: Bundle?, rootKey: String?) {
        addPreferencesFromResource(R.xml.setting_preference)
        alarmReceiver = AlarmReciver()
        init()
        setSummaries()

    }

    override fun onResume() {
        super.onResume()
        preferenceScreen.sharedPreferences.registerOnSharedPreferenceChangeListener(this)
    }

    override fun onPause() {
        super.onPause()
        preferenceScreen.sharedPreferences.unregisterOnSharedPreferenceChangeListener(this)
    }

    private fun init() {
        REMINDER = resources.getString(R.string.reminder_key)
        reminderPreference = findPreference<SwitchPreference>(REMINDER) as SwitchPreference

    }

    private fun setSummaries() {
        val sh = preferenceManager.sharedPreferences
        reminderPreference.isChecked = sh.getBoolean(REMINDER, false)


    }

    override fun onSharedPreferenceChanged(p0: SharedPreferences, p1: String?) {

        if (p1 == REMINDER){
            reminderPreference.isChecked = p0.getBoolean(REMINDER, false)

            if (reminderPreference.isChecked){
                val repeatTime = "09:00"
                val repeatMessage = "Cari teman belajarmu di Github"
                alarmReceiver.setRepeatingAlarm(context!!, AlarmReciver.TYPE_REPEATING, repeatTime, repeatMessage)
            }else{
                alarmReceiver.cancelAlarm(context!!,AlarmReciver.TYPE_REPEATING)

            }
        }

    }

    /*

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_setting)

        initToolbar()

        alarmReceiver = AlarmReciver()

        val switch = findViewById<Switch>(R.id.switch_reminder)
        switch.setOnCheckedChangeListener { _, isChecked ->
            if (isChecked){
                val repeatTime = "09:00"
                val repeatMessage = "Cari teman belajarmu di Github"
                alarmReceiver.setRepeatingAlarm(this, AlarmReciver.TYPE_REPEATING, repeatTime, repeatMessage)
            }else{
                alarmReceiver.cancelAlarm(this,AlarmReciver.TYPE_REPEATING)

            }
        }

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
    }*/
}
