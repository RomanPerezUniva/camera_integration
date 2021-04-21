package com.example.cameraintegration

import android.graphics.Bitmap
import android.os.Parcelable
import kotlinx.android.parcel.Parcelize

@Parcelize
data class KPhoto(val name: String, val photo: Int) : Parcelable