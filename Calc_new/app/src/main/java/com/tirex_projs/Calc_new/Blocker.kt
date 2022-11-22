package com.tirex_projs.Calc_new
import android.graphics.Color
import com.tirex_projs.Calc_new.databinding.ActivityMainBinding;
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import androidx.core.text.isDigitsOnly

class Blocker {
    private val blockedList : ArrayList<Button> = arrayListOf();
    private var bracketCounter : Int = 0;
    private var canDotBeUnblocked : Boolean = true;
    private fun blockButton(view: View){
        if (!blockedList.contains(view)){
            if (view is Button) {
                //view.visibility = View.INVISIBLE
                blockedList.add(view);
            }
        }
    }
    private fun unblockButton(view: View){
        if (blockedList.contains(view)){
            if (view is Button) {
                //view.visibility = View.VISIBLE
                blockedList.remove(view)
            }
        }
    }

    fun smartBlocker(view: View, binding: ActivityMainBinding){
        if (view is Button) {
            if (view.text.isDigitsOnly()) {
                blockButton(binding.bracketLeftButton)
            }else when (view.text){
                "." -> {
                    blockButton(binding.bracketLeftButton)
                    blockButton(binding.bracketRightButton)
                    blockButton(binding.plusButton)
                    blockButton(binding.minusButton)
                    blockButton(binding.multiplyButton)
                    blockButton(binding.divideButton)
                    blockButton(binding.equButton)
                    blockButton(binding.dotButton)
                }
                "(" -> {
                    blockButton(binding.plusButton)
                    blockButton(binding.multiplyButton)
                    blockButton(binding.divideButton)
                    blockButton(binding.equButton)
                    blockButton(binding.dotButton)
                    blockButton(binding.bracketRightButton)
                    bracketCounter++;
                }
                ")" -> {
                    blockButton(binding.dotButton)
                    blockButton(binding.bracketLeftButton)
                    blockNumsButtons(binding)
                    bracketCounter--;
                }
                "x" -> {
                    blockButton(binding.dotButton)
                    blockButton(binding.equButton)
                    blockButton(binding.plusButton)
                    blockButton(binding.minusButton)
                    blockButton(binding.divideButton)
                    blockButton(binding.multiplyButton)
                    blockButton(binding.bracketRightButton)

                }
                "/" -> {
                    blockButton(binding.dotButton)
                    blockButton(binding.equButton)
                    blockButton(binding.plusButton)
                    blockButton(binding.minusButton)
                    blockButton(binding.multiplyButton)
                    blockButton(binding.divideButton)
                    blockButton(binding.bracketRightButton)
                }
                "+" -> {
                    blockButton(binding.dotButton)
                    blockButton(binding.equButton)
                    blockButton(binding.minusButton)
                    blockButton(binding.multiplyButton)
                    blockButton(binding.divideButton)
                    blockButton(binding.plusButton)
                    blockButton(binding.bracketRightButton)
                }
                "-" -> {
                    blockButton(binding.dotButton)
                    blockButton(binding.equButton)
                    blockButton(binding.plusButton)
                    blockButton(binding.multiplyButton)
                    blockButton(binding.divideButton)
                    blockButton(binding.minusButton)
                    blockButton(binding.bracketRightButton)
                }
            }
            if (bracketCounter <= 0){
                blockButton(binding.bracketRightButton)
            }
        }
    }

    fun smartUnblocker(view: View, binding: ActivityMainBinding){
        if (view is Button) {
            if (view.text.isDigitsOnly()) {
                unblockButton(binding.equButton)
                unblockButton(binding.divideButton)
                unblockButton(binding.multiplyButton)
                unblockButton(binding.plusButton)
                unblockButton(binding.minusButton)
                if (canDotBeUnblocked){
                    unblockButton(binding.dotButton)
                    canDotBeUnblocked = false
                }
                if (bracketCounter > 0){
                    unblockButton(binding.bracketRightButton)
                }
            }
            else {
                when (view.text) {
                    "(" -> {
                        unblockButton(binding.minusButton)
                    }
                    ")" -> {
                        unblockButton(binding.divideButton)
                        unblockButton(binding.multiplyButton)
                        unblockButton(binding.plusButton)
                        unblockButton(binding.minusButton)
                        unblockButton(binding.equButton)
                    }
                    "+" -> {
                        unblockButton(binding.bracketLeftButton)
                        unblockNumsButtons(binding)
                        canDotBeUnblocked = true
                    }
                    "-" -> {
                        unblockButton(binding.bracketLeftButton)
                        unblockNumsButtons(binding)
                        canDotBeUnblocked = true
                    }
                    "x" -> {
                        unblockButton(binding.bracketLeftButton)
                        unblockNumsButtons(binding)
                        canDotBeUnblocked = true
                    }
                    "/" -> {
                        unblockButton(binding.bracketLeftButton)
                        unblockNumsButtons(binding)
                        canDotBeUnblocked = true
                    }
                }
            }


        }
    }

    fun startInputBlock(binding: ActivityMainBinding){
        blockButton(binding.dotButton)
        blockButton(binding.equButton)
        blockButton(binding.plusButton)
        blockButton(binding.multiplyButton)
        blockButton(binding.divideButton)
        blockButton(binding.bracketRightButton)
        unblockNumsButtons(binding)
        unblockButton(binding.bracketLeftButton)
        bracketCounter = 0;
        canDotBeUnblocked = true;
    }

    fun isBlocked(view: View) : Boolean {
        return blockedList.contains(view)
    }

    private fun blockNumsButtons(binding: ActivityMainBinding){
        blockButton(binding.Button0)
        blockButton(binding.Button1)
        blockButton(binding.Button2)
        blockButton(binding.Button3)
        blockButton(binding.Button4)
        blockButton(binding.Button5)
        blockButton(binding.Button6)
        blockButton(binding.Button7)
        blockButton(binding.Button8)
        blockButton(binding.Button9)
    }

    private fun unblockNumsButtons(binding: ActivityMainBinding){
        unblockButton(binding.Button0)
        unblockButton(binding.Button1)
        unblockButton(binding.Button2)
        unblockButton(binding.Button3)
        unblockButton(binding.Button4)
        unblockButton(binding.Button5)
        unblockButton(binding.Button6)
        unblockButton(binding.Button7)
        unblockButton(binding.Button8)
        unblockButton(binding.Button9)
    }

    fun symbolicBlocker(binding: ActivityMainBinding, symbol: String, symbolDeleted: String) {
        if ((symbolDeleted == "+") or (symbolDeleted == "-")
            or (symbolDeleted == "x") or (symbolDeleted == "/"))
        {
            canDotBeUnblocked = false
        } else when (symbolDeleted) {
            "." -> {
                canDotBeUnblocked = true
            }
            "(" -> {
                bracketCounter--
            }
            ")" -> {
                bracketCounter++
            }
        }
            if (symbol.isDigitsOnly()) {
            smartBlocker(binding.Button0, binding) //no matter which num button it should be
            smartUnblocker(binding.Button0, binding) //no matter which num button it should be
        } else when (symbol){
                "+" -> {
                    smartBlocker(binding.plusButton, binding)
                    smartUnblocker(binding.plusButton, binding)
                }
                "-" -> {
                    smartBlocker(binding.minusButton, binding)
                    smartUnblocker(binding.minusButton, binding)
                }
                "x" -> {
                    smartBlocker(binding.multiplyButton, binding)
                    smartUnblocker(binding.multiplyButton, binding)
                }
                "/" -> {
                    smartBlocker(binding.divideButton, binding)
                    smartUnblocker(binding.divideButton, binding)
                }
                "(" -> {
                    bracketCounter--;
                    smartBlocker(binding.bracketLeftButton, binding)
                    smartUnblocker(binding.bracketLeftButton, binding)
                }
                ")" -> {
                    bracketCounter++;
                    smartBlocker(binding.bracketRightButton, binding)
                    smartUnblocker(binding.bracketRightButton, binding)
                }

        }

    }
}