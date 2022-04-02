package com.personales.javatest.movies.data;

import com.personales.javatest.movies.model.Genre;
import com.personales.javatest.movies.model.Movie;
import org.junit.*;
import org.springframework.core.io.ClassPathResource;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.datasource.DriverManagerDataSource;
import org.springframework.jdbc.datasource.init.ScriptUtils;

import javax.sql.DataSource;

import java.sql.SQLException;
import java.sql.Statement;
import java.util.Arrays;
import java.util.Collection;

import static org.hamcrest.CoreMatchers.*;
import static org.junit.Assert.*;


public class IMovieRespositoryJdbcIntegrationTest {


    private IMovieRespositoryJdbc movieRepository;
    private DataSource dataSource;

    @Before
    public void setUp() throws Exception {
        dataSource = new DriverManagerDataSource("jdbc:h2:mem:test;MODE=MYSQL", "sasa", "sa");

        ScriptUtils.executeSqlScript(dataSource.getConnection(), new ClassPathResource("sql-scripts/test-data.sql"));

        JdbcTemplate jdbcTemplate = new JdbcTemplate(dataSource);

        movieRepository = new IMovieRespositoryJdbc(jdbcTemplate);
    }

    @After
    public void tearDown() throws Exception {
        //remove H2 files -- https://stackoverflow.com/a/51809831/1121497
        final Statement s = dataSource.getConnection().createStatement();
        s.execute("drop all objects delete files");
    }

    @Test
    public void load_all_movies() throws SQLException {



        Collection<Movie> movies = movieRepository.findAll();

        assertThat(movies, is(Arrays.asList(
                new Movie(1, "Dark Knight", 152, Genre.ACTION, "Christopher Nolan"),
                new Movie(2, "Memento", 113, Genre.THRILLER, "Christopher Nolan"),
                new Movie(3, "Matrix", 136, Genre.ACTION, "Hermanas Wachowski")
                )));

    }

    @Test
    public void load_movie_by_id() {
        Movie movie  = movieRepository.findByid(2);

        assertThat(movie, is(new Movie(2, "Memento", 113, Genre.THRILLER, "Christopher Nolan")));


    }

    @Test
    public void insert_a_movie() {
        Movie movie = new Movie("Super 8", 112, Genre.THRILLER, "J. J. Abrams");
        movieRepository.saveOrUpdate(movie);

        Movie movieFromDb = movieRepository.findByid(4);

        assertThat(movieFromDb, is(new Movie(4,"Super 8", 112, Genre.THRILLER, "J. J. Abrams")));
    }
}