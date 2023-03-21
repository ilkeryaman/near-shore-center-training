package com.nsc.movie.controller;

import com.eri.topdown.movie_service.AddMovieRequest;
import com.eri.topdown.movie_service.DeleteMovieRequest;
import com.eri.topdown.movie_service.ListMoviesRequest;
import com.eri.topdown.movie_service.ListMoviesResponse;

public interface IMovieController {
    ListMoviesResponse listMovies(ListMoviesRequest request);
    void addMovie(AddMovieRequest addMovieRequest);
    void deleteMovie(DeleteMovieRequest deleteMovieRequest);
}
