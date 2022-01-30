package day02;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

import java.time.LocalTime;
import java.util.Arrays;
import java.util.List;

import static org.junit.jupiter.api.Assertions.*;

class MovieServiceTest {

    MovieService movieService;

    @BeforeEach
    void intit () {
        movieService = new MovieService();

        movieService.addMovie(new Movie("Titanic", LocalTime.of(3, 50), List.of("Leonardo", "Kate Winslet")));
        movieService.addMovie(new Movie("Star Wars", LocalTime.of(2, 50), List.of("Harrison Ford", "Mark Hamilton")));
        movieService.addMovie(new Movie("Fugitive", LocalTime.of(1, 50), List.of("Harrison Ford", "Tommy Lee Jones")));

    }


    @Test
    void testGetMoviesIncludedActor(){

        List<Movie> result = movieService.getMoviesIncludedActor("Harrison Ford");

        System.out.println(result.size());
    }

    @Test
    void testGetLongestMovie(){

        System.out.println(movieService.getLongestMovie().toString());
    }
}