package day02;

import java.time.LocalTime;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.stream.Collectors;

public class MovieService {
    private List<Movie> movies = new ArrayList<>();


    public void addMovie(Movie movie){
        movies.add(movie);
    }


    public List<Movie> getMoviesIncludedActor (String actor){
        return movies.stream()
                .filter(movie -> movie.getActors().contains(actor))
                .collect(Collectors.toList());
    }

    public LocalTime getLongestMovie (){
        return movies.stream()
                .max(Comparator.comparing(Movie::getLength)).get().getLength();
    }

}
