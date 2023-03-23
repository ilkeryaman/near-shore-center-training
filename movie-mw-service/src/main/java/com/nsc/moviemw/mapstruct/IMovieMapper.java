package com.nsc.moviemw.mapstruct;

import com.nsc.moviemw.model.movie.Movie;
import org.mapstruct.Mapper;

@Mapper(componentModel = "spring")
public interface IMovieMapper {
    Movie generatedToModel(com.nsc.moviemw.springsoap.client.gen.Movie movie);
    com.nsc.moviemw.springsoap.client.gen.Movie modelToGenerated(Movie movie);
}