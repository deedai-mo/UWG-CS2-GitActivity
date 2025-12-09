module edu.westga.cs1302.helloworld {
    requires javafx.controls;
    requires javafx.fxml;
    requires transitive javafx.graphics;

    opens edu.westga.cs1302.helloworld.view to javafx.fxml;
    exports edu.westga.cs1302.helloworld;
}
