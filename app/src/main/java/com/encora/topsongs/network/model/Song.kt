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
    var audioUrlList: MutableList<AudioUrl> = mutableListOf(),

    @field:ElementList(required = false, inline = true, empty = true)
    var imageList: MutableList<Image> = mutableListOf()
) : Parcelable


@Parcelize
@Root(name = "image", strict = false)
data class Image(
    @field:Attribute(name = "height", required = false)
    var height: String = "",

    @field:Text
    var url: String = ""
) : Parcelable


@Parcelize
@Root(name = "link", strict = false)
data class AudioUrl(
    @field:Attribute(name = "href", required = false)
    var url: String = "",
) : Parcelable

fun Song.convertToDatabaseTableRow(): TopSongTableRow {
    val tmpAudioLink = when {
        audioUrlList.size > 1 -> {
            audioUrlList[1].url
        }
        audioUrlList.isNotEmpty() -> {
            audioUrlList.first().url
        }
        else -> {
            "https://audio-ssl.itunes.apple.com/itunes-assets/AudioPreview116/v4/0b/8f/56/0b8f5687-c65c-58cd-ed4a-22956d9cb71d/mzaf_5917722721771131843.plus.aac.p.m4a"
        }
    }
    return TopSongTableRow(
        title = title,
        imageUrl = imageList.first().url,
        audioLink = tmpAudioLink
    )
}