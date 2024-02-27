package at.ac.fhcampuswien.fhmdb;

import org.junit.jupiter.api.Test;
import at.ac.fhcampuswien.fhmdb.HomeController;
import at.ac.fhcampuswien.fhmdb.models.Movie;
import at.ac.fhcampuswien.fhmdb.ui.MovieCell;

import java.net.URL;
import java.util.ResourceBundle;

import static org.junit.jupiter.api.Assertions.*;

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

class HomeControllerTest {

    @Test
    // This tests if the Reset Filter button clears the query search text field
    void resetFilters_clears_text() {
        // given
        HomeController newHomecontroller = new HomeController();
        String searchQuery = "Test";
        String expected = "Search FHMDb";
        newHomecontroller.searchField.setText(searchQuery);
        newHomecontroller.updateMovieListView();

        // when
        newHomecontroller.setResetFilters();

        // then
        assertEquals(newHomecontroller.searchField.getText(), expected);

    /*@Test
    // This tests if the Reset Filter button clears any genre filter selection
    void resetFilters_clears_genre() {
        // given

        // when

        // then

    */

    }

}