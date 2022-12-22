package com.tirex_projs.telegram_clone.utilits

import android.content.Intent
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import com.tirex_projs.telegram_clone.R
import com.tirex_projs.telegram_clone.activities.AuthControlActivity
import com.tirex_projs.telegram_clone.ui.fragments.ChatsFragment

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

fun AppCompatActivity.replaceActivity(activity: AppCompatActivity) {
    val intent = Intent(this, activity::class.java)
    startActivity(intent)
    this.finish()
}

fun AppCompatActivity.replaceFragment(fragment: Fragment, dataContainerViewId: Int) {
    supportFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(dataContainerViewId, fragment).commit()
}

fun Fragment.replaceFragment(fragment: Fragment, dataContainerViewId: Int) {
    this.parentFragmentManager.beginTransaction()
        .addToBackStack(null)
        .replace(dataContainerViewId, fragment).commit()
}