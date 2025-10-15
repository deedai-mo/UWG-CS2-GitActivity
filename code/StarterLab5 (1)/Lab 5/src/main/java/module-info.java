module edu.westga.cs1302.lab5 {
    requires transitive javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;
	requires javafx.base;

    opens edu.westga.cs1302.lab5.views to javafx.fxml;
    exports edu.westga.cs1302.lab5;
}
