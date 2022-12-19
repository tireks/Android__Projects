package com.tirex_projs.telegram_clone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.tirex_projs.telegram_clone.activities.AuthControlActivity
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
        if (false){  // false is a placeholder!!
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            supportFragmentManager.beginTransaction()
                .replace(R.id.dataContainer, ChatsFragment()).commit()
        } else {
            val intent = Intent(this, AuthControlActivity::class.java)
            startActivity(intent)
        }
    }

    private fun initFields() {
        mToolbar = mActivityMainBinding.mainToolBar
        mAppDrawer = AppDrawer(this, mToolbar)
    }
}