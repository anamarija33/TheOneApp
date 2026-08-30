package org.unizd.rma.brkic.fragments

import android.os.Bundle
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import androidx.core.os.bundleOf
import androidx.fragment.app.Fragment
import androidx.lifecycle.lifecycleScope
import androidx.navigation.fragment.findNavController
import androidx.recyclerview.widget.LinearLayoutManager
import kotlinx.coroutines.launch
import org.unizd.rma.brkic.R
import org.unizd.rma.brkic.adapters.MoviesAdapter
import org.unizd.rma.brkic.databinding.FragmentMoviesBinding
import org.unizd.rma.brkic.model.Movie
import org.unizd.rma.brkic.model.MovieResponse
import org.unizd.rma.brkic.network.ApiClient

class MovieFragment: Fragment() {
    private var _binding: FragmentMoviesBinding? = null
    private val binding get() =_binding!!
    private lateinit var moviesAdapter: MoviesAdapter


    override fun onViewCreated(view: View, savedInstanceState: Bundle?) {
        super.onViewCreated(view, savedInstanceState)
        setupRecyclerView()
        loadMovies()
    }

    private fun loadMovies() {
        viewLifecycleOwner.lifecycleScope.launch {
            val response = ApiClient.theOneApi.getMovies()
            if(response.isSuccessful){
                moviesAdapter.updateMovies(response.body()?.docs ?: emptyList())
            }
        }
    }

    private fun setupRecyclerView() {
        moviesAdapter = MoviesAdapter(emptyList()){ movie -> navigateToMovieDetail(movie)}
        binding.rvMovies.apply{
            layoutManager = LinearLayoutManager(requireContext())
            adapter=moviesAdapter
        }
      }

    private fun navigateToMovieDetail(movie: MovieResponse) {
        val bundle = bundleOf(
            "movieId" to movie.id,
            "movieName" to movie.name
        )
        findNavController().navigate(R.id.action_movieFragment_to_movieDetailsFragment,bundle)

    }



    override fun onCreateView(
        inflater: LayoutInflater,
        container: ViewGroup?,
        savedInstanceState: Bundle?
    ): View? {
        _binding=FragmentMoviesBinding.inflate(inflater,container,false)
        return binding.root
    }

    override fun onDestroyView() {
        super.onDestroyView()
        _binding=null
    }
}