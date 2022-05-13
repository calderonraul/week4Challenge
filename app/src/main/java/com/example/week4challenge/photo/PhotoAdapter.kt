package com.example.week4challenge.photo

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.RecyclerView
import com.example.domain.entity.PhotoDomain
import com.example.week4challenge.R
import com.example.week4challenge.databinding.PhotoAdapterBinding
import com.example.week4challenge.databinding.PhotoAdapterViewtype2Binding

class PhotoAdapter(val clickListener: PhotoClickListener) :
    RecyclerView.Adapter<RecyclerView.ViewHolder>() {
    private val EXCEPTIONMESSAGE = "couldn't create view holder"
    private lateinit var bindingType1: PhotoAdapterBinding
    private lateinit var bindingType2: PhotoAdapterViewtype2Binding
    var photoList: List<PhotoDomain> = ArrayList()

    enum class PhotoType(val value: Int) {
        FIRST_VALUE(1), SECOND_VALUE(2)
    }

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): RecyclerView.ViewHolder =
        when (viewType) {
            PhotoType.FIRST_VALUE.value -> {
                bindingType1 = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.photo_adapter, parent, false
                )

                PhotoViewHolder(bindingType1)
            }
            PhotoType.SECOND_VALUE.value -> {
                bindingType2 = DataBindingUtil.inflate(
                    LayoutInflater.from(parent.context),
                    R.layout.photo_adapter_viewtype2, parent, false

                )
                PhotoViewHolder2(bindingType2)
            }
            else -> {
                throw Exception(EXCEPTIONMESSAGE)
            }
        }


    override fun onBindViewHolder(holder: RecyclerView.ViewHolder, position: Int) {
        return if (photoList[position].id % 2 == 0) {
            (holder as PhotoViewHolder).onBind(position)
        } else {
            (holder as PhotoViewHolder2).onBind(position)
        }
    }

    override fun getItemCount(): Int {
        return photoList.size
    }


    override fun getItemViewType(position: Int): Int {
        return if (photoList[position].id % 2 == 0) PhotoType.FIRST_VALUE.value
        else PhotoType.SECOND_VALUE.value

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

    inner class PhotoViewHolder2(private val viewBinding2: PhotoAdapterViewtype2Binding) :
        RecyclerView.ViewHolder(viewBinding2.root) {
        fun onBind(position: Int) {
            val row = photoList[position]
            viewBinding2.photo = row
            viewBinding2.imgPortada2
            viewBinding2.photoClickListenerInterface = clickListener
        }
    }

    fun setPhotos(photos: List<PhotoDomain>?) {
        if (photos != null) {
            this.photoList = photos
        }
        notifyDataSetChanged()
    }
}