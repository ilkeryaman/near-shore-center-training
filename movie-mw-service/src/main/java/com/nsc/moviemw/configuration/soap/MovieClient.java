package com.nsc.moviemw.configuration.soap;

import com.nsc.moviemw.springsoap.client.gen.*;
import org.springframework.ws.client.core.support.WebServiceGatewaySupport;

import java.math.BigInteger;

public class MovieClient extends WebServiceGatewaySupport {

    public ListMoviesResponse listMovies(Long id) {
        ListMoviesRequest request = new ListMoviesRequest();
        if(id != null){
            request.setId(BigInteger.valueOf(id));
        }

        ListMoviesResponse response = (ListMoviesResponse) getWebServiceTemplate().marshalSendAndReceive(request);
        return response;
    }

    public void addMovie(Movie movie) {
        AddMovieRequest request = new AddMovieRequest();
        request.setMovie(movie);
        getWebServiceTemplate().marshalSendAndReceive(request);
    }

    public void deleteMovie(Long id) {
        DeleteMovieRequest request = new DeleteMovieRequest();
        request.setId(BigInteger.valueOf(id));
        getWebServiceTemplate().marshalSendAndReceive(request);
    }
}
