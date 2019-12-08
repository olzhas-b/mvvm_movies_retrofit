package com.example.endterm.Presentation

import android.view.View
import com.example.endterm.Domain.Movie

interface RecyclerViewClickListener {
    fun onRecyclerViewItemClick(view: View, movie: Movie)
}