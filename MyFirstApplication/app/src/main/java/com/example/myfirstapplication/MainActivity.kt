package com.example.myfirstapplication

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.view.View
import android.widget.EditText
import android.widget.TextView

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
    }
    fun ConvKmsToMiles(view: View) {
        val textBoxInput = findViewById<EditText>(R.id.editTextTextKMs);
        val textBoxOutput = findViewById<EditText>(R.id.editTextTextMiles);
        val KMsDataDouble = textBoxInput.text.toString().toDouble();
        val milesDataString = (KMsDataDouble * 0.62137).toString();
        textBoxOutput.setText(milesDataString);
    }
    fun ConvMilesToKMs(view: View) {
        val textBoxInput = findViewById<EditText>(R.id.editTextTextMiles);
        val textBoxOutput = findViewById<EditText>(R.id.editTextTextKMs);
        val milesDataDouble = textBoxInput.text.toString().toDouble();
        val kmsDataString = (milesDataDouble / 0.62137).toString();
        textBoxOutput.setText(kmsDataString);
    }
}