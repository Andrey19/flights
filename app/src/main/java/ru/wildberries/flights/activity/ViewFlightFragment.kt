package ru.wildberries.flights.activity

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.view.isVisible
import androidx.fragment.app.Fragment
import androidx.fragment.app.activityViewModels
import androidx.navigation.fragment.findNavController
import ru.wildberries.flights.adapter.FlightsAdapter
import ru.wildberries.flights.adapter.OnInteractionListener
import ru.wildberries.flights.adapter.SeatsAdapter
import ru.wildberries.flights.databinding.FragmentViewFlightBinding
import ru.wildberries.flights.util.AndroidUtils
import ru.wildberries.flights.util.LongArg
import ru.wildberries.flights.util.StringArg
import ru.wildberries.flights.viewmodel.FlightViewModel

class ViewFlightFragment : Fragment() {

    companion object {
        var Bundle.id: String? by StringArg
    }

    private val viewModel: FlightViewModel by activityViewModels()

    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View {
        val binding = FragmentViewFlightBinding.inflate(
            inflater,
            container,
            false
        )
        val searchToken = arguments?.id!!
        var flight =  viewModel.getById(searchToken)
        val adapter = SeatsAdapter()
        binding.apply {
            fromCity.text = flight.startCity +" ( " + flight.startLocationCode + " ) "
            toCity.text = flight.endCity +" ( " + flight.endLocationCode + " ) "
            departureDate.text = flight.startDate
            arrivalDate.text = flight.endDate
            serviceClass.text = flight.serviceClass
            price.text = flight.price.toString()

            like.isChecked = flight.isLiked

            binding.list.adapter = adapter
            adapter.submitList(flight.seats)


            like.setOnClickListener {
               viewModel.likeById(searchToken)
            }

            viewModel.data.observe(viewLifecycleOwner) {
                flight =  viewModel.getById(searchToken)
                if (flight != null) {
                    like.isChecked = flight.isLiked
                }
            }

            cancel.setOnClickListener {
                findNavController().navigateUp()
            }
        }



        return binding.root
    }
}
