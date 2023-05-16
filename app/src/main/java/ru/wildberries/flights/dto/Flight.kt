package ru.wildberries.flights.dto

data class Flight(
    val searchToken: String,
    val startCity: String,
    val endCity: String,
    val startLocationCode: String,
    val endLocationCode: String,
    val startDate: String,
    val endDate: String,
    val serviceClass: String,
    val seats: List<Seats>,
    val price: Double,
    val isLiked: Boolean,
)

