package org.unizd.rma.brkic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import kotlinx.coroutines.launch
import org.unizd.rma.brkic.databinding.FragmentMovieDetailsBinding
import org.unizd.rma.brkic.network.ApiClient

class MovieDetailsFragment: Fragment() {
    private var _binding: FragmentMovieDetailsBinding? = null
    private val binding get() = _binding!!


    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding = FragmentMovieDetailsBinding.inflate(inflater,container,false )
        return binding.root
    }

    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        val movieId = requireArguments().getString("movieId")
        // Postavljanje grada u header
        binding.tvMovieName.text =movieId


        // Učitavanje podataka
        if(movieId!=null){
        loadMovieDetails(movieId)}
    }

    private fun loadMovieDetails(movieId:String) {
        showLoading()
        viewLifecycleOwner.lifecycleScope.launch {
            try {
                val response = ApiClient.theOneApi.getMovie(movieId)

                if (response.isSuccessful && response.body() != null) {
                    val movieDetails = response.body()?.docs?.firstOrNull() ?: return@launch
                    binding.tvMovieId.text = "Movie id:${movieDetails.id} "
                    binding.tvMovieName.text = "Movie title: ${movieDetails.name}"
                    binding.tvMovieruntime.text="Runtime: ${movieDetails.runtimeInMinutes} minutes"
                    binding.tvMovieBudget.text = "Budget: ${movieDetails.budgetInMillions}M"
                    binding.tvMovieRevenue.text = "Box office: ${movieDetails.boxOfficeRevenueInMillions}M"
                    binding.tvMovieAcademyRewardNominations.text = "Academy Award nominations: ${movieDetails.academyAwardNominations}"
                    binding.tvMovieAwardWins.text = "Academy Award wins: ${movieDetails.academyAwardWins}"
                    binding.tvMovieRottenTomatoScore.text = "Rotten Tomatoes: ${movieDetails.rottenTomatoesScore}%"

                    displayMovieData()
                } else {
                    showError("Greška: ${response.code()} - ${response.message()}")
                }

            } catch (e: Exception) {
                showError("Greška pri učitavanju: ${e.message}")
            }
        }


    }
    private fun displayMovieData() {
        binding.progressBar.visibility = View.GONE
        binding.headerCard.visibility = View.VISIBLE
        binding.textViewError.visibility = View.GONE

    }
    private fun showLoading() {
        binding.progressBar.visibility = View.VISIBLE
        binding.headerCard.visibility = View.GONE
        binding.textViewError.visibility = View.GONE
    }
    private fun showError(message: String) {
        binding.progressBar.visibility = View.GONE
        binding.headerCard.visibility = View.GONE
        binding.textViewError.visibility = View.VISIBLE
        binding.textViewError.text = message
    }
    override fun onDestroyView() {
        super.onDestroyView()
        _binding = null
    }
}