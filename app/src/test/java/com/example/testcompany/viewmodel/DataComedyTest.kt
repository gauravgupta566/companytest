package com.example.testcompany.viewmodel

import android.content.Context
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class DataComedyTest {


    lateinit var dateComedyModel:DataComedy

    @Mock
   lateinit var context:Context

    @Before
    fun setUp() {
        MockitoAnnotations.initMocks(this)
        dateComedyModel = DataComedy()
        context=Mockito.mock(Context::class.java)
    }

    @Test
    fun getData(){

        dateComedyModel.getData(context,1)

    }


}