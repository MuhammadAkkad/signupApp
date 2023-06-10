package com.example.signupapp.ui.binding

import android.graphics.Bitmap
import android.widget.ImageView
import androidx.databinding.BindingAdapter
import com.example.signupapp.ui.util.gone
import com.example.signupapp.ui.util.visible


object BindingAdapters {

    @BindingAdapter("android:setImageBitmap")
    @JvmStatic
    fun setImageBitmap(imageView: ImageView, bitmap: Bitmap?) {
        try {
            if (bitmap != null) {
                imageView.setImageBitmap(bitmap)
                imageView.visible()
            } else imageView.gone()
        } catch (_: Exception) {
            imageView.gone()
        }
    }

}