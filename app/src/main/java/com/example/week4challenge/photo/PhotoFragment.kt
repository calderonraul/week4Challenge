package com.example.week4challenge.photo

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import com.example.domain.entity.PhotoDomain
import com.example.utils.util.Constants.FRAGMENT_DESTINY
import com.example.utils.util.replaceFragment
import com.example.week4challenge.MainActivity
import com.example.week4challenge.R
import com.example.week4challenge.databinding.FragmentPhotoBinding
import com.example.week4challenge.photodetail.PhotoDetailFragment
import dagger.hilt.android.AndroidEntryPoint

@AndroidEntryPoint
class PhotoFragment : Fragment(), PhotoClickListener {

    private val photoViewModel by viewModels<PhotoViewModel>()
    private lateinit var photoAdapter: PhotoAdapter
    private lateinit var mViewDataBinding: FragmentPhotoBinding

    override fun onCreateView(
        inflater: LayoutInflater, container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        mViewDataBinding =
            DataBindingUtil.inflate(inflater, R.layout.fragment_photo, container, false)
        mViewDataBinding.lifecycleOwner = this
        return mViewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoViewModel.getAllPhotos()
        mViewDataBinding.rvPhotos.setHasFixedSize(true)
        mViewDataBinding.rvPhotos.layoutManager =
            LinearLayoutManager(activity, LinearLayoutManager.VERTICAL, false)
        load()

        mViewDataBinding.swipeRefreshLayout.setOnRefreshListener {
            load()
            mViewDataBinding.swipeRefreshLayout.isRefreshing = false
        }
    }

    private fun load(){
        photoViewModel.photoRX?.subscribe {
            photoAdapter = PhotoAdapter(this)
            photoAdapter.setPhotos(it)
            mViewDataBinding.rvPhotos.adapter = photoAdapter
            mViewDataBinding.rvPhotos.scrollToPosition(15)
        }
        mViewDataBinding.viewModel = photoViewModel
    }


    override fun onItemClick(photo: PhotoDomain) {
        (activity as MainActivity).replaceFragment(
            PhotoDetailFragment.newInstance(photo),
            R.id.fragment_container,
            FRAGMENT_DESTINY
        )
    }
}