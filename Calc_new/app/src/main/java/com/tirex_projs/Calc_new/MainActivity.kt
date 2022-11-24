package com.tirex_projs.Calc_new
import android.content.Context
import android.graphics.drawable.BitmapDrawable
import android.os.Bundle
import android.view.Gravity
import android.view.View
import android.view.WindowManager
import android.widget.Button
import android.widget.LinearLayout
import android.widget.PopupWindow
import androidx.appcompat.app.AppCompatActivity
import com.tirex_projs.Calc_new.databinding.ActivityMainBinding
import com.tirex_projs.Calc_new.databinding.PopupWindowBinding


class MainActivity : AppCompatActivity(),  View.OnClickListener {
    private lateinit var binding: ActivityMainBinding;
    private lateinit var bindingPop: PopupWindowBinding;
    private var bracketsCounter: Int = 0;
    private var blocker: Blocker = Blocker();
    private var cheker: Cheker = Cheker();

    override fun onCreate(savedInstanceState: Bundle?){
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)
        blocker.startInputBlock(binding)
        binding.equButton.setOnClickListener(this)
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

    private fun PopupWindow.dimBehind() {
        val container = contentView.rootView
        val context = contentView.context
        val wm = context.getSystemService(Context.WINDOW_SERVICE) as WindowManager
        val p = container.layoutParams as WindowManager.LayoutParams
        p.flags = p.flags or WindowManager.LayoutParams.FLAG_DIM_BEHIND
        p.dimAmount = 0.9f
        wm.updateViewLayout(container, p)
    }

    private fun showPopUp(warnString: String, view: View) : PopupWindow{
        bindingPop = PopupWindowBinding.inflate(layoutInflater)
        bindingPop.myTV.text = warnString;
        val wid = LinearLayout.LayoutParams.WRAP_CONTENT
        val high = LinearLayout.LayoutParams.WRAP_CONTENT
        val focus = true
        val popupWindow = PopupWindow(bindingPop.root, wid, high, false)
        popupWindow.showAtLocation(view, Gravity.CENTER, 0,0)
        popupWindow.dimBehind()
        popupWindow.isOutsideTouchable = false;
        popupWindow.isTouchable = true;
        bindingPop.OkButton.setOnClickListener(object : View.OnClickListener {
            override fun onClick(v: View?) {
                popupWindow.dismiss()
            }
        })
        return popupWindow
    }

    override fun onClick(view: View) {
        var popupWindow : PopupWindow = PopupWindow()
        if (!blocker.isBlocked(view)){
            when (val errorCode = cheker.fullCheck(binding.TVInput.text.toString())){
                0 -> {
                //all is ok
                }
                1,2 -> {
                    var warnString = ""
                    warnString = if (errorCode == 1){
                        "sorry, the quantity '(' and ')' doesn't seem to match"
                    }else {
                        "sorry, it seems the expression contains unnecessary brackets"
                    }
                    popupWindow = showPopUp(warnString, view)
                }
            }

        }
    }

}