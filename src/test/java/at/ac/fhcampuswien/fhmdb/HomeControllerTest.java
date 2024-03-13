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
import java.util.concurrent.CountDownLatch;

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
    void sortMoviesShouldSortInAscendingOrder() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                // Given
                controller.observableMovies = FXCollections.observableArrayList(
                        new Movie("A", "desc", new ArrayList<>()),
                        new Movie("B", "desc", new ArrayList<>())
                );

                // When
                controller.sortMovies(controller.observableMovies, true);

                // Then
                assertEquals("A", controller.observableMovies.get(0).getTitle());
                assertEquals("B", controller.observableMovies.get(1).getTitle());
            } finally {
                latch.countDown(); // Decrease latch count to continue test execution
            }
        });

        latch.await(); // Wait for the latch to reach 0
    }
    @Test
     void sortMoviesShouldSortInDescendingOrder() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
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
            } finally {
                latch.countDown(); // Decrease latch count to continue test execution
            }
        });

        latch.await(); // Wait for the latch to reach 0
    }
    @Test
    void updateMovieListViewShouldFilterBySearchQuery() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                // Given
                controller.searchField.setText("A");

                // When
                controller.updateMovieListView();

                // Then
                assertEquals(1, controller.observableMovies.size());
                assertEquals("A", controller.observableMovies.get(0).getTitle());
            } finally {
                latch.countDown(); // Ensure latch count is decreased to continue execution
            }
        });

        latch.await(); // Wait for the latch to reach 0 ensuring UI updates are completed
    }


    @Test
    void addAllGenresOptionShouldAddAllGenresOption() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                // When
                controller.addAllGenresOption();

                // Then
                assertTrue(controller.genreComboBox.getItems().contains("All Genres"));
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }


    @Test
    void resetFiltersShouldClearSearchFieldAndGenreComboBox() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                // Given
                controller.searchField.setText("A");
                controller.genreComboBox.getItems().add("All Genres");
                controller.genreComboBox.getSelectionModel().select("All Genres");

                // When
                controller.resetFilters();

                // Then
                assertEquals("", controller.searchField.getText());
                assertNull(controller.genreComboBox.getSelectionModel().getSelectedItem());
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

    @Test
    void addAllGenresOptionShouldNotAddDuplicateAllGenresOption() throws InterruptedException {
        final CountDownLatch latch = new CountDownLatch(1);

        Platform.runLater(() -> {
            try {
                // Given
                controller.genreComboBox.getItems().add("All Genres");

                // When
                controller.addAllGenresOption();

                // Then
                assertEquals(1, controller.genreComboBox.getItems().stream().filter(item -> item.equals("All Genres")).count());
            } finally {
                latch.countDown();
            }
        });

        latch.await();
    }

}