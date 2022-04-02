package com.personales.javatest.movies.service;

import com.personales.javatest.movies.data.IMovieRespository;
import com.personales.javatest.movies.model.Genre;
import com.personales.javatest.movies.model.Movie;
import org.junit.After;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Mockito;
import org.mockito.MockitoAnnotations;
import org.mockito.junit.MockitoJUnitRunner;
import static org.hamcrest.CoreMatchers.*;

import java.util.Arrays;
import java.util.Collection;
import java.util.List;
import java.util.stream.Collectors;

import static org.junit.Assert.*;

@RunWith(MockitoJUnitRunner.class)
public class MovieServiceTest {


    @Mock
    IMovieRespository movieRespository;

    @InjectMocks
    MovieService movieService;


    @Before
    public void setUp() throws Exception {
        MockitoAnnotations.initMocks(this);
        movieService = new MovieService(movieRespository);


        Mockito.when(movieRespository.findAll()).thenReturn(
                Arrays.asList(
                        new Movie(1, "Dark Knight", 152, Genre.ACTION, "Christopher Nolan"),
                        new Movie(2, "Memento", 113, Genre.THRILLER, "Christopher Nolan"),
                        new Movie(3, "There's Something About Mary", 119, Genre.COMEDY, "Peter Farrelly, Bobby Farrelly"),
                        new Movie(4, "Super 8", 112, Genre.THRILLER, "J. J. Abrams"),
                        new Movie(5, "Scream", 111, Genre.HORROR, "Wes Craven"),
                        new Movie(6, "Home Alone", 103, Genre.COMEDY, "Chris Columbus, Raja Gosnell, Peter Hewitt, Rod Daniel"),
                        new Movie(7, "Matrix", 136, Genre.ACTION, "Hermanas Wachowski"),
                        new Movie(8, "Dark Knight Rises", 152, Genre.ACTION, "Christopher Nolan"),
                        new Movie(9, "The Matrix Reloaded", 136, Genre.ACTION, "Hermanas Wachowski"),
                        new Movie(10, "The Matrix Revolutions", 136, Genre.ACTION, "Hermanas Wachowski")
                ));

    }

    @After
    public void tearDown() throws Exception {
        Mockito.validateMockitoUsage();
        Mockito.clearInvocations();
    }

    @Test
    public void return_movies_by_genre(){
        Collection<Movie> movies = movieService.findMoviesByGenre(Genre.COMEDY);
        assertThat(getMovieIds(movies), is(Arrays.asList(3,6)));
        
    }

    @Test
    public void return_movies_by_duration() {
        Collection<Movie> movies = movieService.findMoviesByDuration(119);
        assertThat(getMovieIds(movies), is(Arrays.asList(2,3,4,5,6)));
    }


    @Test
    public void return_movies_by_name(){
        Collection<Movie> movies = movieService.findMoviesByName("matrix");
        assertThat(getMovieIds(movies), is(Arrays.asList(7,9,10)));
    }

    @Test
    public void return_movies_by_director() {
        Collection<Movie> movies = movieService.findMoviesByDirector("christopher");
        assertThat(getMovieIds(movies), is(Arrays.asList(1,2,8)));
    }

    @Test
    public void return_movies_by_template() {
        Integer id = 1;
        String name = null; // no queremos buscar por nombre
        Integer minutes = 150; // 2h 30m
        Genre genre = Genre.ACTION;
        String director = null;
        Movie template = new Movie(name, minutes, genre, director);
        Collection<Movie> movies =
                movieService.findMoviesByTemplate(template);
        assertThat(getMovieIds(movies), is(Arrays.asList(7,9,10)) );
    }

    private List<Integer> getMovieIds(Collection<Movie> movies) {
        return movies.stream().map(Movie::getId).collect(Collectors.toList());
    }
}