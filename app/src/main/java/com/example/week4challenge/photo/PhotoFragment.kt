package com.example.week4challenge.photo

import android.os.Bundle
import android.util.Log
import androidx.fragment.app.Fragment
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.lifecycle.Observer
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.week4challenge.MainActivity
import com.example.week4challenge.R
import com.example.week4challenge.databinding.FragmentPhotoBinding
import com.example.week4challenge.model.Photo
import com.example.week4challenge.photodetail.PhotoDetailFragment
import com.example.week4challenge.util.replaceFragment
import org.koin.androidx.viewmodel.ext.android.viewModel


class PhotoFragment : Fragment(), PhotoClickListener {

    private val photoViewModel by viewModel<PhotoViewModel>()
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var mViewDataBinding: FragmentPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        mViewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)
        val mRootView = mViewDataBinding.root
        mViewDataBinding.lifecycleOwner = this
        return mRootView

    }

    override fun onActivityCreated(savedInstanceState: Bundle?) {
        super.onActivityCreated(savedInstanceState)
        setView()
        mViewDataBinding.viewModel = photoViewModel
        loadContent()

        mViewDataBinding.swipeRefreshLayout.setOnRefreshListener {
            loadContent()
            mViewDataBinding.swipeRefreshLayout.isRefreshing = false
        }


    }


    private fun loadContent() {
        photoViewModel.getAllPhotos()
        photoViewModel.photoList.observe(viewLifecycleOwner, Observer {
            Log.wtf("@@photos", it!!.size.toString())
            if (it.isNotEmpty()) {
                photoAdapter.setPhotos(it)
            }

        })
    }

    private fun setView() {

        photoAdapter = PhotoAdapter(context, this)
        mViewDataBinding.rvPhotos.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        mViewDataBinding.rvPhotos.adapter = photoAdapter
        mViewDataBinding.rvPhotos.isNestedScrollingEnabled = false


    }

    override fun onItemClick(photo: Photo) {
        (activity as MainActivity).replaceFragment(
            PhotoDetailFragment.newInstance(photo),
            R.id.fragment_container,
            "photoDetails"
        )
    }


}