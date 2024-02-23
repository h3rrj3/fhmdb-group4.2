package at.ac.fhcampuswien.fhmdb.models;

import java.util.ArrayList;
import java.util.List;

public class Movie {
    // enumeration of possible genres for List<Genre>
    public enum Genre {
        ACTION, ADVENTURE, ANIMATION, BIOGRAPHY, COMEDY, CRIME, DRAMA, DOCUMENTARY, FAMILY, FANTASY, HISTORY, HORROR, MUSICAL, MYSTERY, ROMANCE, SCIENCE_FICTION, SPORT, THRILLER, WAR, WESTERN
    }
    private String title;
    private String description;
    private List<Genre> genres; // added a List to carry the genre information

    // modified Movie constructor to support genres by adding List<Genre> as parameter and genres as another property
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

    // added getter for genres
    public List<Genre> getGenres() {
        return genres;
    }

    public static List<Movie> initializeMovies(){
        List<Movie> movies = new ArrayList<>();

        // added dummy movies; genres are formatted to a single object by using List.of
        movies.add(new Movie("How to Lose a Guy in 10 Days", "Benjamin Barry is an advertising executive and ladies' man who, to win a big campaign, bets that he can make a woman fall in love with him in 10 days.", List.of(Genre.ROMANCE, Genre.DRAMA)));
        movies.add(new Movie("Oppenheimer", "The story of American scientist J. Robert Oppenheimer and his role in the development of the atomic bomb.", List.of(Genre.DRAMA, Genre.HISTORY)));
        movies.add(new Movie("The Lion King", "Lion prince Simba and his father are targeted by his bitter uncle, who wants to ascend the throne himself.", List.of(Genre.ANIMATION, Genre.FAMILY, Genre.ADVENTURE)));
        movies.add(new Movie("Star Trek II: The Wrath of Khan", "With the assistance of the Enterprise crew, Admiral Kirk must stop an old nemesis, Khan Noonien Singh, from using the life-generating Genesis Device as the ultimate weapon.", List.of(Genre.SCIENCE_FICTION, Genre.ADVENTURE, Genre.ACTION)));
        movies.add(new Movie("The Lord of the Rings: The Return of the King", "Gandalf and Aragorn lead the World of Men against Sauron's army to draw his gaze from Frodo and Sam as they approach Mount Doom with the One Ring.", List.of(Genre.FANTASY, Genre.ADVENTURE)));
        movies.add(new Movie("Les Misérables", "In 19th-century France, Jean Valjean, who for decades has been hunted by the ruthless policeman Javert after breaking parole, agrees to care for a factory worker's daughter. The decision changes their lives forever.", List.of(Genre.MUSICAL, Genre.HISTORY)));
        movies.add(new Movie("The Hateful Eight", "In the dead of a Wyoming winter, a bounty hunter and his prisoner find shelter in a cabin currently inhabited by a collection of nefarious characters.", List.of(Genre.WESTERN, Genre.THRILLER)));
        movies.add(new Movie("Starship Troopers", "Humans in a fascist, militaristic future wage war with giant alien bugs.", List.of(Genre.SCIENCE_FICTION, Genre.WAR, Genre.ACTION, Genre.HORROR)));
        movies.add(new Movie("The Shining", "A family heads to an isolated hotel for the winter where a sinister presence influences the father into violence, while his psychic son sees horrific forebodings from both past and future.", List.of(Genre.HORROR, Genre.THRILLER)));
        movies.add(new Movie("Invictus", "Nelson Mandela, in his first term as President of South Africa, initiates a unique venture to unite the Apartheid-torn land: enlist the national rugby team on a mission to win the 1995 Rugby World Cup.", List.of(Genre.DOCUMENTARY, Genre.HISTORY, Genre.SPORT)));
        movies.add(new Movie("Life Is Beautiful", "When an open-minded Jewish waiter and his son become victims of the Holocaust, he uses a perfect mixture of will, humor and imagination to protect his son from the dangers around their camp.", List.of(Genre.DRAMA, Genre.ROMANCE)));
        movies.add(new Movie("The Usual Suspects", "The sole survivor of a pier shoot-out tells the story of how a notorious criminal influenced the events that began with five criminals meeting in a seemingly random police lineup.", List.of(Genre.CRIME, Genre.DRAMA, Genre.MYSTERY)));
        movies.add(new Movie("Puss in Boots", "An outlaw cat, his childhood egg-friend, and a seductive thief kitty set out in search for the eggs of the fabled Golden Goose to clear his name, restore his lost honor, and regain the trust of his mother and town.", List.of(Genre.COMEDY, Genre.FAMILY, Genre.ANIMATION)));
        movies.add(new Movie("Avatar", "A paraplegic Marine dispatched to the moon Pandora on a unique mission becomes torn between following his orders and protecting the world he feels is his home.", List.of(Genre.ANIMATION, Genre.DRAMA, Genre.ACTION)));
        movies.add(new Movie("The Wolf of Wall Street", "Based on the true story of Jordan Belfort, from his rise to a wealthy stock-broker living the high life to his fall involving crime, corruption and the federal government.", List.of(Genre.DRAMA, Genre.ROMANCE, Genre.BIOGRAPHY)));

        return movies;
    }
}
