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

    public Collection<Movie> findMoviesByTemplate(Movie template){

        return movieRespository.findAll().stream().filter(
                movie -> {
                    if(template.getId() != null){
                        return movie.getId().equals(template.getId());
                    }else{
                        if(template.getMinutes() < 0){
                            throw new IllegalArgumentException("La duración de una pelicula, no puede ser negativo");
                        }else if(movie.getMinutes() <= template.getMinutes()){
                            if(template.getDirector() != null){
                                if(movie.getDirector().toLowerCase().contains(template.getDirector().toLowerCase())){
                                    return true;
                                }
                            }
                            if(template.getGenre() != null){
                                if(movie.getGenre().equals(template.getGenre())){
                                    return true;
                                }
                            }
                            if(template.getName() != null){
                                if(movie.getName().toLowerCase().contains(template.getName().toLowerCase())){
                                    return true;
                                }
                            }
                        }
                    }
                    return false;
                }
        ).collect(Collectors.toList());
    }



}
