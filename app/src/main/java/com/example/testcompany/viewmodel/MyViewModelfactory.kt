package com.example.testcompany.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.ViewModelProvider

class MyViewModelfactory :ViewModelProvider.Factory {

    override fun <T : ViewModel?> create(modelClass: Class<T>): T {

        if (modelClass.isAssignableFrom(DataComedy::class.java)){
            return DataComedy() as T
        }
        throw IllegalArgumentException("Unknown ViewModel class")

    }
}

