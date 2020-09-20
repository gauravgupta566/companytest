package com.example.testcompany.ui

import com.example.testcompany.ui.activity.MainActivity
import com.example.testcompany.viewmodel.DataComedy
import org.junit.Before
import org.mockito.Mockito
import org.mockito.MockitoAnnotations

class MainActivityTest {

    lateinit var mainActivity: MainActivity

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
       mainActivity=Mockito.spy(MainActivity::class.java)

    }
}