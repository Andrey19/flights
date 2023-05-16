package ru.wildberries.flights.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.wildberries.flights.databinding.CardFlightBinding
import ru.wildberries.flights.dto.Flight

interface OnInteractionListener {
    fun onLike(flight: Flight) {}
    fun onView(flight: Flight) {}

}

class FlightsAdapter(
    private val onInteractionListener: OnInteractionListener,
) : ListAdapter<Flight, FlightViewHolder>(FlightDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): FlightViewHolder {
        val binding = CardFlightBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return FlightViewHolder(binding, onInteractionListener)
    }

    override fun onBindViewHolder(holder: FlightViewHolder, position: Int) {
        val flight = getItem(position)
        holder.bind(flight)
    }
}

class FlightViewHolder(
    private val binding: CardFlightBinding,
    private val onInteractionListener: OnInteractionListener,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(flight: Flight) {
        binding.apply {
            fromCity.text = flight.startCity
            toCity.text = flight.endCity
            departureDate.text = flight.startDate
            arrivalDate.text = flight.endDate
            price.text = flight.price.toString()
            like.isChecked = flight.isLiked

            root.setOnClickListener{
                onInteractionListener.onView(flight)
            }

            like.setOnClickListener {
                onInteractionListener.onLike(flight)
            }

        }
    }
}

class FlightDiffCallback : DiffUtil.ItemCallback<Flight>() {
    override fun areItemsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem.searchToken == newItem.searchToken
    }

    override fun areContentsTheSame(oldItem: Flight, newItem: Flight): Boolean {
        return oldItem == newItem
    }
}
