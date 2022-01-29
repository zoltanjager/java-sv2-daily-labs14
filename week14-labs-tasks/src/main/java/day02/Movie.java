package day02;

import java.time.LocalTime;
import java.util.List;

public class Movie {
    private String title;
    private LocalTime length;
    private List<String> actors;

    public Movie(String title, LocalTime length, List<String> actors) {
        this.title = title;
        this.length = length;
        this.actors = actors;
    }

    public String getTitle() {
        return title;
    }

    public LocalTime getLength() {
        return length;
    }

    public List<String> getActors() {
        return actors;
    }
}
