package com.example.calc

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import com.example.calc.databinding.ActivityMainBinding;

class MainActivity : AppCompatActivity(), View.OnClickListener {
    private lateinit var binding: ActivityMainBinding;

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        binding.buttonNum0.setOnClickListener(this);
        binding.buttonNum1.setOnClickListener(this);
        binding.buttonNum2.setOnClickListener(this);
        binding.buttonNum3.setOnClickListener(this);
        binding.buttonNum4.setOnClickListener(this);
        binding.buttonNum5.setOnClickListener(this);
        binding.buttonNum6.setOnClickListener(this);
        binding.buttonNum7.setOnClickListener(this);
        binding.buttonNum8.setOnClickListener(this);
        binding.buttonNum9.setOnClickListener(this);
        binding.buttonDot.setOnClickListener(this);
        binding.buttonLeftBracket.setOnClickListener(this);
        binding.buttonRightBracket.setOnClickListener(this);
        binding.buttonOpDivide.setOnClickListener(this);
        binding.buttonOpEquals.setOnClickListener(this);
        binding.buttonOpMinus.setOnClickListener(this);
        binding.buttonOpMultiply.setOnClickListener(this);
        binding.buttonOpPlus.setOnClickListener(this);
    }

    override fun onClick(view: View) {
        when (view){
            binding.buttonNum0 -> {
                binding.textViewInput.append("0");
            }
            binding.buttonNum1 -> {
                binding.textViewInput.append("1");
            }
            binding.buttonNum2 -> {
                binding.textViewInput.append("2");
            }
            binding.buttonNum3 -> {
                binding.textViewInput.append("3");
            }
            binding.buttonNum4 -> {
                binding.textViewInput.append("4");
            }
            binding.buttonNum5 -> {
                binding.textViewInput.append("5");
            }
            binding.buttonNum6 -> {
                binding.textViewInput.append("6");
            }
            binding.buttonNum7 -> {
                binding.textViewInput.append("7");
            }
            binding.buttonNum8 -> {
                binding.textViewInput.append("8");
            }
            binding.buttonNum9 -> {
                binding.textViewInput.append("9");
            }
            binding.buttonOpEquals -> {
                //////////////////////////////////////
            }
            binding.buttonOpDivide -> {
                binding.textViewInput.append("/");
            }
            binding.buttonOpMinus -> {
                binding.textViewInput.append("-");
            }
            binding.buttonOpMultiply -> {
                binding.textViewInput.append("*");
            }
            binding.buttonOpPlus -> {
                binding.textViewInput.append("+");
            }
            binding.buttonDot -> {
                binding.textViewInput.append(".");
            }
            binding.buttonLeftBracket -> {
                binding.textViewInput.append("(");
            }
            binding.buttonRightBracket -> {
                binding.textViewInput.append(")");
            }
        }
    }
}