package ru.wildberries.flights.viewmodel

import android.app.Application
import androidx.lifecycle.*
import ru.wildberries.flights.dto.Flight
import ru.wildberries.flights.model.FeedModel
import ru.wildberries.flights.repository.*
import java.io.IOException
import kotlin.concurrent.thread

private val empty = Flight(
    searchToken = "",
    startCity = "",
    endCity = "",
    startLocationCode = "",
    endLocationCode = "",
    startDate = "",
    endDate = "",
    serviceClass = "",
    seats = emptyList(),
    price = 0.0,
    isLiked = false,
)

class FlightViewModel(application: Application) : AndroidViewModel(application) {
    // упрощённый вариант
    private val repository: FlightRepository = FlightRepositoryImpl()
    private val _data = MutableLiveData(FeedModel())
    val data: LiveData<FeedModel>
        get() = _data

    init {
        loadFlights()
    }

    fun loadFlights() {
        thread {
            _data.postValue(FeedModel(loading = true))
            try {
                val flights = repository.getAll()
                FeedModel(flights = flights.map {
                    it.toFlight(false)
                }, empty = flights.isEmpty())
            } catch (e: IOException) {
                FeedModel(error = true)
            }.also(_data::postValue)
        }
    }
    fun getById(searchToken: String): Flight{
        return _data.value!!.flights.first {
             it.searchToken == searchToken
        }
    }
    fun likeById(searchToken: String): Flight{
        _data.postValue(FeedModel(flights = _data.value!!.flights.map {
            if (it.searchToken != searchToken) it else it.copy(isLiked = !it.isLiked)
        }))
        return getById(searchToken)
    }


}
