package com.personales.javatest.movies.data;

import com.personales.javatest.movies.model.Genre;
import com.personales.javatest.movies.model.Movie;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;


import java.util.Collection;

public class IMovieRespositoryJdbc implements IMovieRespository {

    private JdbcTemplate jdbcTemplate;

    public IMovieRespositoryJdbc(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }


    @Override
    public Movie findByid(long id) {
        Object[] args = {id};

        return jdbcTemplate.queryForObject("SELECT * FROM movies WHERE id = ?", args, movieMapper);
    }

    @Override
    public Collection<Movie> findAll() {
        return jdbcTemplate.query("SELECT * FROM movies", movieMapper);
    }

    @Override
    public void saveOrUpdate(Movie movie) {
        jdbcTemplate.update("INSERT INTO movies (name, minutes, genre) VALUES (?,?,?)",
                movie.getName(), movie.getMinutes(), movie.getGenre().toString());
    }


    private static RowMapper<Movie> movieMapper = (rs, rowNum) -> new Movie(
            rs.getInt("id"),
            rs.getString("name"),
            rs.getInt("minutes"),
            Genre.valueOf(rs.getString("genre")));
}
