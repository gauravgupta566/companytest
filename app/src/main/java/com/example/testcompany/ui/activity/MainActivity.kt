package com.example.testcompany.ui.activity

import android.content.Context
import android.content.res.Configuration
import android.os.Bundle
import android.text.Editable
import android.text.TextWatcher
import android.view.View
import android.view.inputmethod.InputMethodManager
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.ViewModelProvider
import androidx.recyclerview.widget.GridLayoutManager
import androidx.recyclerview.widget.RecyclerView
import com.example.testcompany.R
import com.example.testcompany.adapter.ListItem
import com.example.testcompany.comman.GridSpacingItemDecoration
import com.example.testcompany.model.Content
import com.example.testcompany.model.Page
import com.example.testcompany.viewmodel.DataComedy
import com.example.testcompany.viewmodel.MyViewModelfactory
import kotlinx.android.synthetic.main.activity_main.*
import java.util.*
import kotlin.collections.ArrayList


class MainActivity : AppCompatActivity(), View.OnClickListener {

   private lateinit var adapter:ListItem
   private var list=ArrayList<Content>()
   private var orientationLandscape=false
   private var pageNumber=1
   private var dataLoading=false
   private lateinit var gridLayoutManager:GridLayoutManager

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)



        val myViewModelfactory=  MyViewModelfactory()

       val model= ViewModelProvider(this,myViewModelfactory).get(DataComedy::class.java)

        model.getResult().observe(this, androidx.lifecycle.Observer {
            getHeading.text= it.title
            addingData(it)

        })

        model.getData(this,pageNumber)


        if (resources.getConfiguration().orientation == Configuration.ORIENTATION_LANDSCAPE) {
            orientationLandscape=true
        }

        if (orientationLandscape){
            gridLayoutManager=GridLayoutManager(this,7)
            recyclerView.addItemDecoration(GridSpacingItemDecoration(7,30,false))
        }
        else{
            gridLayoutManager=GridLayoutManager(this,3)
            recyclerView.addItemDecoration(GridSpacingItemDecoration(3,30,false))
        }
        recyclerView.layoutManager=gridLayoutManager

        adapter= ListItem(list,this)

        recyclerView.adapter=adapter


        recyclerView.addOnScrollListener(object :RecyclerView.OnScrollListener(){
            override fun onScrolled(recyclerView: RecyclerView, dx: Int, dy: Int) {
                super.onScrolled(recyclerView, dx, dy)
                if (dy>0){
                    val visibleItemCount = gridLayoutManager.childCount
                    val totalItemCount = gridLayoutManager.itemCount
                    val firstVisibleItemPosition = gridLayoutManager.findFirstVisibleItemPosition()
                   val canLoad= visibleItemCount + firstVisibleItemPosition >= totalItemCount && firstVisibleItemPosition >= 0
                    if (pageNumber<3 && canLoad &&!dataLoading) {
                           dataLoading=true
                            pageNumber++
                             model.getData(this@MainActivity,pageNumber)


                        //                    && totalItemCount >= ClothesFragment.itemsCount
                    }

                }
            }
        })



        searchEditText.addTextChangedListener(object :TextWatcher{

            override fun afterTextChanged(p0: Editable?) {
            }

            override fun beforeTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
            }

            override fun onTextChanged(p0: CharSequence?, p1: Int, p2: Int, p3: Int) {
                if(p0.toString().length>=3){
                    searchData(p0.toString())
                }

            }

        })

        searchButton.setOnClickListener(this)
        crossButton.setOnClickListener(this)
        backutton.setOnClickListener(this)

    }

    private fun searchData(value: String) {
        val filteredList= ArrayList<Content>()
        for (i in 0 until list.size){
            if (list[i].name.toLowerCase(Locale.getDefault()).contains(value.toLowerCase(Locale.getDefault()))){
                filteredList.add(list[i])
            }
        }
      adapter.applyFilter(filteredList)

    }

    override fun onClick(p0: View?) {

        when(p0?.id){
            R.id.searchButton-> {
                searchButton.visibility=View.GONE
                getHeading.visibility=View.GONE
                backutton.visibility=View.GONE
                searchEditText.visibility=View.VISIBLE
                crossButton.visibility=View.VISIBLE
                searchEditText.requestFocus()

                 getInputManager().showSoftInput(searchEditText, InputMethodManager.SHOW_IMPLICIT)



            }
            R.id.crossButton->{
                getInputManager().hideSoftInputFromWindow(searchEditText.getWindowToken(), 0)

                searchEditText.visibility=View.GONE
                crossButton.visibility=View.GONE

                searchEditText.text=null
                searchButton.visibility=View.VISIBLE
                getHeading.visibility=View.VISIBLE
                backutton.visibility=View.VISIBLE

                adapter.applyFilter(list)





            }
            R.id.backutton->onBackPressed()
        }

    }

    private fun getInputManager():InputMethodManager {
        return  getSystemService(Context.INPUT_METHOD_SERVICE) as InputMethodManager

    }

    private fun addingData(page: Page) {
        list.addAll(page.content_items.content)
        adapter.notifyItemRangeInserted(adapter.getItemCount(), list.size - 1);
        dataLoading=false

    }



}
