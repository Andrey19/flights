package ru.wildberries.flights.model

import ru.wildberries.flights.dto.Flight

data class FeedModel(
    val flights: List<Flight> = emptyList(),
    val loading: Boolean = false,
    val error: Boolean = false,
    val empty: Boolean = false,
    val refreshing: Boolean = false,
)
