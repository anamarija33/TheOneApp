package org.unizd.rma.brkic.utils

import android.content.Context
import android.content.SharedPreferences
import org.unizd.rma.brkic.model.Movie
import com.google.gson.Gson

class PreferencesManager (context: Context){
    private val sharedPreferences: SharedPreferences = context.getSharedPreferences(
        PREFS_NAME,
        Context.MODE_PRIVATE
    )
    private val gson = Gson()

    companion object{
        private const val PREFS_NAME="movies_app_prefs"
        private const val KEY_SELECTED_MOVIE="selected_movie"
    }

    fun saveSelectedMovie(movie: Movie){
        sharedPreferences.edit()
            .putString(KEY_SELECTED_MOVIE, gson.toJson(movie))
            .apply()
    }

    fun getSelectedMovie(): Movie?{
        val json= sharedPreferences.getString(KEY_SELECTED_MOVIE,null)?:return null
        return gson.fromJson(json,Movie::class.java)
    }

    fun hasSelectedMovie(): Boolean{
        return getSelectedMovie()!=null
    }
}