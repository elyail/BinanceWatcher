package com.gabdullinae.binancewatcher

import android.os.Bundle
import androidx.appcompat.app.AppCompatActivity
import com.gabdullinae.binancewatcher.di.DI
import com.gabdullinae.binancewatcher.home.HomeFragment

class BaseActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        DI.app.inject(this)

        if (savedInstanceState == null) {
            val homeFragment = HomeFragment()
            supportFragmentManager
                    .beginTransaction()
                    .add(homeFragment, HomeFragment::class.java.name)
                    .commit()
        }
    }
}
