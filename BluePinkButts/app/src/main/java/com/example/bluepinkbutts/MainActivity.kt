package com.example.bluepinkbutts

//import android.support.constraint.ConstraintSet
import android.graphics.Color
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.constraintlayout.widget.ConstraintSet;
import androidx.constraintlayout.widget.ConstraintLayout;

//import android.support.constraint.ConstraintLayout


class MainActivity  : AppCompatActivity(), View.OnClickListener {
    lateinit var buttonBlue: Button
    lateinit var buttonPink: Button


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main);
        buttonBlue = findViewById<Button>(R.id.buttonBlueInvincible);
        buttonBlue.setOnClickListener(this);
        //buttonPink = findViewById<Button>(R.id.buttonPink);
        //buttonPink.setOnClickListener(this);
    }


    fun toDo(view: View) {
        if (view == buttonBlue) {
            view.visibility = View.INVISIBLE;
            buttonPink = Button(applicationContext);
            buttonPink.text = "Pink";
            buttonPink.setBackgroundColor(Color.parseColor("#FD9BF3"));
            buttonPink.setTextColor(Color.BLACK);
            buttonPink.id = View.generateViewId();
            buttonPink.setOnClickListener(this);
            val mYLayout = ConstraintLayout(applicationContext);
            mYLayout.setBackgroundColor(Color.WHITE);
            mYLayout.addView(buttonPink);
            setContentView(mYLayout);
            val conSet = ConstraintSet();
            conSet.constrainHeight(buttonPink.id, ConstraintSet.WRAP_CONTENT);
            conSet.constrainWidth(buttonPink.id, ConstraintSet.WRAP_CONTENT);
            conSet.connect(buttonPink.id,ConstraintSet.LEFT,
                ConstraintSet.PARENT_ID, ConstraintSet.LEFT, 0);
            conSet.connect(buttonPink.id,ConstraintSet.RIGHT,
                ConstraintSet.PARENT_ID, ConstraintSet.RIGHT, 0);
            conSet.connect(buttonPink.id,ConstraintSet.TOP,
                ConstraintSet.PARENT_ID, ConstraintSet.TOP, 0);
            conSet.connect(buttonPink.id,ConstraintSet.BOTTOM,
                ConstraintSet.PARENT_ID, ConstraintSet.BOTTOM, 0);
            conSet.setHorizontalBias(buttonPink.id, 0.949f);
            conSet.setVerticalBias(buttonPink.id, 0.023f);
            conSet.applyTo(mYLayout);

        }
        if (view.equals(buttonPink)) {
            //view.visibility = View.INVISIBLE;
            Toast.makeText(applicationContext, "to do to do to do", Toast.LENGTH_LONG).show();
        }

    }

    override fun onClick(view: View) {
        toDo(view);
    }
}
/* <Button
        android:id="@+id/buttonPink"
        android:layout_width="wrap_content"
        android:layout_height="wrap_content"
        android:backgroundTint="#CD7575"
        android:onClick="toDo"
        android:text="Pink"
        android:textColor="#4E342E"
        app:layout_constraintBottom_toBottomOf="parent"
        app:layout_constraintEnd_toEndOf="parent"
        app:layout_constraintHorizontal_bias="0.949"
        app:layout_constraintStart_toStartOf="parent"
        app:layout_constraintTop_toTopOf="parent"
        app:layout_constraintVertical_bias="0.023"
        app:strokeColor="#405DA5" />*/

