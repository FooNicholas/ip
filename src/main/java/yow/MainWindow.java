package yow;

import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

/**
 * Controller for the main GUI.
 */
public class MainWindow extends AnchorPane {
    @FXML
    private ScrollPane scrollPane;
    @FXML
    private VBox dialogContainer;
    @FXML
    private TextField userInput;
    @FXML
    private Button sendButton;

    private Yow yow;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/pepe.png"));
    private Image botImage = new Image(this.getClass().getResourceAsStream("/images/yowbot.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /** Injects the Yow instance */
    public void setYow(Yow y) {
        yow = y;
    }

    public void displayWelcomeMessage() {
        dialogContainer.getChildren().add(DialogBox.getBotDialog("Hello! I'm Yow\nWhat can I do for you yow?", botImage));
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Yow's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        yow.parser.parseCommand(input);
        String response = yow.ui.getResponse();

        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getBotDialog(response, botImage)
        );
        userInput.clear();

        if (input.trim().equalsIgnoreCase("bye")) {
            endChat();
        }
    }

    private void endChat() {
        dialogContainer.getChildren().add(DialogBox.getBotDialog("Bye. Hope to see you again soon yow!", botImage));
        Stage stage = (Stage) dialogContainer.getScene().getWindow();
        stage.close();
    }
}