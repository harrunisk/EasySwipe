package com.nstudiosappdev.easyswipeexample

import android.os.Bundle
import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import com.nstudiosappdev.easyswipeexample.R.*
import kotlinx.android.synthetic.main.activity_main.*
import nstudiosappdev.android.view.EasySwipeListener

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(layout.activity_main)

        easySwipe.setListener(object : EasySwipeListener {

            override fun onAccepted() {
                Toast.makeText(applicationContext, "Accepted!", Toast.LENGTH_SHORT).show()
            }

            override fun onRejected() {
                Toast.makeText(applicationContext, "Rejected!", Toast.LENGTH_SHORT).show()
            }
        })

    }
}
