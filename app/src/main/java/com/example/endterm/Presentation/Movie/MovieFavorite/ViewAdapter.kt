package com.example.endterm.Presentation.Movie.MovieFavorite

import android.util.Log
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.example.endterm.Data.Models.Movie.MovieData
import com.example.endterm.R
import com.example.endterm.Utils.Constant



class ViewAdapter( ) : RecyclerView.Adapter<ViewAdapter.MoviesViewHolder>() {



    private val TAG = "RecyclerViewAdapter"
    private val movieList = ArrayList<MovieData>()

    fun addItems(list: List<MovieData>) {
        movieList.addAll(list)
        notifyDataSetChanged()
    }

    fun clearAll() {
        movieList.clear()
        notifyDataSetChanged()
    }

    fun getItem(position: Int) : MovieData? {
        return movieList[position]
    }
    inner class MoviesViewHolder(view: View): RecyclerView.ViewHolder(view) {
        val tvTitle : TextView
        val tvVoteAverage : TextView
        val image : ImageView

        init {
            tvTitle = view.findViewById(R.id.title_TextView)
            tvVoteAverage = view.findViewById(R.id.vote_average_TextView)
            image = view.findViewById(R.id.imageView)
        }

        fun bind (movie: MovieData) {
            tvTitle.text = movie.title
            tvVoteAverage.text = movie.vote_average.toString()
            val imageUrl = "${Constant.BACKDROP_BASE_URL}${movie.backdropImg}"
            Glide.with(itemView.context)
                .load(imageUrl)
                .into(image)

            Log.d("MovieAdapter:", imageUrl + "   -----  " +  itemView.context.toString() + " -----  " + image.toString() + " ]]] ")
        }

//        fun setItemClick(movie: MovieData) {
//            itemView.setOnClickListener{
//                movieClickListener.onMovieClick(movie)
//            }
//        }
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesViewHolder {
        val view = LayoutInflater
            .from(parent.context)
            .inflate(R.layout.list_item, parent, false)
        return MoviesViewHolder(view)
    }

    override fun getItemCount(): Int = movieList.size

    override fun onBindViewHolder(holder: MoviesViewHolder, position: Int) {
        val movie = movieList[position]
        holder.bind(movie)
        //holder.setItemClick(movie)
    }

}
