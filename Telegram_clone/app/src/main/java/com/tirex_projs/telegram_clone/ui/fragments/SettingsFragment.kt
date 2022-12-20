package com.tirex_projs.telegram_clone.ui.fragments

import android.view.LayoutInflater
import android.view.Menu
import android.view.MenuInflater
import android.view.ViewGroup
import com.tirex_projs.telegram_clone.R
import com.tirex_projs.telegram_clone.databinding.FragmentChatsBinding
import com.tirex_projs.telegram_clone.databinding.FragmentSettingsBinding
import com.tirex_projs.telegram_clone.databinding.FragmentSettingsBinding.*

class SettingsFragment : BaseFragment<FragmentSettingsBinding>() {
    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }
    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.settings_action_menu, menu)
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentSettingsBinding {
        return FragmentSettingsBinding.inflate(inflater, container, false)
    }


}