package com.example.endterm.Presentation.Movie.Details

import androidx.lifecycle.MutableLiveData
import com.example.endterm.Base.BaseViewModel
import com.example.endterm.Data.Models.Movie.MovieData
import com.example.endterm.Data.Repository.MovieRepositoryImpl
import com.example.endterm.Domain.Repository.MovieRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext

class DetailViewModel : BaseViewModel(){

    val liveData = MutableLiveData<State>()
    private val movieRepository: MovieRepository = MovieRepositoryImpl()


    fun getMovie(id: Int) {
        uiScope.launch {
            liveData.value = State.ShowLoading
            withContext(Dispatchers.IO) {
                val movie = movieRepository.getMovie(id)
                movie?.let { movie ->
                    liveData.postValue(State.Result(movie))
                }
            }
            liveData.value = State.HideLoading
        }
    }

    fun setFavorite(movieId: Int, sessionId: String, favorite: Boolean) {
        uiScope.launch {
            withContext(Dispatchers.IO) {
                val message = movieRepository.markFavorite(movieId, sessionId, favorite)?.statusMessage
                message?.let { result ->
                    liveData.postValue(State.Favorite(result))
                }
            }
        }
    }
//    fun delateFavorite(movieId: Int, sessionId: String) {
//        uiScope.launch {
//            withContext(Dispatchers.IO) {
//                val message = movieRepository.markFavorite(movieId, sessionId, favorite)?.statusMessage
//                message?.let { result ->
//                    liveData.postValue(State.Favorite(result))
//                }
//            }
//        }
//    }

    sealed class State {
        object ShowLoading: State()
        object HideLoading: State()
        data class Result(val movie: MovieData): State()
        data class Favorite(val result: String): State()
    }
}