package com.codandotv.streamplayerapp.core_shared.extension

import android.content.Context
import android.graphics.BitmapFactory
import android.net.Uri
import android.provider.MediaStore
import java.io.IOException
import java.io.InputStream
import java.net.HttpURLConnection
import java.net.MalformedURLException
import java.net.URL
import java.util.*

fun getUriFromUrlImage(
    contentUrl: String,
    context: Context
): Uri? {
    var url: URL? = null
    try {
        url = URL(contentUrl)
    } catch (e: MalformedURLException) {
        e.printStackTrace()
    }
    var connection: HttpURLConnection? = null
    try {
        assert(url != null)
        connection = url!!.openConnection() as HttpURLConnection
    } catch (e: IOException) {
        showErrorMessage(context, "Erro ao buscar imagem")
    }
    assert(connection != null)
    connection!!.doInput = true
    try {
        connection.connect()
    } catch (e: IOException) {
        showErrorMessage(context, "Erro ao buscar imagem")
    }
    var input: InputStream? = null
    try {
        input = connection.inputStream
    } catch (e: IOException) {
        showErrorMessage(context, "Erro ao buscar imagem")
    }
    val imgBitmap = BitmapFactory.decodeStream(input)
    val rand = Random()
    val randNo = rand.nextInt(100000)
    val imgBitmapPath = MediaStore.Images.Media.insertImage(
        context.contentResolver, imgBitmap,
        "IMG:$randNo", null
    )
    return Uri.parse(imgBitmapPath)
}