package at.ac.fhcampuswien.fhmdb;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import com.jfoenix.controls.JFXButton;
import com.jfoenix.controls.JFXListView;
import com.jfoenix.controls.JFXComboBox;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
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
import java.util.List;

import static org.junit.Assert.assertFalse;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertNull;
import static org.junit.jupiter.api.Assertions.assertTrue;

@ExtendWith(ApplicationExtension.class)
public class HomeControllerTest {
    private HomeController controller;

    @Start
     void start(Stage stage) {
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
     void setUp() {
        Platform.runLater(() -> {
            controller.allMovies = Arrays.asList(
                    new Movie("A", "desc", new ArrayList<>()),
                    new Movie("B", "desc", new ArrayList<>())
            );
            controller.observableMovies = FXCollections.observableArrayList(controller.allMovies);
        });
    }

    @Test
     void sortMoviesShouldSortInAscendingOrder() {
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
     void sortMoviesShouldSortInDescendingOrder() {
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
     void updateMovieListViewShouldFilterBySearchQuery() {
        Platform.runLater(() -> {
            // Given
            controller.searchField.setText("A");

            // When
            controller.updateMovieListView();

            // Then
            assertEquals(1, controller.observableMovies.size());
            assertEquals("A", controller.observableMovies.get(0).getTitle());
        });
    }

    @Test
     void addAllGenresOptionShouldAddAllGenresOption() {
        Platform.runLater(() -> {
            // When
            controller.addAllGenresOption();

            // Then
            assertTrue(controller.genreComboBox.getItems().contains("All Genres"));
        });
    }

    @Test
     void resetFiltersShouldClearSearchFieldAndGenreComboBox() {
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
    @Test
    void addAllGenresOptionShouldNotAddDuplicateAllGenresOption() {
        Platform.runLater(() -> {
            // Given
            controller.genreComboBox.getItems().add("All Genres");

            // When
            controller.addAllGenresOption();

            // Then
            assertEquals(1, controller.genreComboBox.getItems().stream().filter(item -> item.equals("All Genres")).count());
        });
    }

    @Test
    void searchFieldShouldCaptureAndSaveTextEnteredByUser() {
        Platform.runLater(() -> {
            // Given
            String searchText = "Avatar";

            // When
            controller.searchField.setText(searchText);

            // Then
            assertEquals(searchText, controller.searchField.getText());
        });
    }

    @Test
    void resetFiltersShouldShowAllAvailableMovies() {
        Platform.runLater(() -> {
            // Given
            // Set up the scenario where filters are applied
            controller.searchField.setText("A");
            controller.genreComboBox.getItems().add("All Genres");
            controller.genreComboBox.getSelectionModel().select("All Genres");

            controller.updateMovieListView();

            // When
            // Reset all filters
            controller.resetFilters();

            // Then
            // Assert that all movies are displayed
            assertEquals(controller.allMovies.size(), controller.observableMovies.size());
            assertTrue(controller.observableMovies.containsAll(controller.allMovies));
        });

    }

    @Test
    void removingGenresFromComboBoxShouldWorkCorrectly() {
        // Given
        Platform.runLater(() -> {
            // Given
            controller.addAllGenresOption();

            // When
            controller.genreComboBox.getItems().remove("All Genres"); // Entferne "All Genres"

            // Then
            assertFalse(controller.genreComboBox.getItems().contains("All Genres")); // Überprüfen, ob "All Genres" entfernt wurde
        });
    }
}