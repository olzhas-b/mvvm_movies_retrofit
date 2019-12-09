package com.example.endterm.Domain.Repository

import androidx.lifecycle.LiveData
import com.example.endterm.Data.Models.Cinema.Cinema

interface CinemaRepository{
    fun getAllCinema(): LiveData<List<Cinema>>


}