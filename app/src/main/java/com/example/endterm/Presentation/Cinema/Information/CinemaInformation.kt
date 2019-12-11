package com.example.endterm.Presentation.Cinema.Information

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.endterm.R

class CinemaInformation : Fragment() {

    companion object {
        fun newInstance() = CinemaInformation()
    }

    private lateinit var viewModel: CinemaInformationViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cinema_information_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CinemaInformationViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
