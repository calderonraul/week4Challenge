package com.example.week4challenge.photodetail

import android.content.Context
import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week4challenge.R
import com.example.week4challenge.databinding.PhotoDetailFragmentBinding
import com.example.domain.entity.PhotoDomain
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhotoDetailFragment : Fragment() {

    companion object {
        @JvmStatic
        fun newInstance(data: PhotoDomain) = PhotoDetailFragment().apply {
            arguments = Bundle().apply {
            }
        }


    }


    private val photoDetailViewModel by viewModel<PhotoDetailViewModel>()
    private lateinit var photoDetailAdapter: PhotoDetailAdapter
    private lateinit var mViewDataBinding: PhotoDetailFragmentBinding

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
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
        setView()
        mViewDataBinding.viewModel=photoDetailViewModel
        loadContent()
        //mViewDataBinding. = photo
    }

    private fun loadContent() {
        photoDetailViewModel.getAllPhotos()
        photoDetailViewModel.photoList.observe(viewLifecycleOwner, androidx.lifecycle.Observer{
            if (it != null) {
                if (it.isNotEmpty()) {
                    photoDetailAdapter.setPhotos(it)
                }
            }
        })
    }

    private fun setView(){

        photoDetailAdapter=PhotoDetailAdapter()

        mViewDataBinding.photoDetailRv.layoutManager=
            LinearLayoutManager(activity,LinearLayoutManager.VERTICAL,false)
        mViewDataBinding.photoDetailRv.adapter=photoDetailAdapter

    }

}
