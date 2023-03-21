package com.nsc.movie.service;

import com.eri.topdown.movie_service.Movie;

import java.util.List;

public interface IMovieService {
    List<Movie> getMovies();
    Movie findMovieById(long id);
    void addMovie(Movie movie);
    void deleteMovie(long id);
}
