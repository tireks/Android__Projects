package com.tirex_projs.telegram_clone

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import androidx.appcompat.widget.Toolbar
import com.google.firebase.auth.FirebaseAuth
import com.tirex_projs.telegram_clone.activities.AuthControlActivity
import com.tirex_projs.telegram_clone.databinding.ActivityMainBinding
import com.tirex_projs.telegram_clone.ui.fragments.ChatsFragment
import com.tirex_projs.telegram_clone.ui.objects.AppDrawer
import com.tirex_projs.telegram_clone.utilits.replaceActivity
import com.tirex_projs.telegram_clone.utilits.replaceFragment

class MainActivity : AppCompatActivity() {
    private lateinit var mActivityMainBinding: ActivityMainBinding
    private lateinit var mAppDrawer: AppDrawer
    private lateinit var mToolbar: Toolbar
    private lateinit var mAuthFirebase: FirebaseAuth

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
        if (mAuthFirebase.currentUser != null){  // false is a placeholder!!
            setSupportActionBar(mToolbar)
            mAppDrawer.create()
            replaceFragment(ChatsFragment(), R.id.dataContainer)
        } else {
            replaceActivity(AuthControlActivity())
        }
    }

    private fun initFields() {
        mToolbar = mActivityMainBinding.mainToolBar
        mAppDrawer = AppDrawer(this, mToolbar)
        mAuthFirebase = FirebaseAuth.getInstance()
    }
}