package com.tirex_projs.testfocusstart.utilits

import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.tirex_projs.testfocusstart.model.CardModel

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

fun Fragment.showToast(message: String) {
    Toast.makeText(this.context, message, Toast.LENGTH_SHORT).show()
}

