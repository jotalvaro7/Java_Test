package com.personales.javatest.movies.data;

import com.personales.javatest.movies.model.Movie;

import java.util.Collection;

public interface IMovieRespository {

    Movie findByid(long id);
    Collection<Movie> findAll();
    void saveOrUpdate(Movie movie);

}
