package com.example.week4challenge.photodetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import com.example.week4challenge.R
import com.example.week4challenge.databinding.PhotoDetailFragmentBinding
import com.example.data.model.Photo
import com.example.domain.entity.PhotoDomain

class PhotoDetailFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(data: PhotoDomain) = PhotoDetailFragment().apply {
            arguments = Bundle().apply {
                putParcelable("photo_data", data)
                // TODO: please dont user hardcode string use a connstant in this cases
            }
        }


    }

    private var photo: PhotoDomain? = null
    private lateinit var mViewDataBinding: PhotoDetailFragmentBinding


    override fun onAttach(context: Context) {
        super.onAttach(context)
        // TODO: please dont user hardcode string use a connstant in this cases
       // photo = arguments?.getParcelable("photo_data")
        photo?.let { Log.d("detalle", it.title) }
    }

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View{
        mViewDataBinding = DataBindingUtil.inflate(
            inflater,
            R.layout.photo_detail_fragment, container, false
        )
        val mRootView = mViewDataBinding.root
        mViewDataBinding.lifecycleOwner = this
        return mRootView
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        mViewDataBinding.photo = photo
    }


}
