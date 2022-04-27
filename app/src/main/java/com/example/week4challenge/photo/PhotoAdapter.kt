package com.example.week4challenge.photo

import android.content.Context
import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.week4challenge.R
import com.example.week4challenge.databinding.PhotoAdapterBinding
import com.example.week4challenge.model.Photo

class PhotoAdapter(val context: Context?, val clickListener: PhotoClickListener) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    var photoList: List<Photo> = ArrayList()
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): PhotoAdapter.PhotoViewHolder {
        val viewBinding:PhotoAdapterBinding=DataBindingUtil.inflate(LayoutInflater.from(parent.context),
            R.layout.photo_adapter,parent,false)
        return PhotoViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.PhotoViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }
    fun setPhotos(photos:List<Photo>){
        this.photoList=photos
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(private val viewBinding: PhotoAdapterBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(position: Int){
            val row = photoList[position]
            viewBinding.photo=row
            viewBinding.imgPortada
            viewBinding.photoClickListenerInterface=clickListener
        }
    }
}