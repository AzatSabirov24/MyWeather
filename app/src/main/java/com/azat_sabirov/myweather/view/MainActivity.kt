package com.azat_sabirov.myweather.view

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.azat_sabirov.myweather.R
import com.azat_sabirov.myweather.model.MainFragment

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.main_activity)
        if (savedInstanceState == null) {
            supportFragmentManager.beginTransaction()
                .replace(R.id.container, MainFragment.newInstance())
                .commitNow()
        }
    }
}