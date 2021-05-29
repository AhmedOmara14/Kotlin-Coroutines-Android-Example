package com.omaradev.examplecoroutine.adapter

import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.TextView
import androidx.recyclerview.widget.RecyclerView
import com.omaradev.examplecoroutine.R
import com.omaradev.examplecoroutine.models.Posts.PostsItem
import java.security.AccessController.getContext
import java.util.*

class AdapterOfPosts : RecyclerView.Adapter<AdapterOfPosts.viewHolder> {

    private var listOfAllServices: List<PostsItem> = ArrayList<PostsItem>()

     constructor(
         list: List<PostsItem>,
     ) {
        this.listOfAllServices = list
    }




    class viewHolder(itemView: View) : RecyclerView.ViewHolder(itemView) {
        public val title: TextView
        public val id: TextView
        public val body: TextView

        init {
            title = itemView.findViewById(R.id.tvCardViewTitle)
            id = itemView.findViewById(R.id.tvCardViewID)
            body = itemView.findViewById(R.id.tvCardViewBody)

        }
    }

    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): viewHolder {
        val v: View = LayoutInflater.from(parent.context)
            .inflate(R.layout.cardviewallposts, parent, false)
        return viewHolder(v)
    }

    override fun onBindViewHolder(holder: viewHolder, position: Int) {
        holder.title.setText(listOfAllServices.get(position).title)
        holder.id.setText(listOfAllServices.get(position).id.toString())
        holder.body.setText(listOfAllServices.get(position).body)
    }

    override fun getItemCount(): Int {
        return listOfAllServices.size
    }
}