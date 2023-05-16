package ru.wildberries.flights.repository

import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import ru.wildberries.flights.dto.Flight
import ru.wildberries.flights.dto.GetAllFlight
import ru.wildberries.flights.dto.GetFlight
import java.lang.Exception
import java.util.concurrent.TimeUnit


class FlightRepositoryImpl : FlightRepository {
    private val client = OkHttpClient.Builder()
        .connectTimeout(30, TimeUnit.SECONDS)
        .build()
    private val gson = Gson()
    private val typeToken = object : TypeToken<GetAllFlight>() {}

    companion object {
        private const val BASE_URL =
            "https://vmeste.wildberries.ru/api/avia-service/twirp/aviaapijsonrpcv1.WebAviaService/GetCheap"
        private val jsonType = "application/json".toMediaType()
    }

    data class PostRequest(
        val startLocationCode: String = "LED"
    )

    override fun getAll(): List<GetFlight> {
        val request: Request = Request.Builder()
            .post(gson.toJson(PostRequest()).toRequestBody(jsonType))
            .url(BASE_URL)
            .build()
        val response = client.newCall(request).execute()
        if (response.code != 200) {
            throw RuntimeException("bad response")
        }
        try {
            return gson.fromJson(response.body?.string(), typeToken).flights
        } catch (e: Exception) {
            throw RuntimeException("bad response")
        }

    }
}
