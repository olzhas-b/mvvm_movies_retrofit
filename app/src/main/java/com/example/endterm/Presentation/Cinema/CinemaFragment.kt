package com.example.endterm.Presentation.Cinema

import androidx.lifecycle.ViewModelProviders
import android.os.Bundle
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup

import com.example.endterm.R

class CinemaFragment : Fragment() {

    companion object {
        fun newInstance() = CinemaFragment()
    }

    private lateinit var viewModel: CinemaViewModel

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.cinema_fragment, container, false)
    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(CinemaViewModel::class.java)
        // TODO: Use the ViewModel
    }

}
