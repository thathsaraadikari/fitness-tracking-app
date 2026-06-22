package com.example.fitnesstracker

import android.app.AlertDialog
import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import androidx.activity.OnBackPressedCallback
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.ui.components.CounterButtonComposable
import com.example.fitnesstracker.ui.components.ResumeStateMessage
import com.example.fitnesstracker.ui.components.UserCard

class WorkoutActivity : BaseActivity() {
    private lateinit var backPressCallback: OnBackPressedCallback

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_workout)
        Log.d(TAG, "onCreate: WorkoutActivity created")

        findViewById<Button>(R.id.btn_home).setOnClickListener {
            Log.d(TAG, "Navigating WorkoutActivity -> HomeActivity")
            startActivity(Intent(this, HomeActivity::class.java))
        }

        findViewById<Button>(R.id.btn_summary).setOnClickListener {
            Log.d(TAG, "Navigating WorkoutActivity -> SummaryActivity")
            startActivity(Intent(this, SummaryActivity::class.java))
        }

        findViewById<ComposeView>(R.id.compose_workout).setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier.fillMaxWidth(),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ResumeStateMessage()
                    UserCard(name = "Workout User", status = "Status: In Session")
                    CounterButtonComposable()
                }
            }
        }

        backPressCallback = object : OnBackPressedCallback(true) {
            override fun handleOnBackPressed() {
                showBackConfirmation()
            }
        }
        onBackPressedDispatcher.addCallback(this, backPressCallback)
    }

    private fun showBackConfirmation() {
        AlertDialog.Builder(this)
            .setTitle(getString(R.string.back_confirm_title))
            .setMessage(getString(R.string.back_confirm_message))
            .setPositiveButton(getString(R.string.confirm_yes)) { _, _ ->
                Log.d(TAG, "Back confirmed by user")
                backPressCallback.isEnabled = false
                onBackPressedDispatcher.onBackPressed()
            }
            .setNegativeButton(getString(R.string.confirm_no)) { dialog, _ ->
                Log.d(TAG, "Back cancelled by user")
                dialog.dismiss()
            }
            .show()
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
        private const val TAG = "WorkoutActivity"
    }
}
