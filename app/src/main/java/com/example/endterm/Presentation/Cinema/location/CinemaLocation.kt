package com.example.endterm.Presentation.Cinema.location

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.endterm.R

class CinemaLocation : Fragment() {

    companion object {
        fun newInstance() = CinemaLocation()
    }

    private lateinit var viewModel: CinemaLocationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cinema_location_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CinemaLocationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
