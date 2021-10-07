package com.jmilham.pageturner.helper.adapter

import android.content.Context
import android.graphics.Bitmap
import android.graphics.drawable.Drawable
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.ContextCompat
import androidx.recyclerview.widget.RecyclerView
import com.jmilham.pageturner.R
import com.jmilham.pageturner.models.BookModel
import com.squareup.picasso.Picasso
import com.squareup.picasso.Target

class BookModelAdapter(private val dataSet: ArrayList<BookModel.Book>) :
    RecyclerView.Adapter<BookModelAdapter.ViewHolder>() {

    /**
     * Provide a reference to the type of views that you are using
     * (custom ViewHolder).
     */
    class ViewHolder(view: View) : RecyclerView.ViewHolder(view) {
        val titleView: TextView = view.findViewById(R.id.title)
        val coverImage: ImageView = view.findViewById(R.id.cover_image)
        val context: Context = view.context

        init {
            // Define click listener for the ViewHolder's View.
        }
    }

    fun clearDataSet() {
        dataSet.clear()
        notifyDataSetChanged()
    }

    // Create new views (invoked by the layout manager)
    override fun onCreateViewHolder(viewGroup: ViewGroup, viewType: Int): ViewHolder {
        // Create a new view, which defines the UI of the list item
        val view = LayoutInflater.from(viewGroup.context)
            .inflate(R.layout.list_bookmodel_book, viewGroup, false)

        return ViewHolder(view)
    }

    // Replace the contents of a view (invoked by the layout manager)
    override fun onBindViewHolder(viewHolder: ViewHolder, position: Int) {

        // Get element from your dataset at this position and replace the
        // contents of the view with that element
        viewHolder.titleView.text = dataSet[position].title
        val url =
            viewHolder.context.getString(R.string.base_cover_api) + dataSet[position].coverKey + viewHolder.context.getString(
                R.string.medium_cover_extension
            )
        val placeholder = ContextCompat.getDrawable(viewHolder.context, R.drawable.ic_launcher_icon_foreground)

        val target = object : Target {
            override fun onBitmapLoaded(bitmap: Bitmap?, from: Picasso.LoadedFrom?) {
                if(bitmap?.height ?: -1 != 1){
                    viewHolder.coverImage.setImageBitmap(bitmap)
                }else{
                    viewHolder.coverImage.setImageDrawable(placeholder)
                }
            }

            override fun onBitmapFailed(e: Exception?, errorDrawable: Drawable?) {
                viewHolder.coverImage.setImageDrawable(errorDrawable)
            }

            override fun onPrepareLoad(placeHolderDrawable: Drawable?) {
                viewHolder.coverImage.setImageDrawable(placeHolderDrawable)
            }

        }
        Picasso.get().load(url).error(placeholder!!)
            .into(target)

    }

    // Return the size of your dataset (invoked by the layout manager)
    override fun getItemCount() = dataSet.size

}
