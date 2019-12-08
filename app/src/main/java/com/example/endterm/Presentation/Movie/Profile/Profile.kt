package com.example.endterm.Presentation.Movie.Profile

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
import com.bumptech.glide.load.engine.executor.GlideExecutor.UncaughtThrowableStrategy.LOG

import com.example.endterm.R
import com.example.endterm.Utils.AppPreferences
import kotlinx.android.synthetic.main.profile_fragment.*

class Profile : Fragment() {

    private lateinit var navController: NavController
    private lateinit var viewModel: ProfileViewModel
    private var sessionId: String? = null
    companion object {
        fun newInstance() = Profile()

    }


    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        return inflater.inflate(R.layout.profile_fragment, container, false)
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        viewModel = ViewModelProviders.of(this).get(ProfileViewModel::class.java)
        bindViews(view)
        setData()
    }
    private fun bindViews(view: View) = with(view) {
        navController = Navigation.findNavController(this)
        sessionId = activity?.applicationContext?.let { context ->  AppPreferences.getSessionId(context) }

    }

    private fun setData(){
        sessionId?.let { sessionId ->
            viewModel.loadInformation(sessionId)
        }
        viewModel.liveData.observe(viewLifecycleOwner, Observer { result->
            when(result){
                is ProfileViewModel.State.Result -> {
                    Log.d("ProfileTextView",  result.name + "  "  + result.username)
                    name.text = result.name
                    username.text = result.username

                }
            }
        })
    }

}
