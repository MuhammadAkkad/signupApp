package com.example.signupapp.ui.model

import android.graphics.Bitmap

data class SignUpFormModel(
    var image: Bitmap?,
    var name: String?,
    var email: String?,
    var password: String?,
    var website: String?
) : java.io.Serializable {
    constructor() : this(null, null, null, null, null)
}
