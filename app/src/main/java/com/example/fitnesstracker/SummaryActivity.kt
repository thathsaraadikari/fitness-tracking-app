package com.example.fitnesstracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.platform.ComposeView
import com.example.fitnesstracker.ui.components.CenteredButtonList

class SummaryActivity : BaseActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_summary)
        Log.d(TAG, "onCreate: SummaryActivity created")

        findViewById<Button>(R.id.btn_home).setOnClickListener {
            Log.d(TAG, "Navigating SummaryActivity -> HomeActivity")
            startActivity(Intent(this, HomeActivity::class.java))
        }

        findViewById<Button>(R.id.btn_workout).setOnClickListener {
            Log.d(TAG, "Navigating SummaryActivity -> WorkoutActivity")
            startActivity(Intent(this, WorkoutActivity::class.java))
        }

        findViewById<ComposeView>(R.id.compose_summary).setContent {
            MaterialTheme {
                CenteredButtonList(buttonLabels = listOf("Daily", "Weekly", "Monthly"))
            }
        }
    }

    override fun onStart() {
        super.onStart()
        Log.d(TAG, "onStart")
    }

    override fun onResume() {
        super.onResume()
        Log.d(TAG, "onResume")
    }

    override fun onPause() {
        Log.d(TAG, "onPause")
        super.onPause()
    }

    override fun onStop() {
        Log.d(TAG, "onStop")
        super.onStop()
    }

    override fun onDestroy() {
        Log.d(TAG, "onDestroy")
        super.onDestroy()
    }

    companion object {
        private const val TAG = "SummaryActivity"
    }
}
