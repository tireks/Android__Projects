package com.example.testtaskshift
import android.app.Activity
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import java.util.Calendar
import android.view.View
import com.example.testtaskshift.databinding.SecondScreenActivityBinding;
import android.icu.text.DateFormat;
import android.text.Editable
import android.view.Gravity
import android.view.LayoutInflater
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import com.example.testtaskshift.databinding.PopupLayoutBinding
import javax.xml.datatype.DatatypeConstants.MONTHS

class SecondScreenActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var bindingSecond: SecondScreenActivityBinding;
    private lateinit var bindingPopup: PopupLayoutBinding;
    private lateinit var prefStorage : SharedPreferences;
    private lateinit var editorStorage : SharedPreferences.Editor;
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        bindingSecond = SecondScreenActivityBinding.inflate(layoutInflater)
        setContentView(bindingSecond.root)
        bindingSecond.buttonGreeting.setOnClickListener(this);
        bindingSecond.buttonDropUser.setOnClickListener(this);
        prefStorage = this.getSharedPreferences("SAVED_USER", Context.MODE_PRIVATE)
        editorStorage = prefStorage.edit();
    }

    override fun onClick(view: View) {
        when(view){
            bindingSecond.buttonGreeting -> {
                greeting(view);
            }
            bindingSecond.buttonDropUser -> {
                dropUser();
            }
        }
    }

    fun greeting(view: View){
        val qwe = intent.getStringExtra("dataSend")
        bindingPopup = PopupLayoutBinding.inflate(layoutInflater)
        bindingPopup.myTV.text = "Приветствуем, " + qwe;
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = LinearLayout.LayoutParams.WRAP_CONTENT
        val focus = true
        val popupWindow = PopupWindow(bindingPopup.root, wid, high, focus)
        popupWindow.showAtLocation(view, Gravity.CENTER, 0,0)
    }

    fun dropUser(){
        editorStorage.putBoolean("CONTAINS_USER", false);
        editorStorage.commit();
        val intent = Intent(this@SecondScreenActivity, MainActivity::class.java)
        startActivity(intent);
    }


}