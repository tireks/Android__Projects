package com.example.clcklist

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.ArrayAdapter
import android.widget.Button
import android.widget.TextView
import androidx.constraintlayout.widget.ConstraintLayout
import com.example.clcklist.databinding.ActivityMainBinding

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding
    lateinit var button4sides: Button;
    lateinit var button6sides: Button;
    lateinit var button8sides: Button;
    lateinit var button10sides: Button;
    lateinit var button12sides: Button;
    lateinit var button20sides: Button;
    lateinit var button100sides: Button;
    lateinit var outputTV: TextView;
    lateinit var buttonGhost: Button;
    lateinit var myLayout: ConstraintLayout;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        binding.button20s.setOnClickListener {

        }
        with(binding){
            button20s
        }

        button4sides = findViewById<Button>(R.id.button4s);
        button6sides = findViewById<Button>(R.id.button6s);
        button8sides = findViewById<Button>(R.id.button8s);
        button10sides = findViewById<Button>(R.id.button10s);
        button12sides = findViewById<Button>(R.id.button12s);
        button20sides = findViewById<Button>(R.id.button20s);
        button100sides = findViewById<Button>(R.id.button100s);
        button4sides.setOnClickListener(this);
        button6sides.setOnClickListener(this);
        button8sides.setOnClickListener(this);
        button10sides.setOnClickListener(this);
        button12sides.setOnClickListener(this);
        button20sides.setOnClickListener(this);
        button100sides.setOnClickListener(this);
        outputTV = findViewById(R.id.outputTextView);
        buttonGhost = findViewById(R.id.buttonGhost);
        buttonGhost.setOnClickListener(this);
        buttonGhost.setOnClickListener {

        }
        //myLayout = ConstraintLayout(applicationContext);
        myLayout = findViewById(R.id.mainlayout);
    }

    fun rollDice(view: View) {
        var diceResult: Int;
        if (view == button4sides) {
            diceResult = (1..4).random();
            outputTV.text = diceResult.toString();
            buttonGhost.visibility = View.VISIBLE;
            myLayout.setBackgroundResource(R.drawable.dice_d4_03);
        }
        if (view == button6sides) {
            diceResult = (1..6).random();
            outputTV.text = diceResult.toString();
            buttonGhost.visibility = View.VISIBLE;
        }
        if (view == button8sides) {
            diceResult = (1..8).random();
            outputTV.setText(diceResult.toString());
            buttonGhost.visibility = View.VISIBLE;
        }
        if (view == button10sides) {
            diceResult = (1..10).random();
            outputTV.setText(diceResult.toString());
            buttonGhost.visibility = View.VISIBLE;
        }
        if (view == button12sides) {
            diceResult = (1..12).random();
            outputTV.setText(diceResult.toString());
            buttonGhost.visibility = View.VISIBLE;
        }
        if (view == button20sides) {
            diceResult = (1..20).random();
            outputTV.setText(diceResult.toString());
            buttonGhost.visibility = View.VISIBLE;
        }
        if (view == button100sides) {
            diceResult = (1..100).random();
            outputTV.setText(diceResult.toString());
            buttonGhost.visibility = View.VISIBLE;
        }
        if (view == buttonGhost) {
            outputTV.text = "";
            buttonGhost.visibility = View.INVISIBLE;
        }

    }

    override fun onClick(view: View) {
        rollDice(view);
    }
}