package ru.wildberries.flights.dto

data class GetAllFlight(
    val flights : List<GetFlight>
)

data class GetFlight(
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
){
    fun toFlight(isLiked:Boolean) = Flight(searchToken,startCity,
        endCity,startLocationCode,endLocationCode,
        startDate,endDate,serviceClass,seats,
        price,isLiked)
}

data class Seats(
    val passengerType: String,
    val count: Int
)

