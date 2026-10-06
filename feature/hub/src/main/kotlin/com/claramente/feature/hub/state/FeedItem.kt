package com.claramente.feature.hub.state

data class FeedItem(
    val id: String,
    val tag: String,
    val tone: FeedTone,
    val title: String,
    val time: String,
)
