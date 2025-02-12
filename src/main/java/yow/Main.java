package yow;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.AnchorPane;
import javafx.stage.Stage;
import yow.Yow;

/**
 * A GUI for Yow using FXML.
 */
public class Main extends Application {

    private Yow yow = new Yow();

    public Main() throws IOException, YowException {
    }

    @Override
    public void start(Stage stage) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(Main.class.getResource("/view/MainWindow.fxml"));
            AnchorPane ap = fxmlLoader.load();
            Scene scene = new Scene(ap);
            stage.setScene(scene);
            fxmlLoader.<MainWindow>getController().setYow(yow);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}