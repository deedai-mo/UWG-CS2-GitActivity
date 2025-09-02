module edu.westga.cs1302.hilogame {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.graphics;

    opens edu.westga.cs1302.hilogame.views to javafx.fxml;
    exports edu.westga.cs1302.hilogame;
}
