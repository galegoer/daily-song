package com.mongodb.app.domain

import org.mongodb.kbson.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.Date

class Song() : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()

    var song_title: String = ""
    var artist: String = ""
    var album: String = ""
    var album_url: String = ""
    var rating: Double = 0.0
    var youtube_url: String = ""
    var date_used: Date? = null
    var song_of_day: Boolean = false


//    TODO: May need to be incorporated later?
//    override fun equals(other: Any?): Boolean {
//        if (other == null) return false
//        if (other !is Song) return false
//        if (this._id != other._id) return false
//
//        if (this.song_title != other.song_title) return false
//        if (this.artist != other.artist) return false
//        if (this.album != other.album) return false
//        if (this.album_url != other.album_url) return false
//        if (this.rating != other.rating) return false
//        if (this.youtube_url != other.youtube_url) return false
//        if (this.date_used != other.date_used) return false
//        return true
//    }
}
