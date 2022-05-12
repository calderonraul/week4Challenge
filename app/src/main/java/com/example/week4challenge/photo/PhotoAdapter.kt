package com.example.week4challenge.photo

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.week4challenge.R
import com.example.week4challenge.databinding.PhotoAdapterBinding
import com.example.data.model.Photo
import com.example.domain.entity.PhotoDomain
import com.example.week4challenge.databinding.PhotoAdapterViewtype2Binding

class PhotoAdapter(val clickListener: PhotoClickListener) :
    RecyclerView.Adapter<PhotoAdapter.PhotoViewHolder>() {

    var photoList: List<PhotoDomain> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoAdapter.PhotoViewHolder {
        val viewBinding: PhotoAdapterBinding = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.photo_adapter, parent, false
        )
        return PhotoViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: PhotoAdapter.PhotoViewHolder, position: Int) {
        holder.onBind(position)
    }

    override fun getItemCount(): Int {
        return photoList.size
    }

    fun setPhotos(photos: List<PhotoDomain>?) {
        if (photos != null) {
            this.photoList = photos
        }
        notifyDataSetChanged()
    }

    inner class PhotoViewHolder(private val viewBinding: PhotoAdapterBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(position: Int) {
            val row = photoList[position]
            viewBinding.photo = row
            viewBinding.imgPortada
            viewBinding.photoClickListenerInterface = clickListener
        }
    }
}