package com.example.testcompany.ui

import com.example.testcompany.model.Page
import com.example.testcompany.ui.activity.MainActivity
import com.nhaarman.mockito_kotlin.any
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.modules.junit4.PowerMockRunner

@RunWith(PowerMockRunner::class)
class MainActivityTest {

    lateinit var mainActivity: MainActivity

    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
       mainActivity=Mockito.spy(MainActivity::class.java)

    }

    @Test
    fun checkInputMethod(){
      val  method = MainActivity::class.java.getDeclaredMethod(
            "getInputManager")
        method.isAccessible=true
        method.invoke(mainActivity)
        PowerMockito.doReturn(any()).`when`(mainActivity,"getInputManager")

    }

    @Test
    fun addingData(){
        val  method = MainActivity::class.java.getDeclaredMethod(
            "addingData", any())
        method.isAccessible=true
        method.invoke(mainActivity)

    }

}