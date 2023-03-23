package com.nsc.moviemw.service;

import com.nsc.moviemw.model.movie.Movie;

import java.util.List;

public interface IMovieService {
    List<Movie> getMovieList();

    Movie findMovieById(Long id);

    boolean addMovie(Movie movie);
    boolean deleteMovie(long id);
}
