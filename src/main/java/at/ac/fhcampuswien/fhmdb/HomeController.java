package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXComboBox;
import com.jfoenix.controls.JFXListView;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.TextField;

import java.net.URL;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.ResourceBundle;

public class HomeController implements Initializable {
    @FXML
    public JFXButton searchBtn;

    @FXML
    public TextField searchField;

    @FXML
    public JFXListView movieListView;

    @FXML
    public JFXComboBox genreComboBox;

    @FXML
    public JFXButton sortBtn;

    public List<Movie> allMovies = Movie.initializeMovies();

    private ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

    @Override
    public void initialize(URL url, ResourceBundle resourceBundle) {
        observableMovies.addAll(allMovies);         // add dummy data to observable list
        sortMovies(observableMovies, true); // to sort in ascending order

        // initialize UI stuff
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data

        // genre filter dropdown selection
        genreComboBox.setPromptText("Filter by Genre");
        genreComboBox.getItems().addAll(Movie.Genre.values());  // defines the possible elements of the genre filter
        //genreComboBox.setItems(FXCollections.observableArrayList(Movie.Genre.values()));  // alternative to line above by using FXcollections and observableArrayListe

        // Sort button:
        sortBtn.setOnAction(actionEvent -> {
            if(sortBtn.getText().equals("Sort (asc)")) {
                sortMovies(observableMovies, false); // to sort in descending order
                sortBtn.setText("Sort (desc)");
            } else {
                sortMovies(observableMovies, true); // to sort in ascending order
                sortBtn.setText("Sort (asc)");
            }
        });

        // Filter button:
        searchBtn.setOnAction(actionEvent -> {
            updateMovieListView();
        });
    }
    // method to sort movies by ascending/descending title
    private void sortMovies(ObservableList<Movie> movies, boolean ascending) {
        if (ascending) {
            // sort in ascending order
            FXCollections.sort(movies, Comparator.comparing(Movie::getTitle));
        } else {
            // sort in descending order
            FXCollections.sort(movies, Comparator.comparing(Movie::getTitle).reversed());
        }
    }

    // method to update observableMovies according to the user inputs regarding queries and/or genre
    public void updateMovieListView() {
        String searchQuery = searchField.getText().toLowerCase();  // checks the user input and pulls everything to lower case
        Movie.Genre selectedGenre = (Movie.Genre) genreComboBox.getValue(); // checks the selected genre

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie allMovie : allMovies) {
            boolean matchesQuery = searchQuery.isEmpty() ||
                    allMovie.getTitle().toLowerCase().contains(searchQuery) ||
                    allMovie.getDescription().toLowerCase().contains(searchQuery);
            boolean matchesGenre = selectedGenre == null || allMovie.getGenres().contains(selectedGenre);
            if (matchesQuery && matchesGenre) {
                filteredMovies.add(allMovie);
            }
        }

        //observableMovies.setAll(filteredMovies); // sets observableMovies to filtered movies
        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
        sortMovies(observableMovies, true); // to sort in ascending order
        if(sortBtn.getText().equals("Sort (desc)")) { // to guarantee Sort (asc) on sortBtn on fresh movieListView
            sortBtn.setText("Sort (asc)");
        }
        movieListView.refresh();
    }


}