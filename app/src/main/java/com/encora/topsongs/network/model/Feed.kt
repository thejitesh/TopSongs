package com.encora.topsongs.network.model

import org.simpleframework.xml.Element
import org.simpleframework.xml.ElementList
import org.simpleframework.xml.Root

@Root(name = "feed", strict = false)
data class Feed(
    @field:Element(required = false, name = "title")
    var title: String? = "",

    @field:ElementList(required = false, name = "entry", entry = "entry", inline = true, empty = true)
    var songList: List<Song> = mutableListOf()
)
