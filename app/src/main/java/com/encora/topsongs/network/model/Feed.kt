package com.encora.topsongs.network.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "feed", strict = false)
data class Feed(
    @field:Element(required = false, name = "title")
    var title: String? = "",

    @field:Element(required = false, name = "updated")
    var updated: String? = "",

    @field:Element(required = false, name = "icon")
    var icon: String? = "",

    @field:ElementList(required = false, name = "entry", entry = "entry", inline = true, empty = true)
    var songList: List<Song> = mutableListOf()
)
