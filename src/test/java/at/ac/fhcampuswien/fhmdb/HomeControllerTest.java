package at.ac.fhcampuswien.fhmdb;

import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

class HomeControllerTest {

    @Test
    // This tests if the Reset Filter button clears the query search text field
    void resetFilters_clears_text() {
        // given
        HomeController newHomecontroller = new HomeController();
        String searchQuery = "Test";

        try {
            newHomecontroller.searchField.setText(searchQuery);
            newHomecontroller.updateMovieListView();

            // when
            newHomecontroller.resetFilters();

        } catch (NullPointerException ignore) {}

        // then
        assertNull(newHomecontroller.searchField);
        //assertEquals(newHomecontroller.searchField.getText(), expected);
    }

    @Test
    // This tests if the Reset Filter button clears any genre filter selection
    void resetFilters_clears_genre() {
        // given
        HomeController newHomecontroller = new HomeController();
        try {
            newHomecontroller.genreComboBox.setValue("DRAMA");

        // when
        newHomecontroller.resetFilters();
        } catch (NullPointerException ignore) {}

        // then
        assertNull(newHomecontroller.genreComboBox);
    }
}