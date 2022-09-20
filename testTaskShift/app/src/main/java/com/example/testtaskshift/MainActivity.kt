package com.example.testtaskshift

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.app.DatePickerDialog
import android.app.ProgressDialog.show
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.content.res.ColorStateList
import android.graphics.Color
import java.util.Calendar
import android.view.View
import com.example.testtaskshift.databinding.ActivityMainBinding;
import android.icu.text.DateFormat;
import android.text.Editable
import android.widget.*
import androidx.core.widget.doAfterTextChanged
import javax.xml.datatype.DatatypeConstants.MONTHS

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding;
    private var validCont = ValidationContainer();
    private lateinit var preferencesStorage : SharedPreferences;
    private lateinit var editorStorage : SharedPreferences.Editor;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        preferencesStorage = this.getSharedPreferences("SAVED_USER", Context.MODE_PRIVATE)
        editorStorage = preferencesStorage.edit();
        if (preferencesStorage.contains("CONTAINS_USER") && preferencesStorage.getBoolean("CONTAINS_USER", false)){
            val dataFromPrefs = preferencesStorage.getString("USERNAME", "no user found")
            val intent = Intent(this@MainActivity, SecondScreenActivity::class.java).apply {
                putExtra("dataSend", dataFromPrefs);
            }
            startActivity(intent);
        }
        binding.buttonBirthdate.setOnClickListener(this);
        binding.buttonConfirm.setOnClickListener(this);

        binding.editTextLastname.doAfterTextChanged {
            text: Editable? ->  checkInputsText(text, "Lastname");
        }
        binding.editTextName.doAfterTextChanged {
            text: Editable? -> checkInputsText(text, "Name");
        }
        binding.editTextSurname.doAfterTextChanged {
            text: Editable? -> checkInputsText(text, "Surname");
        }
        binding.editTextPassInp.doAfterTextChanged {
                text: Editable? -> checkInputsText(text, "PassInp");
        }
        binding.editTextPassCheck.doAfterTextChanged {
                text: Editable? -> checkInputsText(text, "PassCheck");
        }
    }

    override fun onClick(view: View) {
        val idView : Int = view.id;
        when (view){
            binding.buttonBirthdate -> {
                callDatePicker();
            }
            binding.buttonConfirm -> {
                registration();
            }
        }
    }

    fun callDatePicker() {
        val calendar: Calendar = Calendar.getInstance();
        val mYear = calendar.get(Calendar.YEAR);
        var wqe : Int = 0;
        val mMonth = calendar.get(Calendar.MONTH);
        val mDay = calendar.get(Calendar.DAY_OF_MONTH);
        val datePickerDialog = DatePickerDialog(this, DatePickerDialog.OnDateSetListener { datePicker, year, monthOfYear, dayOfMonth ->
            checkInputsDate(year, monthOfYear, dayOfMonth, mYear, mMonth, mDay);
            // todo вот тут кажется можно в вызове функции вытащить нужные данные
            binding.editTextBirthdate.setText(dayOfMonth.toString() + "." + (monthOfYear + 1).toString() + "." + year.toString());
        }, mYear, mMonth, mDay)
        datePickerDialog.show();

    }

    fun registration(){
        if (validCont.accessController()){
            val dataToSecondActivity = binding.editTextName.text.toString() + " " + binding.editTextSurname.text.toString() + " " + binding.editTextLastname.text.toString();
            editorStorage.putBoolean("CONTAINS_USER", true);
            editorStorage.putString("USERNAME", dataToSecondActivity);
            editorStorage.commit();
            val intent = Intent(this@MainActivity, SecondScreenActivity::class.java).apply {
                putExtra("dataSend", dataToSecondActivity);
            }
            startActivity(intent);
        } else {
            Toast.makeText(applicationContext,
                "Регистрация недоступна, проверьте правильность введенных данных",
                Toast.LENGTH_LONG).show();
        }
    }

    fun checkInputsDate(year: Int, month: Int, day: Int, todayYear : Int, todayMonth: Int, todayDay : Int){
        if(((year == todayYear - 18) && (month > todayMonth || (month == todayMonth && day > todayDay)))
            || year > todayYear - 18){
            if (binding.textViewBirthdateWarn.visibility == View.INVISIBLE) {
                showAlert(binding.editTextBirthdate, binding.textViewBirthdateWarn, validCont, "Birthdate");
            }
        } else{
            cancelAlert(binding.editTextBirthdate, binding.textViewBirthdateWarn, validCont, "Birthdate")
        }
    }

    fun checkInputsText(text : Editable?, switchString : String){
        val texts : String = text.toString();
        when (switchString){
            "Name" -> {
                if (texts.count() <= 2 || texts.contains("[^a-zA-Z]".toRegex())){
                    if (binding.textViewNameWarn.visibility == View.INVISIBLE) {
                        showAlert(binding.editTextName, binding.textViewNameWarn, validCont, switchString);
                    }
                } else {
                    if (binding.textViewNameWarn.visibility == View.VISIBLE) {
                        cancelAlert(binding.editTextName, binding.textViewNameWarn, validCont, switchString)
                    }
                }
            }
            "Surname" -> {
                if (texts.count() <= 2 || texts.contains("[^a-zA-Z]".toRegex())){
                    if (binding.textViewSurnameWarn.visibility == View.INVISIBLE) {
                        showAlert(binding.editTextSurname, binding.textViewSurnameWarn, validCont, switchString)
                    }

                } else {
                    if (binding.textViewSurnameWarn.visibility == View.VISIBLE) {
                        cancelAlert(binding.editTextSurname, binding.textViewSurnameWarn, validCont, switchString)
                    }
                }
            }
            "Lastname" -> {
                if (texts.count() <= 2 || texts.contains("[^a-zA-Z]".toRegex())){
                    if (binding.textViewLastnameWarn.visibility == View.INVISIBLE) {
                        showAlert(binding.editTextLastname, binding.textViewLastnameWarn, validCont, switchString)
                    }

                } else {
                    if (binding.textViewLastnameWarn.visibility == View.VISIBLE) {
                        cancelAlert(binding.editTextLastname, binding.textViewLastnameWarn, validCont, switchString)
                    }
                }
            }
            "PassInp" -> {
                if (texts.count() <= 6 || !texts.contains("[0-9]".toRegex()) || !texts.contains("[a-z]".toRegex()) || !texts.contains("[A-Z]".toRegex())) {
                    if (binding.textViewPassInpWarn.visibility == View.INVISIBLE) {
                        showAlert(binding.editTextPassInp, binding.textViewPassInpWarn, validCont, switchString)
                    }
                } else {
                    if (binding.textViewPassInpWarn.visibility == View.VISIBLE) {
                        cancelAlert(binding.editTextPassInp, binding.textViewPassInpWarn, validCont, switchString)
                    }
                }
                if (text.toString() != binding.editTextPassCheck.text.toString()) {
                    if (binding.textViewPassCheckWarn.visibility == View.INVISIBLE) {
                        showAlert(binding.editTextPassCheck, binding.textViewPassCheckWarn, validCont, "PassCheck")
                    }
                }
            }
            "PassCheck" -> {
                if (text.toString() != binding.editTextPassInp.text.toString()) {
                    if (binding.textViewPassCheckWarn.visibility == View.INVISIBLE) {
                        showAlert(binding.editTextPassCheck, binding.textViewPassCheckWarn, validCont, switchString)
                    }

                } else {
                    if (binding.textViewPassCheckWarn.visibility == View.VISIBLE) {
                        cancelAlert(binding.editTextPassCheck, binding.textViewPassCheckWarn, validCont, switchString)
                    }
                }
            }

        }

    }

    fun showAlert(editTxt : EditText, txtViewWarn : TextView, validCont_ : ValidationContainer, switchString : String){
        editTxt.setTextColor(Color.RED);
        txtViewWarn.visibility = View.VISIBLE;
        validCont_.validsUpdate(switchString, false)
    }
    fun cancelAlert(editTxt : EditText, txtViewWarn : TextView, validCont_ : ValidationContainer, switchString : String){
        txtViewWarn.visibility = View.INVISIBLE;
        editTxt.setTextColor(Color.BLACK);
        validCont_.validsUpdate(switchString, true)
    }
}






//todo допилить регексы, там пропускает всякие небуквенные символы
// todo вытащить все чеки отсюда в отдельный класс