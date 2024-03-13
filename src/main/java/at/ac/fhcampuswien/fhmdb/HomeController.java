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

    @FXML
    public JFXButton removeFilterBtn;

    public ObservableList<Movie> observableMovies = FXCollections.observableArrayList();   // automatically updates corresponding UI elements when underlying data changes

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
            addAllGenresOption();
            updateMovieListView();
        });

        // Remove Filter button:
        removeFilterBtn.setOnAction(actionEvent -> resetFilters());
    }

    // method to sort movies by ascending/descending title
    public void sortMovies(ObservableList<Movie> movies, boolean ascending) {
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
        String searchQuery = searchField.getText().toLowerCase();
        Object selectedGenre = genreComboBox.getValue();

        if ("All Genres".equals(selectedGenre)) {
            selectedGenre = null;
            genreComboBox.getSelectionModel().clearSelection();
            genreComboBox.setPromptText("Filter by Genre");
        }

        List<Movie> filteredMovies = new ArrayList<>();
        for (Movie movie : allMovies) {
            boolean matchesQuery = searchQuery.isEmpty() ||
                    movie.getTitle().toLowerCase().contains(searchQuery) ||
                    movie.getDescription().toLowerCase().contains(searchQuery);
            boolean matchesGenre = selectedGenre == null || movie.getGenres().contains(selectedGenre);
            if (matchesQuery && matchesGenre) {
                filteredMovies.add(movie);
            }
        }

        observableMovies.clear();
        observableMovies.addAll(filteredMovies);
        sortMovies(observableMovies, true);
        if (sortBtn.getText().equals("Sort (desc)")) {
            sortBtn.setText("Sort (asc)");
        }
        //movieListView.refresh();
        movieListView.setItems(observableMovies);   // set data of observable list to list view
        movieListView.setCellFactory(movieListView -> new MovieCell()); // use custom cell factory to display data
    }

    // method to set "All Genres" option
    public void addAllGenresOption() {
        if (!genreComboBox.getItems().contains("All Genres")) {
            genreComboBox.getItems().add(0, "All Genres");
        }
    }

    // method to reset all filters
    public void resetFilters() {
        searchField.clear();  // Clear the search field
        genreComboBox.getSelectionModel().clearSelection();  // Reset the genre combo box
        genreComboBox.setPromptText("Filter by Genre");
        updateMovieListView();  // Refresh the movie list view
    }

}