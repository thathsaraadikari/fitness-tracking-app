package com.example.fitnesstracker

import android.util.Log
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity

open class BaseActivity : AppCompatActivity() {
    override fun onResume() {
        super.onResume()
        Toast.makeText(this, "${this::class.java.simpleName} resumed", Toast.LENGTH_SHORT).show()
        Log.d("BaseActivity", "${this::class.java.simpleName} onResume toast shown")
    }
}
