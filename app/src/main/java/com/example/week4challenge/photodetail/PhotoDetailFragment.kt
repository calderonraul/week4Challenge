package com.example.week4challenge.photodetail

import android.content.Context
import androidx.lifecycle.ViewModelProvider
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.week4challenge.R
import com.example.week4challenge.databinding.PhotoDetailFragmentBinding
import com.example.week4challenge.model.Photo

class PhotoDetailFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(data: Photo) = PhotoDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable("photo_data", data)
                // TODO: please dont user hardcode string use a connstant in this cases
            }
        }
    }

    private var photo: Photo? = null
    private lateinit var mViewDataBinding: PhotoDetailFragmentBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        // TODO: please dont user hardcode string use a connstant in this cases
        photo = arguments?.getParcelable("photo_data")

        photo?.let { Log.d("detalle", it.title) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.photo_detail_fragment, container, false
        )
        val mRootView = mViewDataBinding.root
        mViewDataBinding.lifecycleOwner = this
        return mRootView
    }

    // TODO: this functions has been deprecated you can do this in a observable or in view created. function

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        //enableBackButton()
        mViewDataBinding.photo = photo
    }

}