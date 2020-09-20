package com.example.testcompany.adapter

import android.content.Context
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.bumptech.glide.Glide
import com.bumptech.glide.load.DataSource
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.engine.GlideException
import com.bumptech.glide.request.RequestListener
import com.bumptech.glide.request.target.Target
import com.example.testcompany.R
import com.example.testcompany.model.Content
import kotlinx.android.synthetic.main.item.view.*

class ListItem(private var list:ArrayList<Content>,private val context: Context):RecyclerView.Adapter<ListItem.MyViewHolder>() {



    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): MyViewHolder {
        val view=LayoutInflater.from(parent.context).inflate(R.layout.item, parent, false)
        return MyViewHolder(view)
    }


    override fun getItemCount(): Int {
     return  list.size
    }

    fun applyFilter(filteredList:ArrayList<Content>){
        list= filteredList
        notifyDataSetChanged()

    }

    override fun onBindViewHolder(holder: MyViewHolder, position: Int) {
        holder.bind(list[position])
      }


    inner class MyViewHolder(view: View) :RecyclerView.ViewHolder(view){

        private val imageView:ImageView=view.getImage
       private val textview:TextView=view.getText

        fun  bind(item:Content){
             Glide.with(context).load(getImage(item.poster_image))
                .error(R.drawable.placeholder_for_missing_posters)
                 .diskCacheStrategy(DiskCacheStrategy.RESOURCE)
                .listener(object : RequestListener<Drawable> {
                    override fun onLoadFailed(
                        e: GlideException?,
                        model: Any?,
                        target: Target<Drawable>?,
                        isFirstResource: Boolean
                    ): Boolean {
                        textview.visibility=View.VISIBLE

                        return false


                    }

                    override fun onResourceReady(
                        resource: Drawable?,
                        model: Any?,
                        target: Target<Drawable>?,
                        dataSource: DataSource?,
                        isFirstResource: Boolean
                    ): Boolean {

                        textview.visibility=View.VISIBLE
                        return false
                    }

                })

                .into(imageView)

            textview.text=item.name

        }
    }

   fun getImage(imageName:String):Int {
    val getimageName=  imageName.substring(0,imageName.length-4)
       return context.resources.getIdentifier(getimageName, "drawable", context.packageName)
   }

}