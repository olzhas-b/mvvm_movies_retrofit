package com.example.endterm.Presentation.Movie.Details

import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.Toast
import androidx.lifecycle.Observer
import androidx.lifecycle.ViewModelProviders
import com.bumptech.glide.Glide

import com.example.endterm.R
import com.example.endterm.Utils.AppPreferences
import com.example.endterm.Utils.Constant
import kotlinx.android.synthetic.main.fragment_movie_details.*

class MovieDetails : Fragment() {
    private lateinit var viewModel: DetailViewModel
    private var movieId: Int? = null
    private var favorite: Boolean = true

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.fragment_movie_details, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        bindViews()

        val sessionId = activity?.applicationContext?.let { context ->  AppPreferences.getSessionId(context) }
        btnFavorite.setOnClickListener {
            movieId?.let { movieId ->
                sessionId?.let { sessionId ->
                    viewModel.setFavorite(movieId, sessionId, favorite)
                }
            }

        }
        setData()

    }

    private fun bindViews() {
        movieId = arguments?.getInt(Constant.MOVIE_ID)
        favorite = arguments?.getBoolean(Constant.FAV_BOOLEAN) ?: true
        viewModel = ViewModelProviders.of(this).get(DetailViewModel::class.java)
    }
    private fun setData() {
        movieId?.let { movieId ->
            viewModel.getMovie(movieId)
        }
        viewModel.liveData.observe(viewLifecycleOwner, Observer { result ->
            when(result) {
                is DetailViewModel.State.ShowLoading -> {
                    progressBar.visibility = View.VISIBLE
                }
                is DetailViewModel.State.HideLoading -> {
                    progressBar.visibility = View.GONE
                }
                is DetailViewModel.State.Result -> {
                    val imageUrl = "${Constant.BACKDROP_BASE_URL}${result.movie.backdropImg}"
                    Glide.with(this)
                        .load(imageUrl)
                        .into(ivBackdrop)
                    tvName.text = result.movie.title
                    tvRating.text = "${result.movie.vote_average}/10"
                    tvOverview.text = result.movie.overview
                }
                is DetailViewModel.State.Favorite -> {
                    Toast.makeText(context, result.result, Toast.LENGTH_LONG).show()
                }
            }
        })

    }
}
