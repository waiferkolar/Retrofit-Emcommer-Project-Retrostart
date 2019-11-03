package coder.test.retrostart

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import coder.test.retrostart.R

import coder.test.retrostart.models.Category
import com.squareup.picasso.Picasso
import kotlinx.android.synthetic.main.cat_row.view.*

class CategoryAdapter(val context:Context, val cats : List<Category>)
    : RecyclerView.Adapter<CategoryAdapter.ViewHolder>()
{
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): ViewHolder {
        val v = LayoutInflater.from(context).inflate(R.layout.cat_row,parent,false)
        return ViewHolder(v)
    }

    override fun getItemCount(): Int = cats.size
    override fun onBindViewHolder(holder: ViewHolder, position: Int) {

        val cat = cats[position]

        Picasso.get().load("https://images-na.ssl-images-amazon.com/images/I/81-ftUPmZjL._SX679_.jpg")
            .into(holder.itemView.catImage);
        holder.itemView.catName.text = cat.name

    }

    class ViewHolder(item:View) : RecyclerView.ViewHolder(item)
}