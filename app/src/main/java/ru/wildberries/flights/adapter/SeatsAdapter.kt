package ru.wildberries.flights.adapter

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.DiffUtil
import androidx.recyclerview.widget.ListAdapter
import androidx.recyclerview.widget.RecyclerView
import ru.wildberries.flights.databinding.CardFlightBinding
import ru.wildberries.flights.databinding.CardSeatBinding
import ru.wildberries.flights.dto.Flight
import ru.wildberries.flights.dto.Seats


class SeatsAdapter() : ListAdapter<Seats, SeatViewHolder>(SeatDiffCallback()) {
    override fun onCreateViewHolder(parent: ViewGroup, viewType: Int): SeatViewHolder {
        val binding = CardSeatBinding.inflate(LayoutInflater.from(parent.context), parent, false)
        return SeatViewHolder(binding)
    }

    override fun onBindViewHolder(holder: SeatViewHolder, position: Int) {
        val seat = getItem(position)
        holder.bind(seat)
    }
}

class SeatViewHolder(
    private val binding: CardSeatBinding,
) : RecyclerView.ViewHolder(binding.root) {

    fun bind(seats: Seats) {
        binding.apply {
            passengerType.text = seats.passengerType
            count.text = seats.count.toString()
        }
    }
}

class SeatDiffCallback : DiffUtil.ItemCallback<Seats>() {
    override fun areItemsTheSame(oldItem: Seats, newItem: Seats): Boolean {
        return oldItem.passengerType == newItem.passengerType
    }

    override fun areContentsTheSame(oldItem: Seats, newItem: Seats): Boolean {
        return oldItem == newItem
    }
}
