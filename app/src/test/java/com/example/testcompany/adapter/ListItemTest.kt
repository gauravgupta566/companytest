package com.example.testcompany.adapter

import android.content.Context
import android.view.ViewGroup
import com.example.testcompany.model.Content
import com.nhaarman.mockito_kotlin.any
import org.junit.Assert
import org.junit.Before
import org.junit.Test
import org.junit.runner.RunWith
import org.mockito.Mock
import org.mockito.Mockito
import org.mockito.MockitoAnnotations
import org.powermock.api.mockito.PowerMockito
import org.powermock.modules.junit4.PowerMockRunner
import java.lang.NullPointerException

@RunWith(PowerMockRunner::class)
class ListItemTest {

    lateinit var listItemAdapter: ListItem
    var list= ArrayList<Content>()

    @Mock
    lateinit var viewGroup: ViewGroup

    lateinit var holder: ListItem.MyViewHolder



    @Before
    fun setUp(){
        MockitoAnnotations.initMocks(this)
        holder= PowerMockito.mock(ListItem.MyViewHolder::class.java)
        listItemAdapter= ListItem(list,Mockito.mock(Context::class.java))

    }

    @Test(expected = NullPointerException::class)
    fun onCreateViewHolderWithNull(){
        val a="abc"
        listItemAdapter.onCreateViewHolder(a as ViewGroup,1)
    }

    @Test()
    fun onCreateViewHolder(){

        viewGroup= PowerMockito.mock(ViewGroup::class.java)
        listItemAdapter.onCreateViewHolder(viewGroup, 1)
    }

    @Test()
    fun onBindViewCalled(){
        listItemAdapter.onBindViewHolder(holder, 0)
    }


    @Test
    fun getItemCount(){
        Assert.assertEquals(0,listItemAdapter.itemCount)
    /*    addJson()
        Assert.assertEquals(1,listItemAdapter.itemCount)
*/
    }



}
