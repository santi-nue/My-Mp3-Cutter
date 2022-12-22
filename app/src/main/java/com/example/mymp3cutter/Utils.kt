package com.example.mymp3cutter

import android.content.ContentUris
import android.content.Context
import android.database.Cursor
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import android.provider.MediaStore
import android.util.Log

/**
 * Created by Hamza Chaudhary
 * Sr. Software Engineer Android
 * Created on 21 Dec,2022 20:05
 * Copyright (c) All rights reserved.
 */

fun isMarshmallowPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.M
fun isNougatPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N
fun isNougatMR1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.N_MR1
fun isOreoPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O
fun isOreo() = Build.VERSION.SDK_INT == Build.VERSION_CODES.O
fun isOreoMr1Plus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.O_MR1
fun isPiePlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.P
fun isQPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q
fun isRPlus() = Build.VERSION.SDK_INT >= Build.VERSION_CODES.R


fun Context.getMediaIDByPath(songPath: String): Long {
    var id: Long = 0
    val cr = contentResolver
    val uri = if (isRPlus()) MediaStore.Audio.Media.getContentUri("external")
    else MediaStore.Audio.Media.getContentUriForPath("external")

    val selection = MediaStore.Audio.Media.DATA
    val selectionArgs = arrayOf(songPath)
    val projection = arrayOf(MediaStore.Audio.Media._ID)
    val sortOrder = MediaStore.Audio.Media.TITLE + " ASC"
    val cursor = uri?.let {
        cr.query(
            it, projection, "$selection=?", selectionArgs, null
        )
    }
    if (cursor != null) {
        while (cursor.moveToNext()) {
            val idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            id = cursor.getString(idIndex).toLong()
        }
    }
    cursor?.close()
    return id
}

fun Context.getURIFromPathString(path: String): Uri {
    var id: Long = 0

    val collection: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
    } else {
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    }
    val projection = arrayOf(
        MediaStore.Audio.Media._ID
    )

    val selection = MediaStore.Audio.Media.DATA + "=?"
    val selectionArgs = arrayOf(
        path
    )

    val cursor: Cursor? = contentResolver.query(
        collection, projection, selection, selectionArgs, null
    )
    if (cursor != null) {
        while (cursor.moveToNext()) {
            val idIndex = cursor.getColumnIndex(MediaStore.Audio.Media._ID)
            id = cursor.getString(idIndex).toLong()
        }
    }
    cursor?.close()

    return ContentUris.withAppendedId(MediaStore.Audio.Media.EXTERNAL_CONTENT_URI, id)

}

fun getMediaCollectionURI(): Uri {
    val collection: Uri = if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.Q) {
        MediaStore.Audio.Media.getContentUri(MediaStore.VOLUME_EXTERNAL)
    } else {
        MediaStore.Audio.Media.EXTERNAL_CONTENT_URI
    }
    return collection

}

