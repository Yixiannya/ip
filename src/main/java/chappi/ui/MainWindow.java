package chappi.ui;

import javafx.application.Platform;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.ScrollPane;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.layout.AnchorPane;
import javafx.scene.layout.VBox;
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

    private Chappi chappi;

    private Image userImage = new Image(this.getClass().getResourceAsStream("/images/UserImage.png"));
    private Image chappiImage = new Image(this.getClass().getResourceAsStream("/images/ChappiImage.png"));

    @FXML
    public void initialize() {
        scrollPane.vvalueProperty().bind(dialogContainer.heightProperty());
    }

    /**
     * Injects the Chappi instance
     * and displays the greeting message on initialisation of Chappi.
     */
    public void setChappi(Chappi c) {
        chappi = c;
        showGreeting();
    }

    /**
     * Displays Chappi's greeting in the proper dialog container.
     */
    private void showGreeting() {
        dialogContainer.getChildren().addAll(
                DialogBox.getChappiDialog(chappi.showGreeting(), chappiImage)
        );
    }

    /**
     * Creates two dialog boxes, one echoing user input and the other containing Chappi's reply and then appends them to
     * the dialog container. Clears the user input after processing.
     */
    @FXML
    private void handleUserInput() {
        String input = userInput.getText();
        String response = chappi.getResponse(input);

        if (response.equals("bye")) {
            Platform.exit();
            return;
        }
        dialogContainer.getChildren().addAll(
                DialogBox.getUserDialog(input, userImage),
                DialogBox.getChappiDialog(response, chappiImage)
        );
        userInput.clear();
    }
}
