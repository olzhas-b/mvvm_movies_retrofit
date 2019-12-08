package com.example.endterm.Presentation.Movie.MovieFavorite

import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.endterm.Base.BaseViewModel
import com.example.endterm.Data.Models.Movie.MovieData
import com.example.endterm.Data.Repository.MovieRepositoryImpl
import com.example.endterm.Domain.Repository.MovieRepository
import com.example.endterm.Utils.AppPreferences
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext


class FavoriteViewModel : BaseViewModel() {
    val liveData = MutableLiveData<State>()
    private val movieRepository: MovieRepository = MovieRepositoryImpl()


    fun loadMovies(page: Int = 1, sessionId: String) {
        uiScope.launch {
            withContext(coroutineContext) {
                val response = movieRepository.getFavoriteMovies(page, sessionId)
                val list = response?.results ?: emptyList()
                liveData.postValue(State.Result(list))
            }
        }
    }


    sealed class State {
        data class Result(val list: List<MovieData>): State()
    }
}
