package com.nsc.moviemw.service.impl;

import com.nsc.moviemw.configuration.soap.MovieClient;
import com.nsc.moviemw.mapstruct.IMovieMapper;
import com.nsc.moviemw.model.movie.Movie;
import com.nsc.moviemw.service.IMovieService;
import com.nsc.moviemw.springsoap.client.gen.ListMoviesResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.ArrayList;
import java.util.List;

@Service
public class MovieServiceImpl implements IMovieService {
    @Autowired
    private MovieClient movieClient;

    @Autowired
    private IMovieMapper movieMapper;

    @Override
    public List<Movie> getMovieList() {
        ListMoviesResponse response = movieClient.listMovies(null);
        List<Movie> movies = new ArrayList<>();
        response.getMovies().forEach(movie ->
                movies.add(movieMapper.generatedToModel(movie)));
        return movies;
    }

    @Override
    public Movie findMovieById(Long id) {
        ListMoviesResponse response = movieClient.listMovies(id);
        if(response.getMovies() != null && response.getMovies().size() > 0){
            return movieMapper.generatedToModel(response.getMovies().get(0));
        }
        else return null;
    }

    @Override
    public boolean addMovie(Movie movie) {
        movieClient.addMovie(movieMapper.modelToGenerated(movie));
        return true;
    }

    @Override
    public boolean deleteMovie(long id) {
        movieClient.deleteMovie(id);
        return true;
    }
}
