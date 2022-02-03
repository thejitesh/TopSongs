package com.encora.topsongs.network.model

import com.encora.topsongs.database.TopSongTableRow
import org.simpleframework.xml.Element
import org.simpleframework.xml.Root

@Root(name = "entry", strict = false)
data class Song(
    @field:Element(name = "id", required = false)
    var id: String? = "",

    @field:Element(name = "title", required = false)
    var title: String? = "",

    @field:Element(name = "name", required = false)
    var name: String? = "",

    @field:Element(name = "link", required = false)
    var link: String? = null
)

fun Song.convertToDatabaseTableRow() : TopSongTableRow{
    return TopSongTableRow(title = title)
}

/*@Root(name = "link", strict = false)
data class Link (
    @field:Attribute(name = "href", required = false)
    var href: String = ""
    *//*   @field:Text var text: String? = null*//*
)*/
