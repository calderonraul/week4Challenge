package com.example.week4challenge.photodetail

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.PhotoDomain
import com.example.week4challenge.R
import com.example.week4challenge.databinding.PhotoDetailAdapterBinding


class PhotoDetailAdapter: RecyclerView.Adapter<PhotoDetailAdapter.PhotoViewHolder>() {
    var photoList: List<PhotoDomain> = ArrayList()
    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): PhotoDetailAdapter.PhotoViewHolder {
        val viewBinding:PhotoDetailAdapterBinding  = DataBindingUtil.inflate(
            LayoutInflater.from(parent.context),
            R.layout.photo_detail_adapter, parent, false
        )
        return PhotoViewHolder(viewBinding)
    }

    override fun onBindViewHolder(holder: PhotoDetailAdapter.PhotoViewHolder, position: Int) {
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

    inner class PhotoViewHolder(private var viewBinding: PhotoDetailAdapterBinding) :
        RecyclerView.ViewHolder(viewBinding.root) {
        fun onBind(position: Int) {
            val row = photoList[position]
           viewBinding.photo=row
            viewBinding.photoIv
        }
    }


}