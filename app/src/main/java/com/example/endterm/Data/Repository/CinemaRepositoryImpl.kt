package com.example.endterm.Data.Repository

import androidx.lifecycle.LiveData
import com.example.endterm.Data.Models.Cinema.Cinema
import com.example.endterm.Data.Room.CinemaDao
import com.example.endterm.Domain.Repository.CinemaRepository

class CinemaRepositoryImpl(private val cinemaDao: CinemaDao) : CinemaRepository {


    override fun getAllCinema(): LiveData<List<Cinema>> {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    //override fun getCinema(id: Int): LiveData<Cinema> = cinemaDao.getCinema(id)
}