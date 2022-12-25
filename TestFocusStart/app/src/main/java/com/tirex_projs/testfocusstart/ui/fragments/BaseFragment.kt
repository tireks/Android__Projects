package com.tirex_projs.testfocusstart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ArrayAdapter
import androidx.fragment.app.Fragment
import androidx.viewbinding.ViewBinding
import com.tirex_projs.testfocusstart.network.BinListApiService
import io.reactivex.disposables.Disposable

abstract class BaseFragment<VB : ViewBinding> : Fragment() {
    var disposableHome: Disposable? = null
    private var _binding: VB? = null
    val binding get() = _binding!!
    val binListApiService by lazy {
        BinListApiService.create()
    }

    override fun onCreateView(inflater: LayoutInflater, container: ViewGroup?, savedInstanceState: Bundle?): View? {
        _binding = inflateViewBinding(inflater, container)
        val stringsArr: Array<String> = arrayOf("11111111","00000000")
        val adapter : ArrayAdapter<String>  = ArrayAdapter<String>(requireActivity(),android.R.layout.simple_list_item_1,stringsArr)
        return binding.root
    }
    abstract fun inflateViewBinding(inflater: LayoutInflater, container: ViewGroup?): VB

    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}