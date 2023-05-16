package ru.wildberries.flights.repository


import ru.wildberries.flights.dto.GetFlight

interface FlightRepository {
    fun getAll(): List<GetFlight>

}
