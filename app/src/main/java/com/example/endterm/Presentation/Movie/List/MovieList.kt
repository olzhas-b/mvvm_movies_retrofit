package com.example.endterm.Presentation.Movie.List

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.lifecycle.Observer
import androidx.navigation.NavController
import androidx.navigation.Navigation
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.endterm.*
import com.example.endterm.Data.Models.Movie.MovieData
import com.example.endterm.Utils.Constant


class MovieList : Fragment(){

    private lateinit var navController: NavController
    private lateinit var viewModel: MoviesViewModel
    private lateinit var recyclerView: RecyclerView
    private val TAG = "MoviesFragment"

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
            bundle.putBoolean(Constant.FAV_BOOLEAN, true)
            navController.navigate(
                R.id.action_movies_fragment_to_movieDetails,
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
        viewModel = ViewModelProviders.of(this).get(MoviesViewModel::class.java)
        recyclerView = view.findViewById(R.id.recycler_view_movies)
        navController = Navigation.findNavController(view)
        val layoutManager = LinearLayoutManager(context)
        recyclerView.layoutManager = layoutManager
        setData()
        setAdapter()
    }

    private fun setData() {
        viewModel.liveData.observe(viewLifecycleOwner, Observer { result->
            when(result){
                is MoviesViewModel.State.Result -> {
                    movieAdapter.addItems(result.list)
                }
            }
        })
    }

    private fun setAdapter() {
        recyclerView.adapter = movieAdapter

    }
}
