package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    // Enumeration of possible genres
    public enum Genre {
        ACTION, ADVENTURE, ANIMATION, BIOGRAPHY, COMEDY, CRIME, DRAMA, DOCUMENTARY, FAMILY, FANTASY, HISTORY, HORROR, MUSICAL, MYSTERY, ROMANCE, SCIENCE_FICTION, SPORT, THRILLER, WAR, WESTERN
    }
    private String title;
    private String description;
    private List<Genre> genres;
    public Movie(String title, String description, List<Genre> genres) {
        this.title = title;
        this.description = description;
        this.genres = genres;
    }

    public String getTitle() {
        return title;
    }

    public String getDescription() {
        return description;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();
        movies.add(new Movie("Life Is Beautiful", "When an open-minded Jewish waiter and his son become victims of the Holocaust, he uses a perfect mixture of will, humor and imagination to protect his son from the dangers around their camp.", List.of(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("The Usual Suspects", "The sole survivor of a pier shoot-out tells the story of how a notorious criminal influenced the events that began with five criminals meeting in a seemingly random police lineup.", List.of(Genre.CRIME, Genre.DRAMA, Genre.MYSTERY)));
        movies.add(new Movie("Puss in Boots", "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town.", List.of(Genre.COMEDY, Genre.FAMILY, Genre.ANIMATION)));
        movies.add(new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", List.of(Genre.ANIMATION, Genre.DRAMA, Genre.ACTION)));
        movies.add(new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", List.of(Genre.DRAMA, Genre.ROMANCE, Genre.BIOGRAPHY)));
        return movies;
    }
}
