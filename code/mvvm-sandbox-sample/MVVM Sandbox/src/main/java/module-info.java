module edu.westga.cs1302.mvvmsandbox {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens edu.westga.cs1302.mvvmsandbox.view to javafx.fxml;
    exports edu.westga.cs1302.mvvmsandbox;
}
