package com.nsc.moviemw.model.response;

import lombok.AllArgsConstructor;
import lombok.Data;

@Data
@AllArgsConstructor
public class MovieResponse<T> {
    private String code;
    private String message;
    private T data;
}
