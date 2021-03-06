package com.example.testcompany.viewmodel

import android.content.Context
import android.util.Log
import androidx.lifecycle.LiveData
import androidx.lifecycle.MutableLiveData
import androidx.lifecycle.ViewModel
import com.example.testcompany.model.ComedyList
import com.example.testcompany.model.Content
import com.example.testcompany.model.Page
import com.google.gson.Gson
import java.io.InputStream


class DataComedy :ViewModel(){

   var result=MutableLiveData<Page>()

   fun getData(context: Context,pageNumber:Int){
     val fileName: String
       if(pageNumber==1){
           fileName="firstapi.json"
       }
       else if(pageNumber==2){
           fileName="secondapi.json"

       }
       else {
           fileName="thirdapi.json"

       }
       try {
           val  inputStream: InputStream = context.assets.open(fileName)
           val  json = inputStream.bufferedReader().use{it.readText()}
           val gson=Gson()
           val  comedyList=  gson.fromJson(json, ComedyList::class.java)
           result.postValue(comedyList.page)

       } catch (ex: Exception) {
           ex.printStackTrace()
       }
    }

   fun getResult():LiveData<Page>{
        return result
    }


}

