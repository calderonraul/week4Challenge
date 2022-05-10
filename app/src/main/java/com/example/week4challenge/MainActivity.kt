package com.example.week4challenge

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import com.example.week4challenge.photo.PhotoFragment
import com.example.utils.util.replaceFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        addCountriesFragment()

    }

    private fun addCountriesFragment() {
        /* Display First Fragment initially */
        replaceFragment(PhotoFragment(),
            R.id.fragment_container
        )
    }

    override fun onBackPressed() {
        if (supportFragmentManager.backStackEntryCount > 0) {
            supportFragmentManager.popBackStack()
        } else {
            super.onBackPressed()
        }
    }

}