package com.nsc.movie.controller.impl;

import com.eri.topdown.movie_service.*;
import com.nsc.movie.controller.IMovieController;
import com.nsc.movie.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.ws.server.endpoint.annotation.Endpoint;
import org.springframework.ws.server.endpoint.annotation.PayloadRoot;
import org.springframework.ws.server.endpoint.annotation.RequestPayload;
import org.springframework.ws.server.endpoint.annotation.ResponsePayload;

import java.util.Arrays;
import java.util.List;

@Endpoint
public class MovieControllerImpl implements IMovieController {

    @Autowired
    private IMovieService movieService;

    protected static final String NAMESPACE_URI = "http://topdown.eri.com/movie-service";

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "listMoviesRequest")
    @ResponsePayload
    public ListMoviesResponse listMovies(@RequestPayload ListMoviesRequest request) {
        List<Movie> movieList = request.getId() == null ? movieService.getMovies()
                : Arrays.asList(movieService.findMovieById(request.getId().longValue()));
        ListMoviesResponse listMoviesResponse = new ListMoviesResponse();
        listMoviesResponse.getMovies().addAll(movieList);
        return listMoviesResponse;
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "addMovieRequest")
    @ResponsePayload
    public void addMovie(@RequestPayload AddMovieRequest addMovieRequest) {
        movieService.addMovie(addMovieRequest.getMovie());
    }

    @Override
    @PayloadRoot(namespace = NAMESPACE_URI, localPart = "deleteMovieRequest")
    @ResponsePayload
    public void deleteMovie(@RequestPayload DeleteMovieRequest deleteMovieRequest) {
        movieService.deleteMovie(deleteMovieRequest.getId().longValue());
    }
}
