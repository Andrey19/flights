package ru.wildberries.flights.activity

import ru.wildberries.flights.activity.ViewFlightFragment.Companion.id
import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.wildberries.flights.R
import ru.wildberries.flights.adapter.OnInteractionListener
import ru.wildberries.flights.adapter.FlightsAdapter
import ru.wildberries.flights.databinding.FragmentFeedBinding
import ru.wildberries.flights.dto.Flight
import ru.wildberries.flights.viewmodel.FlightViewModel

class FeedFragment : Fragment() {

    private val viewModel: FlightViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentFeedBinding.inflate(inflater, container, false)

        val adapter = FlightsAdapter(object : OnInteractionListener {
            override fun onLike(flight: Flight) {
                viewModel.likeById(flight.searchToken)
            }
            override fun onView(flight: Flight) {
                findNavController().navigate(R.id.action_feedFragment_to_viewFlightFragment,
                    Bundle().apply {
                        id = flight.searchToken
                    }
                )
            }
        })
        binding.list.adapter = adapter
        viewModel.data.observe(viewLifecycleOwner) { state ->
            adapter.submitList(state.flights)
            binding.progress.isVisible = state.loading
            binding.errorGroup.isVisible = state.error
            binding.emptyText.isVisible = state.empty
        }

        binding.retryButton.setOnClickListener {
            viewModel.loadFlights()
        }

        return binding.root
    }
}
