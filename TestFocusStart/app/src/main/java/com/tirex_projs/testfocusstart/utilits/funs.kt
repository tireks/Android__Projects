package com.tirex_projs.testfocusstart.utilits

import androidx.appcompat.app.AppCompatActivity
import androidx.fragment.app.Fragment

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