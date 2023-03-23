package com.nsc.moviemw.controller;

import com.nsc.moviemw.enums.response.ResponseCode;
import com.nsc.moviemw.enums.response.ResponseMessage;
import com.nsc.moviemw.model.movie.Movie;
import com.nsc.moviemw.model.response.MovieResponse;
import com.nsc.moviemw.service.IMovieService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/movie-mw-api")
public class MovieController {

    @Autowired
    IMovieService movieService;

    @GetMapping("/movies")
    public ResponseEntity<MovieResponse> listMovies(){
        List<Movie> listOfMovies = movieService.getMovieList();
        return ResponseEntity.status(HttpStatus.OK).body(new MovieResponse<List<Movie>>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), listOfMovies));
    }

    @GetMapping("/movies/{id}")
    public ResponseEntity<MovieResponse> getMovieById(@PathVariable Long id){
        Movie movie = movieService.findMovieById(id);
        return ResponseEntity.status(HttpStatus.OK).body(new MovieResponse<Movie>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), movie));
    }

    @PostMapping("/movies")
    public ResponseEntity<MovieResponse> addMovie(@RequestBody Movie movie){
        boolean result = movieService.addMovie(movie);
        return result
                ? ResponseEntity.status(HttpStatus.CREATED).body(new MovieResponse<Movie>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), null))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MovieResponse<Movie>(ResponseCode.GENERAL_ERROR.getValue(),
                ResponseMessage.GENERAL_ERROR.getValue(), null));
    }

    @DeleteMapping("/movies/{id}")
    public ResponseEntity<MovieResponse> deleteMovie(@PathVariable Long id){
        boolean result = movieService.deleteMovie(id);
        return result
                ? ResponseEntity.status(HttpStatus.OK).body(new MovieResponse<Movie>(ResponseCode.OK.getValue(), ResponseMessage.OK.getValue(), null))
                : ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(new MovieResponse<Movie>(ResponseCode.GENERAL_ERROR.getValue(),
                ResponseMessage.GENERAL_ERROR.getValue(), null));
    }

}
