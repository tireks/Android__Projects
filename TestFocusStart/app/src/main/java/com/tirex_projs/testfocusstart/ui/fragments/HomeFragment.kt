package com.tirex_projs.testfocusstart.ui.fragments

import android.content.Context
import android.content.Context.TELEPHONY_SERVICE
import android.content.Intent
import android.content.SharedPreferences
import android.net.Uri
import android.os.Bundle
import android.telephony.TelephonyManager
import android.view.*
import android.widget.AdapterView
import android.widget.AdapterView.OnItemClickListener
import android.widget.ArrayAdapter
import android.widget.TextView
import android.widget.Toast
import androidx.core.content.ContextCompat.getSystemService
import androidx.viewbinding.ViewBinding
import com.tirex_projs.testfocusstart.R
import com.tirex_projs.testfocusstart.databinding.FragmentHomeBinding
import com.tirex_projs.testfocusstart.model.CardModel
import com.tirex_projs.testfocusstart.utilits.AppArrayAdapter
import com.tirex_projs.testfocusstart.utilits.AppHistoryWorker
import com.tirex_projs.testfocusstart.utilits.AppTextWatcher
import com.tirex_projs.testfocusstart.utilits.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


open class HomeFragment : BaseFragment<FragmentHomeBinding>(), View.OnClickListener, OnItemClickListener {
    private lateinit var stringsArr: ArrayList<String>
    private lateinit var preferencesStorage: SharedPreferences
    private lateinit var editorStorage: SharedPreferences.Editor
    private lateinit var adapter: AppArrayAdapter<String>
    private var bindListTV: ArrayList<TextView> = arrayListOf()
    private lateinit var historyWorker: AppHistoryWorker

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        var emptySearch: Boolean = true
        preferencesStorage =
            requireActivity().getSharedPreferences(
                "com.tirex_projs.testfocusstart.SEARCH_HISTORY",
                Context.MODE_PRIVATE
            )
        editorStorage = preferencesStorage.edit()
        stringsArr = arrayListOf()
        adapter =
            AppArrayAdapter(requireActivity(), android.R.layout.simple_list_item_1, stringsArr)
        historyWorker = AppHistoryWorker(preferencesStorage,stringsArr, editorStorage, adapter)
        historyWorker.importHistory()
        binding.homeHeaderBinInputEditText.threshold = 0
        binding.homeHeaderBinInputEditText.setAdapter(adapter)
        binding.homeHeaderBinInputEditText.addTextChangedListener(AppTextWatcher {
            val string = binding.homeHeaderBinInputEditText.text.toString()
            if ((string.length == 8) and (emptySearch)) {
                emptySearch = false
                beginSearch(string)
            } else if ((string.length != 8) and (!emptySearch)) {
                emptySearch = true
                screenClear()
            }
        })
        binding.homeHeaderBinInputEditText.onItemClickListener = this
        binding.bankURLRow.setOnClickListener(this)
        binding.bankPhoneRow.setOnClickListener(this)
        binding.countryCoordsRow.setOnClickListener(this)
        bindListTV = arrayListOf(
            binding.schemeBrandRowDataScheme,
            binding.schemeBrandRowDataBrand,
            binding.numberRowDataLabelLeftLength,
            binding.numberRowDataLabelRightLUHN,
            binding.typePrepaidRowDataType,
            binding.typePrepaidRowDataPrepaid,
            binding.bankRowDataLabelName,
            binding.bankRowDataLabelRightCity,
            binding.bankURLRowDataLabel,
            binding.bankPhoneRowDataLabel,
            binding.countryRowDataLabelName,
            binding.countryRowDataLabelRightCurrency,
            binding.countryCoordsRowDataLongtitude,
            binding.countryCoordsRowDataLatitude,
        )
    }

    private fun screenClear() {
        for (i in bindListTV.indices){
            bindListTV[i].text = ""
        }
    }

    override fun onItemClick(p0: AdapterView<*>?, p1: View?, p2: Int, p3: Long) {
        beginSearch(binding.homeHeaderBinInputEditText.text.toString())
    }

    override fun inflateViewBinding(
        inflater: LayoutInflater,
        container: ViewGroup?
    ): FragmentHomeBinding {
        return FragmentHomeBinding.inflate(inflater, container, false)
    }

    override fun onResume() {
        super.onResume()
        setHasOptionsMenu(true)
    }

    override fun onCreateOptionsMenu(menu: Menu, inflater: MenuInflater) {
        activity?.menuInflater?.inflate(R.menu.home_action_menu, menu)
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when (item.itemId) {
            R.id.home_menu_btn_delete_history -> {
                historyWorker.deleteHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    override fun onClick(p0: View?) {
        when (p0) {
            binding.bankURLRow -> {
                if (binding.bankURLRowDataLabel.text.toString() != "---") {
                    openWebPage("https://" + binding.bankURLRowDataLabel.text.toString())
                }
            }
            binding.bankPhoneRow -> {
                if (binding.bankPhoneRowDataLabel.text.toString()!= "---") {
                    dialPhoneNumber(binding.bankPhoneRowDataLabel.text.toString())
                }
            }
            binding.countryCoordsRow -> {
                if ((binding.countryCoordsRowDataLatitude.text.toString()!= "---") and
                    (binding.countryCoordsRowDataLongtitude.text.toString()!= "---")
                ) {
                    val lat = binding.countryCoordsRowDataLatitude.text.toString() + ".0"
                    val long = binding.countryCoordsRowDataLongtitude.text.toString() + ".0"
                    showMap("geo:$lat,$long?q=$lat,$long&z=16")

                }
            }
        }
    }

    private fun openWebPage(url: String) {
        val webpage: Uri = Uri.parse(url)
        val intent = Intent(Intent.ACTION_VIEW, webpage)
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            requireActivity().startActivity(intent)
        }
    }

    private fun dialPhoneNumber(phoneNumber: String) {
        val intent = Intent(Intent.ACTION_DIAL)
        intent.data = Uri.parse("tel:$phoneNumber")
        startActivity(intent)

    }

    private fun showMap(geoLocationStr: String) {
        val geoLocationUri = Uri.parse(geoLocationStr)
        val intent = Intent(Intent.ACTION_VIEW).apply {
            data = geoLocationUri
        }
        if (intent.resolveActivity(requireActivity().packageManager) != null) {
            requireActivity().startActivity(intent)
        }
    }

    private fun beginSearch(cardNum: String) {
        showToast("Ok")
        screenClear()
        disposableHome = binListApiService.getCard("https://lookup.binlist.net/$cardNum")
            .subscribeOn(Schedulers.io())
            .observeOn(AndroidSchedulers.mainThread())
            .subscribe(
                { card ->
                    binding.schemeBrandRowDataBrand.text = card.brand
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
                    historyWorker.exportHistory(cardNum)
                    placeholder()
                },
                { error -> Toast.makeText(this.context, error.message, Toast.LENGTH_SHORT).show() }
            )
    }

    private fun placeholder() {
        for (i in bindListTV.indices) {
            if (bindListTV[i].text.toString().isEmpty()){
                bindListTV[i].text = "---"
            }
        }
    }


    override fun onPause() {
        super.onPause()
        disposableHome?.dispose()
    }

    /*TODO рефактор дизайна*/
    /*TODO рефактор кода этого фрагмента*/

}


