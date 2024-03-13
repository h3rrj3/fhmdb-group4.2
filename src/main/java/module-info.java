module at.ac.fhcampuswien.fhmdb {
    requires javafx.controls;
    requires javafx.fxml;

    requires com.jfoenix;

    // Opens to javafx.fxml for FXML loading
    opens at.ac.fhcampuswien.fhmdb;

    // This opens your package to all unnamed modules, which includes JUnit when it's not running as a module
    // This is necessary for JUnit to use reflection to call test methods and setup/teardown methods

    // Exports your package to be accessible by other modules, necessary for modular applications
    exports at.ac.fhcampuswien.fhmdb;
}
