package chappi.main;

import java.io.IOException;

import chappi.ui.Chappi;
import chappi.ui.MainWindow;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;

/**
 * A GUI for Duke using FXML.
 */
public class Main extends Application {

    private Chappi chappi = new Chappi("./data/chappiSave.txt");

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            scene.getStylesheets().add(getClass().getResource("/css/user-dialog-box.css").toExternalForm());
            scene.getStylesheets().add(getClass().getResource("/css/chappi-dialog-box.css").toExternalForm());

            stage.setScene(scene);
            stage.setMinHeight(220);
            stage.setMinWidth(417);
            fxmlLoader.<MainWindow>getController().setChappi(chappi); // inject the Chappi instance

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
