package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.scene.Scene;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.testfx.framework.junit5.ApplicationExtension;
import org.testfx.framework.junit5.Start;

import java.util.ArrayList;
import java.util.Arrays;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public class HomeControllerTest {
    private HomeController controller;

    @Start
    public void start(Stage stage) {
        Platform.runLater(() -> {
            controller = new HomeController();
            controller.searchField = new TextField();
            controller.genreComboBox = new JFXComboBox();
            controller.sortBtn = new JFXButton(); // Initialize sortBtn
            controller.movieListView = new JFXListView(); // Initialize movieListView
            stage.setScene(new Scene(controller.searchField));
            stage.show();
        });
    }

    @BeforeEach
    public void setUp() {
        Platform.runLater(() -> {
            controller.allMovies = Arrays.asList(
                    new Movie("A", "desc", new ArrayList<>()),
                    new Movie("B", "desc", new ArrayList<>())
            );
            controller.observableMovies = FXCollections.observableArrayList(controller.allMovies);
        });
    }

    @Test
    public void sortMoviesShouldSortInAscendingOrder() {
        Platform.runLater(() -> {
            // Given
            controller.observableMovies = FXCollections.observableArrayList(
                    new Movie("B", "desc", new ArrayList<>()),
                    new Movie("A", "desc", new ArrayList<>())
            );

            // When
            controller.sortMovies(controller.observableMovies, true);

            // Then
            assertEquals("A", controller.observableMovies.get(0).getTitle());
            assertEquals("B", controller.observableMovies.get(1).getTitle());
        });
    }
    @Test
    public void sortMoviesShouldSortInDescendingOrder() {
        Platform.runLater(() -> {
            // Given
            controller.observableMovies = FXCollections.observableArrayList(
                    new Movie("A", "desc", new ArrayList<>()),
                    new Movie("B", "desc", new ArrayList<>())
            );

            // When
            controller.sortMovies(controller.observableMovies, false);

            // Then
            assertEquals("B", controller.observableMovies.get(0).getTitle());
            assertEquals("A", controller.observableMovies.get(1).getTitle());
        });
    }
    @Test
    public void updateMovieListViewShouldFilterByGenre() {
        Platform.runLater(() -> {
            // Given
            controller.genreComboBox.getItems().addAll(Movie.Genre.values());
            controller.genreComboBox.getSelectionModel().select(Movie.Genre.ACTION.toString());
            controller.allMovies = Arrays.asList(
                    new Movie("A", "desc", new ArrayList<>(Arrays.asList(Movie.Genre.ACTION))),
                    new Movie("B", "desc", new ArrayList<>(Arrays.asList(Movie.Genre.COMEDY)))
            );
            controller.observableMovies = FXCollections.observableArrayList(controller.allMovies);

            // When
            controller.updateMovieListView();

            // Then
            assertEquals(1, controller.observableMovies.size());
            assertEquals("A", controller.observableMovies.get(0).getTitle());
        });
    }
    @Test
    public void updateMovieListViewShouldFilterBySearchQuery() {
        Platform.runLater(() -> {
            // Given
            controller.searchField.setText("A");
            controller.allMovies = Arrays.asList(
                    new Movie("A", "desc", new ArrayList<>()),
                    new Movie("B", "desc", new ArrayList<>())
            );
            controller.observableMovies = FXCollections.observableArrayList(controller.allMovies);

            // When
            controller.updateMovieListView();

            // Then
            assertEquals(1, controller.observableMovies.size());
            assertEquals("A", controller.observableMovies.get(0).getTitle());
        });
    }

    @Test
    public void addAllGenresOptionShouldAddAllGenresOption() {
        Platform.runLater(() -> {
            // When
            controller.addAllGenresOption();

            // Then
            assertTrue(controller.genreComboBox.getItems().contains("All Genres"));
        });
    }

    @Test
    public void resetFiltersShouldClearSearchFieldAndGenreComboBox() {
        Platform.runLater(() -> {
            // Given
            controller.searchField.setText("A");
            controller.genreComboBox.getItems().add("All Genres");
            controller.genreComboBox.getSelectionModel().select("All Genres");

            // When
            controller.resetFilters();

            // Then
            assertEquals("", controller.searchField.getText());
            assertNull(controller.genreComboBox.getSelectionModel().getSelectedItem());
        });
    }
}