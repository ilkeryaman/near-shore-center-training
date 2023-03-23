package com.nsc.movie.service.impl;

import com.eri.topdown.movie_service.Director;
import com.eri.topdown.movie_service.Movie;
import com.eri.topdown.movie_service.Star;
import com.nsc.movie.enums.Category;
import com.nsc.movie.service.IMovieService;
import org.springframework.stereotype.Service;

import javax.annotation.PostConstruct;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

@Service
public class MovieMemoryDBServiceImpl implements IMovieService {

    private List<Movie> movies;

    MovieMemoryDBServiceImpl(){
        movies = new ArrayList<>();
    }

    @PostConstruct
    private void initData(){
        Director director1 = new Director();
        director1.setName("Gizem");
        director1.setSurname("Solum");

        Director director2 = new Director();
        director1.setName("Abidath");
        director2.setSurname("Batchan");

        Star star1 = new Star();
        star1.setName("Sinan");
        star1.setSurname("Bulubay");

        Star star2 = new Star();
        star2.setName("Abdul Samet");
        star2.setSurname("Altunsoy");

        Star star3 = new Star();
        star3.setName("Ilker");
        star3.setSurname("Yaman");

        Movie movie1 = new Movie();
        movie1.setId(BigInteger.valueOf(1));
        movie1.setTitle("The Best Movie");
        movie1.getCategories().addAll(Arrays.asList(Category.DRAMA.getValue(), Category.ACTION.getValue()));
        movie1.getDirectors().add(director1);
        movie1.getStars().addAll(Arrays.asList(star1, star2));

        Movie movie2 = new Movie();
        movie2.setId(BigInteger.valueOf(2));
        movie2.setTitle("The Worst Movie");
        movie2.getCategories().addAll(Arrays.asList(Category.COMEDY.getValue(), Category.FANTASY.getValue()));
        movie2.getDirectors().add(director2);
        movie2.getStars().addAll(Arrays.asList(star3));

        movies.add(movie1);
        movies.add(movie2);
    }

    @Override
    public List<Movie> getMovies() {
        return movies;
    }

    @Override
    public Movie findMovieById(long id) {
        return movies.stream().filter(movie -> movie.getId().equals(BigInteger.valueOf(id))).findFirst().orElse(null);
    }

    @Override
    public void addMovie(Movie movie) {
        movies.add(movie);
    }

    @Override
    public void deleteMovie(long id) {
        movies.removeIf(movie -> movie.getId().equals(BigInteger.valueOf(id)));
    }
}
