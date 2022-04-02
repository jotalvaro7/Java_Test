package com.personales.javatest.movies.service;

import com.personales.javatest.movies.data.IMovieRespository;
import com.personales.javatest.movies.model.Genre;
import com.personales.javatest.movies.model.Movie;

import java.util.Collection;
import java.util.stream.Collectors;

public class MovieService {

    private IMovieRespository movieRespository;

    public MovieService(IMovieRespository movieRespository) {
        this.movieRespository = movieRespository;
    }

    public Collection<Movie> findMoviesByGenre(Genre genre){

        return movieRespository.findAll().stream().filter(movie -> movie.getGenre() == genre).collect(Collectors.toList());

    }


    public Collection<Movie> findMoviesByDuration(int duration) {
        return movieRespository.findAll().stream().filter(movie -> movie.getMinutes() <= duration).collect(Collectors.toList());
    }


    public Collection<Movie> findMoviesByName(String name){
        return movieRespository.findAll().stream().filter(movie -> movie.getName().toLowerCase().contains(name)).collect(Collectors.toList());
    }

    public Collection<Movie> findMoviesByDirector(String director){
        return movieRespository.findAll().stream().filter(movie -> movie.getDirector().toLowerCase().contains(director)).collect(Collectors.toList());
    }


}
