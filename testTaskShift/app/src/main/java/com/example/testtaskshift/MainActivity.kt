package com.example.testtaskshift

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

    fun checkInputsDate(year: Int, month: Int, day: Int, todayYear : Int, todayMonth: Int, todayDay : Int){
        if(((year == todayYear - 18) && (month > todayMonth || (month == todayMonth && day > todayDay)))
            || year > todayYear - 18){

            if (binding.textViewBirthdateWarn.visibility == View.INVISIBLE) {
                binding.editTextBirthdate.setTextColor(Color.RED);
                binding.textViewBirthdateWarn.visibility = View.VISIBLE;
                validCont.validsUpdate("Birthdate", false)
                Toast.makeText(applicationContext,
                    "False",
                    Toast.LENGTH_SHORT).show();
            }
        } else{
            binding.textViewBirthdateWarn.visibility = View.INVISIBLE;
            binding.editTextBirthdate.setTextColor(Color.BLACK);
            validCont.validsUpdate("Birthdate", true)
            Toast.makeText(applicationContext,
                "True",
                Toast.LENGTH_SHORT).show();
        }
    }

    fun checkInputsText(text : Editable?, switchString : String){
        val texts : String = text.toString();
        when (switchString){
            "Name" -> {
                if (texts.count() <= 2 || texts.contains("[0-9]".toRegex())){
                    if (binding.textViewNameWarn.visibility == View.INVISIBLE) {
                        binding.editTextName.setTextColor(Color.RED);
                        binding.textViewNameWarn.visibility = View.VISIBLE;
                        validCont.validsUpdate(switchString, false)
                    }

                } else {
                    if (binding.textViewNameWarn.visibility == View.VISIBLE) {
                        binding.textViewNameWarn.visibility = View.INVISIBLE;
                        binding.editTextName.setTextColor(Color.BLACK);
                        validCont.validsUpdate(switchString, true)
                        //todo вот тут и изменения валидов и работа с контроллером доступа кнопки и так во всех остальных
                    }
                }
            }
            "Surname" -> {
                if (texts.count() <= 2 || texts.contains("[0-9]".toRegex())){
                    if (binding.textViewSurnameWarn.visibility == View.INVISIBLE) {
                        binding.editTextSurname.setTextColor(Color.RED);
                        binding.textViewSurnameWarn.visibility = View.VISIBLE;
                        validCont.validsUpdate(switchString, false)
                    }

                } else {
                    if (binding.textViewSurnameWarn.visibility == View.VISIBLE) {
                        binding.textViewSurnameWarn.visibility = View.INVISIBLE;
                        binding.editTextSurname.setTextColor(Color.BLACK);
                        validCont.validsUpdate(switchString, true)
                        //todo вот тут и изменения валидов и работа с контроллером доступа кнопки и так во всех остальных
                    }
                }
            }
            "Lastname" -> {
                if (texts.count() <= 2 || texts.contains("[0-9]".toRegex())){
                    if (binding.textViewLastnameWarn.visibility == View.INVISIBLE) {
                        binding.editTextLastname.setTextColor(Color.RED);
                        binding.textViewLastnameWarn.visibility = View.VISIBLE;
                        validCont.validsUpdate(switchString, false)
                    }

                } else {
                    if (binding.textViewLastnameWarn.visibility == View.VISIBLE) {
                        binding.textViewLastnameWarn.visibility = View.INVISIBLE;
                        binding.editTextLastname.setTextColor(Color.BLACK);
                        validCont.validsUpdate(switchString, true)
                        //todo вот тут и изменения валидов и работа с контроллером доступа кнопки и так во всех остальных
                    }
                }
            }
            "PassInp" -> {
                if (texts.count() <= 6 || !texts.contains("[0-9]".toRegex()) || !texts.contains("[a-z]".toRegex()) || !texts.contains("[A-Z]".toRegex())) {
                    if (binding.textViewPassInpWarn.visibility == View.INVISIBLE) {
                        binding.editTextPassInp.setTextColor(Color.RED);
                        binding.textViewPassInpWarn.visibility = View.VISIBLE;
                        validCont.validsUpdate(switchString, false)

                    }
                } else {
                    if (binding.textViewPassInpWarn.visibility == View.VISIBLE) {
                        binding.textViewPassInpWarn.visibility = View.INVISIBLE;
                        binding.editTextPassInp.setTextColor(Color.BLACK);
                        validCont.validsUpdate(switchString, true)
                    }
                }
            }
            "PassCheck" -> {
                if (text.toString() != binding.editTextPassInp.text.toString()) {
                    if (binding.textViewPassCheckWarn.visibility == View.INVISIBLE) {
                        binding.editTextPassCheck.setTextColor(Color.RED);
                        binding.textViewPassCheckWarn.visibility = View.VISIBLE;
                        validCont.validsUpdate(switchString, false)
                    }

                } else {
                    if (binding.textViewPassCheckWarn.visibility == View.VISIBLE) {
                        binding.textViewPassCheckWarn.visibility = View.INVISIBLE;
                        binding.editTextPassCheck.setTextColor(Color.BLACK);
                        validCont.validsUpdate(switchString, true)
                    }
                }
            }

        }

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

    fun confirmAccessChecker(numAttribute : Int, validStatus : Boolean){

    }
}

/*{
                        override fun onDateSet(
                            view: DatePicker,
                            year: Int,
                            monthOfYear: Int,
                            dayOfMonth: Int
                        )
                        {
                            val editTextDateParam =
                                dayOfMonth.toString() + "." + (monthOfYear + 1) + "." + year
                            editTextDate.setText(editTextDateParam)
                        }
                    }, mYear, mMonth, mDay)
                datePickerDialog.show()*/




//todo допилить регексы, там пропускает всякие небуквенные символы
// todo вытащить все чеки отсюда в отдельный класс