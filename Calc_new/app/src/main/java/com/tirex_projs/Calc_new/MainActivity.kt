package com.tirex_projs.Calc_new

import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import com.tirex_projs.Calc_new.databinding.ActivityMainBinding;

class MainActivity : AppCompatActivity(){
    private lateinit var binding: ActivityMainBinding;
    private var bracketsCounter: Int = 0;
    private var blocker: Blocker = Blocker();
    private var cheker: Cheker = Cheker();

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        blocker.startInputBlock(binding)
    }

    fun opButtonOnClick(view: View){
        if (!blocker.isBlocked(view)) {
            blocker.smartBlocker(view, binding)
            blocker.smartUnblocker(view, binding)
            if (view is Button){
                binding.TVInput.append(view.text)
            }
        }

    }
    fun numberButtonOnClick(view: View){
        if (!blocker.isBlocked(view)) {
            blocker.smartBlocker(view, binding)
            blocker.smartUnblocker(view, binding)
            if (view is Button) {
                binding.TVInput.append(view.text)
            }
            /*when (view) {
                binding.dotButton -> {
                    //if (!blocker.isBlocked(view)){
                    blocker.smartBlocker(view, binding)
                    //}
                }
            }*/
        }
    }
    fun acButtonOnClick(view: View){
        if (view is Button){
            binding.TVInput.text = ""
            binding.TVResult.text = ""
        }

        blocker.startInputBlock(binding)
    }
    fun delButtonOnClick(view: View){
        if (binding.TVInput.text.length > 1) {
            var length : Int = binding.TVInput.length()
            var delSymbol : String = binding.TVInput.text.substring(length - 1, length)
            if (view is Button){
                binding.TVInput.text = binding.TVInput.text.substring(0,length - 1)
            }
            length = binding.TVInput.length()
            //val newLastSymbol : String =
            blocker.symbolicBlocker(binding, binding.TVInput.text.substring(length - 1, length), delSymbol)
        } else {
            acButtonOnClick(binding.acButton)
        }

    }
    fun equButtonOnClick(view: View){
        when (cheker.fullCheck(binding.TVInput.text.toString())){
            0 -> {
                //all is ok
            }
            1 -> {
                //open modal window
            }
            2 -> {
                //open modal window
            }
        }
    }

}