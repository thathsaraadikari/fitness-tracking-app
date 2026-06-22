package com.example.fitnesstracker

import android.content.Intent
import android.os.Bundle
import android.util.Log
import android.widget.Button
import android.widget.EditText
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.MaterialTheme
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.ComposeView
import androidx.compose.ui.unit.dp
import com.example.fitnesstracker.ui.components.CenteredButtonList
import com.example.fitnesstracker.ui.components.CounterButtonComposable
import com.example.fitnesstracker.ui.components.FixedHelloText
import com.example.fitnesstracker.ui.components.ResumeStateMessage
import com.example.fitnesstracker.ui.components.UserCard

class HomeActivity : BaseActivity() {

    private lateinit var emailInput: EditText

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_home)
        Log.d(TAG, "onCreate: HomeActivity created")

        emailInput = findViewById(R.id.email_input)

        findViewById<Button>(R.id.btn_to_workout).setOnClickListener {
            Log.d(TAG, "Navigating HomeActivity -> WorkoutActivity")
            startActivity(Intent(this, WorkoutActivity::class.java))
        }

        findViewById<Button>(R.id.btn_to_summary).setOnClickListener {
            Log.d(TAG, "Navigating HomeActivity -> SummaryActivity")
            startActivity(Intent(this, SummaryActivity::class.java))
        }

        findViewById<ComposeView>(R.id.compose_home).setContent {
            MaterialTheme {
                Column(
                    modifier = Modifier
                        .fillMaxWidth()
                        .verticalScroll(rememberScrollState()),
                    verticalArrangement = Arrangement.spacedBy(12.dp)
                ) {
                    ResumeStateMessage()
                    UserCard(name = getString(R.string.user_name), status = getString(R.string.status_active))
                    FixedHelloText()
                    CounterButtonComposable()
                    CenteredButtonList(buttonLabels = listOf("Start", "Plan", "Profile"))
                }
            }
        }
    }

    override fun onRestoreInstanceState(savedInstanceState: Bundle) {
        super.onRestoreInstanceState(savedInstanceState)
        val restoredEmail = savedInstanceState.getString(STATE_EMAIL).orEmpty()
        emailInput.setText(restoredEmail)
        Log.d(TAG, "onRestoreInstanceState: restored email=$restoredEmail")
    }

    override fun onSaveInstanceState(outState: Bundle) {
        outState.putString(STATE_EMAIL, emailInput.text?.toString().orEmpty())
        Log.d(TAG, "onSaveInstanceState: saved email=${emailInput.text}")
        super.onSaveInstanceState(outState)
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
        private const val TAG = "HomeActivity"
        private const val STATE_EMAIL = "state_email"
    }
}
