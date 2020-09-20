package com.example.testcompany.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider
import com.example.testcompany.model.Page

class MyViewModelfactory(var page: String) :ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DataComedy::class.java)){
            return DataComedy("") as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}


/*
* if (modelClass.isAssignableFrom(ScoreViewModel::class.java)) {
       return ScoreViewModel(finalScore) as T
   }
   throw IllegalArgumentException("Unknown ViewModel class")

* */