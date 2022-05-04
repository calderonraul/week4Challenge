package com.example.utils.util

import android.content.Context
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.fragment.app.FragmentManager
import androidx.fragment.app.FragmentTransaction
import com.example.utils.R


fun Context.noNetworkConnectivityError(): AppResult.Error {
    return AppResult.Error(Exception(this.resources.getString(R.string.no_network_connectivity)))
}


fun AppCompatActivity.addFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.inTransaction {
        add(frameId, fragment)
        backStackTag?.let { addToBackStack(fragment.javaClass.name) }
    }
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, frameId: Int, backStackTag: String? = null) {
    supportFragmentManager.inTransaction {
        replace(frameId, fragment)
        backStackTag?.let { addToBackStack(fragment.javaClass.name) }
    }
}

inline fun FragmentManager.inTransaction(func: FragmentTransaction.() -> Unit) {
    val fragmentTransaction = beginTransaction()
    fragmentTransaction.func()
    fragmentTransaction.commit()
}