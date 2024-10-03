package com.zainco.trellapremierleague.utils

import android.annotation.SuppressLint
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import coil.decode.SvgDecoder
import coil.request.ImageRequest
import java.text.SimpleDateFormat


@Composable
fun getImageUrl(url: String) = if (url.contains("svg")) {
    ImageRequest.Builder(LocalContext.current).data(url)
        .decoderFactory(SvgDecoder.Factory()).build()
} else url

@SuppressLint("SimpleDateFormat")
fun String.formatDate(): String {
    val form = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val nFormat = SimpleDateFormat("hh:MM")
    val date = form.parse(this)
    return nFormat.format(date)
}

@SuppressLint("SimpleDateFormat")
fun String.formatDateToCompare(): String {
    val form = SimpleDateFormat("yyyy-MM-dd'T'HH:mm:ss")
    val nFormat = SimpleDateFormat("YYYY/MM/dd")
    val date = form.parse(this)
    return nFormat.format(date)
}