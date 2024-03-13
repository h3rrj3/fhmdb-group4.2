package at.ac.fhcampuswien.fhmdb.ui;

import at.ac.fhcampuswien.fhmdb.models.Movie;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.ListCell;
import javafx.scene.layout.Background;
import javafx.scene.layout.BackgroundFill;
import javafx.scene.layout.VBox;
import javafx.scene.paint.Color;

public class MovieCell extends ListCell<Movie> {
    private final Label title = new Label();
    private final Label detail = new Label();
    private final Label genre = new Label(); // added label genre
    private final VBox layout = new VBox(title, detail, genre); // added the label genre as parameter to be included for every movie's VBox

    @Override
    protected void updateItem(Movie movie, boolean empty) {
        // @Override: steuert das Aussen und Verhalten, wenn sich der Inhalt ändert.
        super.updateItem(movie, empty);

        if (empty || movie == null) {
            setText(null);
            setGraphic(null);
            getStyleClass().remove("movie-cell");
        } else {
            this.getStyleClass().add("movie-cell");
            title.setText(movie.getTitle());
            detail.setText(
                    movie.getDescription() != null // wenn erfüllt
                            ? movie.getDescription() // getDescription
                            : "No description available" // wenn nicht erfüllt
                    /*
                    if (movie.getDescription() != null) {
                    detail.setText(movie.getDescription());
                    } else {
                    detail.setText("No description available");}
                     */
            );
            // added the label genre to display the respective genres
            genre.setText(
                    movie.getGenres() != null
                            ? movie.getGenres().toString().replace("[", "").replace("]", "")    // removes the square brackets of the ArrayList Genres
                            : "No genres available"
            );

            // color scheme
            title.getStyleClass().add("text-yellow");
            detail.getStyleClass().add("text-white");
            genre.getStyleClass().add("text-whiteItalic");  // make the genre text appear white and italic as required
            layout.setBackground(new Background(new BackgroundFill(Color.web("#454545"), null, null))); //VBox Layout hat keine getStyleClass Methode wie Label
            // layout.getStyleClass().add("background-light-black");

            // layout
            title.fontProperty().set(title.getFont().font(20)); // oder in text-yellow in css definieren ( -fx-font-size: 20px; )
            detail.setMaxWidth(this.getScene().getWidth() - 30); // sicherstellung, das Label nie breiter als der zur verfügung stehende Platz.
            detail.setWrapText(true); // Zeilenumbruch
            layout.setPadding(new Insets(10)); // ein neues Objekt als Parameter ?
            layout.spacingProperty().set(10); // abstand Labels
            layout.alignmentProperty().set(Pos.CENTER_LEFT);
            setGraphic(layout);
        }
    }
}

