package com.mongodb.app.domain

import org.mongodb.kbson.ObjectId
import io.realm.kotlin.types.RealmObject
import io.realm.kotlin.types.annotations.Ignore
import io.realm.kotlin.types.annotations.PrimaryKey
import java.util.Date

open class Song() : RealmObject {
    @PrimaryKey
    var _id: ObjectId = ObjectId()

    var album: String? = null
    var artist: String = ""
    var image_url: String? = null
    var song_of_day: Boolean? = null
    var song_title: String = ""
    var youtube_url: String? = null

    constructor(song_title: String, artist: String) : this() {
        this.song_title = song_title
        this.artist = artist
    }

    // TODO: This may need to be changed
//    @Ignore
//    var date_used: Date? = null
//    @Ignore
//    var tags: Array<String> = arrayOf()
    //    var rating: Double = 0.0


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
