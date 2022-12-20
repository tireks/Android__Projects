package com.tirex_projs.telegram_clone.ui.fragments

import android.view.Menu
import android.view.MenuInflater
import com.tirex_projs.telegram_clone.R
import com.tirex_projs.telegram_clone.databinding.FragmentSettingsBinding
import com.tirex_projs.telegram_clone.databinding.FragmentSettingsBinding.*

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override fun getViewBinding() = inflate(layoutInflater)

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }


}