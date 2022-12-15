package com.tirex_projs.telegram_clone

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.tirex_projs.telegram_clone.databinding.ActivityMainBinding
import com.tirex_projs.telegram_clone.ui.fragments.ChatsFragment
import com.tirex_projs.telegram_clone.ui.objects.AppDrawer

class MainActivity : AppCompatActivity() {
    private lateinit var mActivityMainBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        mActivityMainBinding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(mActivityMainBinding.root)
    }

    override fun onStart() {
        super.onStart()
        initFields()
        initFunctionality()
    }

    private fun initFunctionality() {
        setSupportActionBar(mToolbar)
        mAppDrawer.create()
        supportFragmentManager.beginTransaction()
            .replace(R.id.dataContainer, ChatsFragment()).commit()
    }

    private fun initFields() {
        mToolbar = mActivityMainBinding.mainToolBar
        mAppDrawer = AppDrawer(this, mToolbar)
    }
}