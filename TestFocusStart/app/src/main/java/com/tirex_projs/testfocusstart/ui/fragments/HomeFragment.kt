package com.tirex_projs.testfocusstart.ui.fragments

import android.content.Context
import android.content.SharedPreferences
import android.os.Bundle
import android.view.*
import android.view.View.OnTouchListener
import android.widget.ArrayAdapter
import android.widget.Filter
import android.widget.Toast
import com.tirex_projs.testfocusstart.R
import com.tirex_projs.testfocusstart.databinding.FragmentHomeBinding
import com.tirex_projs.testfocusstart.utilits.AppTextWatcher
import com.tirex_projs.testfocusstart.utilits.showToast
import io.reactivex.android.schedulers.AndroidSchedulers
import io.reactivex.schedulers.Schedulers


class HomeFragment : BaseFragment<FragmentHomeBinding>() {
    private lateinit var stringsArr: MutableList<String>
    private lateinit var preferencesStorage : SharedPreferences
    private lateinit var editorStorage : SharedPreferences.Editor;
    lateinit var adapter: ArrayAdapter<String>
    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        preferencesStorage  =
            requireActivity().getSharedPreferences("com.tirex_projs.testfocusstart.SEARCH_HISTORY", Context.MODE_PRIVATE)
        editorStorage = preferencesStorage.edit()
        stringsArr = mutableListOf()
        importHistory()
        adapter = ArrayAdapter<String>(requireActivity(),android.R.layout.simple_list_item_1,stringsArr)
        binding.homeHeaderBinInputEditText.threshold=0
        binding.homeHeaderBinInputEditText.setAdapter(adapter)
        binding.homeHeaderBinInputEditText.addTextChangedListener(AppTextWatcher {
            val string = binding.homeHeaderBinInputEditText.text.toString()
            if (string.length == 8) {
                beginSearch(string)
            }
        })
        binding.schemeBrandRowDataScheme.addTextChangedListener(AppTextWatcher{
            if (binding.schemeBrandRowDataScheme.text.isNotEmpty()){
                //addToHistory(adapter)
            }
        })
    }

    private fun importHistory() {
        if (preferencesStorage.contains("SIZE") && (preferencesStorage.getInt("SIZE", 0) != 0)){
            var historySize : Int = preferencesStorage.getInt("SIZE", 0)
            var counter = 0;
            var tempString : String = ""
            while (counter < historySize){
                if (preferencesStorage.contains("SLOT${counter+1}")){
                    tempString = preferencesStorage.getString("SLOT${counter+1}", "111").toString()
                    stringsArr.add(tempString)
                }
                counter++
            }
        }
    }

    private fun exportHistory(cardNum: String){
        var currentSize : Int = 0
        if (!stringsArr.contains(cardNum)){
            /*adapter.add(cardNum)*///нельзя просто добавлять, хотя надо проверить, добавляет ли он совпадающие варианты
            stringsArr.add(cardNum)
            adapter.add(cardNum)
            adapter.notifyDataSetChanged()
            if (preferencesStorage.contains("SIZE")){
                currentSize = preferencesStorage.getInt("SIZE", 0)
                currentSize++
                editorStorage.putInt("SIZE", currentSize)
                //editorStorage.commit()
            } else {
                currentSize = 1
                editorStorage.putInt("SIZE", 1)
                //editorStorage
            }
            editorStorage.putString("SLOT${currentSize}", cardNum)
            editorStorage.commit()
        }


    }

    private fun deleteHistory(){
        var currentSize : Int = 0
        if (preferencesStorage.contains("SIZE")){
            currentSize = preferencesStorage.getInt("SIZE", 0)
            editorStorage.putInt("SIZE", 0)
        }
        while (currentSize > 0){
            if (preferencesStorage.contains("SLOT${currentSize}")){
                /*tempString = preferencesStorage.getString("SLOT${currentSize+1}", "111").toString()
                stringsArr.add(tempString)*/
                editorStorage.remove("\"SLOT${currentSize}\"")
            }
            currentSize--
        }
        editorStorage.commit()
        stringsArr.clear()
        adapter.clear()
        adapter.notifyDataSetChanged()
    }

    /*private fun addToHistory(adapter: ArrayAdapter<String>) {
        adapter.add(binding.homeHeaderBinInputEditText.text.toString())
    }*/

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
                deleteHistory()
                true
            }
            else -> super.onOptionsItemSelected(item)

        }
    }

    private fun beginSearch(cardNum: String) {
        showToast("Ok")
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
                    //adapter.add(cardNum)/////
                    exportHistory(cardNum)
                },
                { error -> Toast.makeText(this.context, error.message, Toast.LENGTH_SHORT).show() }
            )
    }


    override fun onPause() {
        super.onPause()
        disposableHome?.dispose()
    }
    /*TODO удаление истории*/
    /*todo очистка полей при изменении бина -- думаю сделать специальный воркер, обрабатывающий работу с биндингом*/
    /*TODO сейчас активация обработчика истории происходит от изменения поля scheme, это плохо*/
    /*TODO сделать кнопки активными*/
    /*TODO посмотреть ка выполнить последний пункт*/
    /*TODO рефактор дизайна*/
    /*TODO рефактор кода этого фрагмента*/

}

