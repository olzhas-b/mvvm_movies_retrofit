package com.example.endterm.Presentation.Login

import android.util.Log
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endterm.Base.BaseViewModel
import com.example.endterm.Data.Repository.UserRepositoryImpl
import com.example.endterm.Domain.Repository.UserRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class LoginViewModel: BaseViewModel() {

    val liveData = MutableLiveData<State>()

    private val userRepository: UserRepository = UserRepositoryImpl()

    fun login(username: String, password: String) {
        uiScope.launch {
            liveData.value = State.ShowLoading
            withContext(Dispatchers.IO) {
                val token = userRepository.createToken().requestToken
                token?.let { token ->
                    val success = userRepository.login(username, password, token).success
                    val sessionId = userRepository.createSession(token).sessionId
                    liveData.postValue(
                        success?.let { success ->
                            sessionId?.let { sessionId ->
                                State.Result(success, sessionId)
                            }
                        }
                    )
                }
            }
            liveData.value = State.HideLoading
        }
    }

    sealed class State {
        object ShowLoading: State()
        object HideLoading: State()
        data class Result(val success: Boolean, val sessionId: String): State()
    }
}