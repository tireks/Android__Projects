package com.tirex_projs.testfocusstart.ui.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import com.tirex_projs.testfocusstart.R
import com.tirex_projs.testfocusstart.databinding.FragmentHomeBinding
import com.tirex_projs.testfocusstart.utilits.AppTextWatcher
import com.tirex_projs.testfocusstart.utilits.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.disposables.Disposable
import io.reactivex.schedulers.Schedulers

class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        binding.homeHeaderBinInputEditText.addTextChangedListener(AppTextWatcher {
            val string = binding.homeHeaderBinInputEditText.text.toString()
            if (string.length == 8) {
                beginSearch()
            }
        })
    }
    override fun onResume() {
        super.onResume()
    }

    private fun beginSearch() {
        showToast("Ok")
        val cardNum = binding.homeHeaderBinInputEditText.text.toString()
        disposableHome = binListApiService.getCard("https://lookup.binlist.net/$cardNum")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { card -> with(card){
                    fun dataScanner(textView: android.widget.TextView, APIdata : String){
                        if (APIdata.isEmpty()){
                            textView.text = "-no data-"
                        }
                    }
                    binding.schemeBrandRowDataBrand.text = card.brand; dataScanner(binding.schemeBrandRowDataBrand, binding.schemeBrandRowDataBrand.text.toString())
                    binding.schemeBrandRowDataScheme.text = card.scheme
                    binding.typePrepaidRowDataType.text = card.type
                    binding.typePrepaidRowDataPrepaid.text = card.prepaid

                    binding.numberRowDataLabelLeftLength.text = card.number.length
                    binding.numberRowDataLabelRightLUHN.text = card.number.luhn

                    binding.bankRowDataLabelName.text = card.bank.name
                    binding.bankRowDataLabelRightCity.text = card.bank.city
                    binding.bankURLRowDataLabel.text = card.bank.url
                    binding.bankPhoneRowDataLabel.text = card.bank.phone

                    binding.countryRowDataLabelName.text = card.country.name
                    binding.countryRowDataLabelRightCurrency.text = card.country.currency
                    binding.countryCoordsRowDataLatitude.text = card.country.latitude
                    binding.countryCoordsRowDataLongtitude.text = card.country.longitude
                    }
                },
                { error -> Toast.makeText(this.context, error.message, Toast.LENGTH_SHORT).show() }
            )

    }
    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onPause() {
        super.onPause()
        disposableHome?.dispose()
    }
}