package com.example.endterm.Presentation.Movie.MovieFavorite

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.endterm.Data.Models.Movie.MovieData
import com.example.endterm.Presentation.Movie.List.RecyclerViewAdapter

import com.example.endterm.R
import com.example.endterm.Utils.AppPreferences
import com.example.endterm.Utils.Constant

class FavoriteList : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel: FavoriteViewModel
    private lateinit var recyclerView: RecyclerView
    private val TAG = "Favorite___Fragment"

    private var sessionId: String? = null

    private val movieAdapter by lazy {
        RecyclerViewAdapter(
            movieClickListener = movieClickListener
        )
    }

    private val movieClickListener = object: RecyclerViewAdapter.MovieClickListener {
        override fun onMovieClick(movie: MovieData) {
            val bundle = Bundle()
            movie.id?.let {
                    id -> bundle.putInt(Constant.MOVIE_ID, id)
            }
            bundle.putBoolean(Constant.FAV_BOOLEAN, false)
            navController.navigate(
                R.id.action_favoriteList_to_movieDetails,
                bundle
            )
        }
    }
    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.movies_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        sessionId = activity?.applicationContext?.let { context ->  AppPreferences.getSessionId(context) }
        viewModel = ViewModelProviders.of(this).get(FavoriteViewModel::class.java)
        bindViews(view)
        setData()
        setAdapter()
    }
    private fun bindViews(view: View) = with(view) {
        navController = Navigation.findNavController(this)
        recyclerView = view.findViewById(R.id.recycler_view_movies)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
    }

    private fun setData() {
        sessionId?.let { id ->  viewModel.loadMovies(1, id)}
        viewModel.liveData.observe(viewLifecycleOwner, Observer { result->
            when(result){
                is FavoriteViewModel.State.Result -> {
                    movieAdapter.addItems(result.list)
                }
            }
        })
    }

    private fun setAdapter() {
        recyclerView.adapter = movieAdapter
    }
}

