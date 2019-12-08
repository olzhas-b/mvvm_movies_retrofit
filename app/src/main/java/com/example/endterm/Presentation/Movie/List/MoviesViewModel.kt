package com.example.endterm.Presentation.Movie.List

import androidx.lifecycle.MutableLiveData
import com.example.endterm.Base.BaseViewModel
import com.example.endterm.Data.Models.Movie.MovieData
import com.example.endterm.Data.Repository.MovieRepositoryImpl
import com.example.endterm.Domain.Repository.MovieRepository
import kotlinx.coroutines.*

class MoviesViewModel : BaseViewModel() {
    val liveData = MutableLiveData<State>()
    private val movieRepository: MovieRepository = MovieRepositoryImpl()


    init {
        loadMovies()
    }
    fun loadMovies(page: Int = 1) {
        uiScope.launch {
            withContext(coroutineContext) {
                val response = movieRepository.getPopularMovies(page)
                val list = response?.results ?: emptyList()
                liveData.postValue(State.Result(list))
            }
        }
    }


    sealed class State {
        data class Result(val list: List<MovieData>): State()
    }
}
