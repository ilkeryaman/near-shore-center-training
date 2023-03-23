package com.nsc.moviemw.model.movie;

import lombok.Data;

import java.util.List;

@Data
public class Movie {
    protected Long id;
    protected String title;
    protected List<String> categories;
    protected List<Director> directors;
    protected List<Star> stars;
}
