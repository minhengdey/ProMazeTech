module ProMazeTech {
    requires javafx.controls;
    requires javafx.fxml;
    requires javafx.web;

    requires org.controlsfx.controls;
    requires com.dlsc.formsfx;
    requires net.synedra.validatorfx;
    requires org.kordamp.ikonli.javafx;
    requires org.kordamp.bootstrapfx.core;
    requires eu.hansolo.tilesfx;
    requires com.almasb.fxgl.all;

    opens ProMazeTech.Controller to javafx.fxml;
    opens ProMazeTech.Model to javafx.fxml;
    opens ProMazeTech.View to javafx.fxml;
    opens ProMazeTech to javafx.fxml;

    exports ProMazeTech.Controller;
    exports ProMazeTech.Model;
    exports ProMazeTech.View;
    exports ProMazeTech;
    exports ProMazeTech.interListener;
    opens ProMazeTech.interListener to javafx.fxml;
}