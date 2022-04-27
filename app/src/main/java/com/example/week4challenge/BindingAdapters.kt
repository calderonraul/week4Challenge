package com.example.week4challenge

import android.util.Log
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.bumptech.glide.Glide
import com.bumptech.glide.load.engine.DiskCacheStrategy
import com.bumptech.glide.load.model.GlideUrl
import com.bumptech.glide.load.model.LazyHeaders
import com.bumptech.glide.request.RequestOptions

@BindingAdapter("imgURL")
fun loadImage(view: ImageView, IMGurl: String) {
    Log.wtf("equisDe",IMGurl)


    IMGurl.let {
        val url = GlideUrl(
            it, LazyHeaders.Builder()
                .addHeader("User-Agent", "your-user-agent")
                .build()
        );
        Glide.with(view)
            .load(url)
            .diskCacheStrategy(DiskCacheStrategy.ALL)
            .apply(
                RequestOptions()
                    .placeholder(R.drawable.loading_animation)
                    .error(R.drawable.ic_broken_image)
            )
            .into(view)
    }

}


