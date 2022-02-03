package com.encora.topsongs.network.model

import android.os.Parcelable
import com.encora.topsongs.database.TopSongTableRow
import kotlinx.parcelize.Parcelize
import org.simpleframework.xml.*

@Parcelize
@Root(name = "entry", strict = false)
data class Song @JvmOverloads constructor(
    @field:Element(name = "id", required = false)
    var id: String? = "",

    @field:Element(name = "title", required = false)
    var title: String? = "",

    @field:Element(name = "name", required = false)
    var name: String? = "",

    @field:ElementList(required = false, inline = true, empty = true)
    var link: MutableList<Link> = mutableListOf(),

    @field:ElementList(required = false, inline = true, empty = true)
    var imageList: MutableList<Image> = mutableListOf()
) : Parcelable


@Parcelize
@Root(name = "image", strict = false)
data class Image(
    @field:Attribute(name = "height", required = false)
    var height: String = "",

    @field:Text
    var imageUrl: String = ""
) : Parcelable


@Parcelize
@Root(name = "link", strict = false)
data class Link(
    @field:Attribute(name = "href", required = false)
    var href: String = "",
) : Parcelable

fun Song.convertToDatabaseTableRow(): TopSongTableRow {
    return TopSongTableRow(title = title)
}