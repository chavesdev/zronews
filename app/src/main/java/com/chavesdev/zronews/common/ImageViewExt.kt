package com.chavesdev.zronews.common

import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.squareup.picasso.Picasso

@BindingAdapter("imageUrl")
fun ImageView.loadImage(url: String?) {
    if(url.isNullOrEmpty()) return
    Picasso.get().load(url).into(this)
}