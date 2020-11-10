package com.example.migratetonewandroid.scopestorage.a10

import android.net.Uri
import androidx.recyclerview.widget.DiffUtil
import java.util.*

data class Image(val id: Long,
                 val displayName: String,
                 val dateTaken: Date,
                 val contentUri: Uri) {
    companion object {
        val DiffCallback = object : DiffUtil.ItemCallback<Image>() {
            override fun areItemsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem.id == newItem.id
            }

            override fun areContentsTheSame(oldItem: Image, newItem: Image): Boolean {
                return oldItem == newItem
            }

        }
    }
}