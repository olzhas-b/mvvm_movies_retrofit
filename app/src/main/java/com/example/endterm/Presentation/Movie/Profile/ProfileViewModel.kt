package com.example.endterm.Presentation.Movie.Profile

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endterm.Base.BaseViewModel
import com.example.endterm.Data.Models.Movie.MovieData
import com.example.endterm.Data.Models.User.ProfileResponse
import com.example.endterm.Data.Repository.UserRepositoryImpl
import com.example.endterm.Domain.Repository.UserRepository
import com.example.endterm.Presentation.Movie.MovieFavorite.FavoriteViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class ProfileViewModel : BaseViewModel() {
    // TODO: Implement the ViewModel
    val liveData = MutableLiveData<State>()
    private val profileRepository: UserRepository = UserRepositoryImpl()

    fun loadInformation(sessionId: String) {
        uiScope.launch {
            withContext(coroutineContext) {
                val response = profileRepository.getProfileInformation(sessionId)
                val name = response?.name
                val username = response?.username
                liveData.postValue(State.Result(name, username))
            }
        }
    }
    sealed class State {
        data class Result(val name : String?, val username : String?): State()
    }
}
