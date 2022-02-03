package com.encora.topsongs.utils

import android.content.Context
import android.widget.ImageView
import com.bumptech.glide.Glide

object ImageUtils {

    fun loadImage(context: Context, url: String?, imageView: ImageView) {
        if (url != null) {
            Glide.with(context).load(url).into(imageView)
        }
    }
}