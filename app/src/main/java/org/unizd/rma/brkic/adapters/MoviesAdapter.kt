package org.unizd.rma.brkic.adapters

import android.view.LayoutInflater
import android.view.ViewGroup
import androidx.recyclerview.widget.RecyclerView
import org.unizd.rma.brkic.databinding.ItemMovieBinding
import org.unizd.rma.brkic.model.MovieResponse


class MoviesAdapter(
    private var movies: List<MovieResponse>,
    private val onMovieClick: (MovieResponse)-> Unit
): RecyclerView.Adapter<MoviesAdapter.MovieViewHolder>()
{

    override fun onCreateViewHolder(
        parent: ViewGroup,
        viewType: Int
    ): MoviesAdapter.MovieViewHolder {
        val binding = ItemMovieBinding.inflate(LayoutInflater.from(parent.context),parent,false)
        return MovieViewHolder(binding)
    }

    override fun onBindViewHolder(holder: MoviesAdapter.MovieViewHolder, position: Int) {
        holder.bind(movies[position])
    }


    override fun getItemCount(): Int {
        return movies.size
    }

    fun updateMovies(movieList: List<MovieResponse>) {
        movies=movieList
        notifyDataSetChanged()
    }

    inner class MovieViewHolder(
        private val binding: ItemMovieBinding
    ): RecyclerView.ViewHolder(binding.root){
        fun bind(movie: MovieResponse){
            binding.tvMovieTitle.text = movie.movie.name
            binding.root.setOnClickListener{
                onMovieClick(movie)
            }
        }
    }
}