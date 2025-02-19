package yow;

import java.io.IOException;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.image.Image;
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
            stage.setTitle("YowBot");
            stage.getIcons().add(new Image(this.getClass().getResourceAsStream("/images/yowbot.png")));

            MainWindow controller = fxmlLoader.getController();
            controller.setYow(yow);
            controller.displayWelcomeMessage();

            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}