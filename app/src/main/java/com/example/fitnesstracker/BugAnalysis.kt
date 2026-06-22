package com.example.fitnesstracker

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch

/**
 * Requirement note:
 * If a UI function is missing @Composable, Compose treats it as a normal Kotlin function and
 * it cannot be invoked from other composables, producing compile-time errors.
 *
 * Bug analysis for random crash reports:
 * Using `this` inside a coroutine in a ViewModel can accidentally capture the ViewModel instance
 * or an Activity context reference that is no longer valid. If that stale context is used after
 * the app returns from background, memory leaks and crashes may occur.
 */
class SafeContextViewModel(application: Application) : AndroidViewModel(application) {

    fun doBackgroundWorkSafely() {
        val appContext = getApplication<Application>().applicationContext
        viewModelScope.launch(Dispatchers.IO) {
            // Safe: uses application context, not an Activity `this` reference.
            appContext.packageName
        }
    }
}
