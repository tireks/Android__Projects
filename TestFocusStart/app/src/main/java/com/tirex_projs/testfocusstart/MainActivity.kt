package com.tirex_projs.testfocusstart

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.tirex_projs.testfocusstart.databinding.ActivityMainBinding
import com.tirex_projs.testfocusstart.ui.fragments.HomeFragment
import com.tirex_projs.testfocusstart.utilits.replaceFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mainBinding: ActivityMainBinding
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mainBinding.root)
    }
    override fun onStart() {
        super.onStart()
        initFields()
        initFunctionality()
    }

    private fun initFunctionality() {
        //setSupportActionBar(mToolbar)
        replaceFragment(HomeFragment(), R.id.mainDataContainer)
    }

    private fun initFields() {
       // mToolbar = mainBinding.mainToolbar
    }

}