package com.example.endterm.Base

import androidx.lifecycle.ViewModel
import kotlinx.coroutines.CoroutineScope
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.SupervisorJob
import kotlin.coroutines.CoroutineContext

abstract class BaseViewModel: ViewModel() {

    private val job = SupervisorJob()

    private val coroutineContext: CoroutineContext
        get() = Dispatchers.Main + job

    protected val uiScope: CoroutineScope = CoroutineScope(coroutineContext)

    override fun onCleared() {
        super.onCleared()
        job.cancel()
    }
}