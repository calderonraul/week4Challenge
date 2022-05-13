package com.example.week4challenge.photodetail

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.databinding.DataBindingUtil
import androidx.fragment.app.Fragment
import androidx.fragment.app.viewModels
import androidx.recyclerview.widget.LinearLayoutManager
import androidx.recyclerview.widget.PagerSnapHelper
import com.example.domain.entity.PhotoDomain
import com.example.week4challenge.R
import com.example.week4challenge.databinding.PhotoDetailFragmentBinding
import dagger.hilt.android.AndroidEntryPoint


@AndroidEntryPoint
class PhotoDetailFragment : Fragment() {
    var selectedPhoto:Int = 0

    companion object {
        @JvmStatic
        fun newInstance(data: PhotoDomain) = PhotoDetailFragment().apply {
            arguments = Bundle().apply {
                selectedPhoto=data.id
            }
        }


    }
    private val photoDetailViewModel by viewModels<PhotoDetailViewModel>()
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
        return mViewDataBinding.root
    }


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        photoDetailViewModel.getAllPhotos()

        mViewDataBinding.photoDetailRv.layoutManager =
            LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false)

        photoDetailViewModel.photoRX?.subscribe {
            photoDetailAdapter = PhotoDetailAdapter()
            photoDetailAdapter.setPhotos(it)
            mViewDataBinding.photoDetailRv.adapter = photoDetailAdapter
            PagerSnapHelper().attachToRecyclerView(mViewDataBinding.photoDetailRv)
            mViewDataBinding.photoDetailRv.smoothScrollToPosition(selectedPhoto)
        }

        mViewDataBinding.viewModel = photoDetailViewModel

    }
}
