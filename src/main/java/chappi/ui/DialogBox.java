package chappi.ui;

import java.io.IOException;
import java.util.Collections;

import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Label;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.layout.HBox;

/**
 * Represents a dialog box consisting of an ImageView to represent the speaker's face
 * and a label containing text from the speaker.
 */
public class DialogBox extends HBox {
    @FXML
    private Label dialog;
    @FXML
    private ImageView displayPicture;

    private DialogBox(String text, Image img) {
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(MainWindow.class.getResource("/view/DialogBox.fxml"));
            fxmlLoader.setController(this);
            fxmlLoader.setRoot(this);
            fxmlLoader.load();
        } catch (IOException e) {
            e.printStackTrace();
        }

        dialog.setText(text);
        displayPicture.setImage(img);
    }

    /**
     * Flips the dialog box such that the ImageView is on the left and text on the right.
     */
    private void flip() {
        ObservableList<Node> tmp = FXCollections.observableArrayList(this.getChildren());
        Collections.reverse(tmp);
        getChildren().setAll(tmp);
        setAlignment(Pos.TOP_LEFT);
    }

    /**
     * Contains user input text in a dialog box next to an image representing user.
     * @param text String representation of user's input.
     * @param img 150x150 image to indicate this is the user's input.
     * @return DialogBox containing the user's input and image.
     */
    public static DialogBox getUserDialog(String text, Image img) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("user-dialog-box");
        return db;
    }

    /**
     * Contains Chappi's response in a dialog box next to an image representing Chappi.
     * Chappi's dialog box is flipped to differentiate it from user input.
     * @param text String representation of Chappi's response.
     * @param img 150x150 image to indicate this is Chappi's response.
     * @return DialogBox containing Chappi's response and image.
     */
    public static DialogBox getChappiDialog(String text, Image img, String commandType) {
        DialogBox db = new DialogBox(text, img);
        db.dialog.getStyleClass().add("chappi-dialog-box");
        db.flip();
        if (!commandType.isBlank()) {
            db.changeDialogStyle(commandType);
        }
        return db;
    }

    private void changeDialogStyle(String commandType) {
        switch(commandType) {
        case "AddDeadlineTaskCommand":
        case "AddEventTaskCommand":
        case "AddTodoTaskCommand":
            dialog.getStyleClass().add("add-label");
            break;
        case "MarkTaskCommand":
        case "UnmarkTaskCommand":
            dialog.getStyleClass().add("marked-label");
            break;
        case "DeleteTaskCommand":
            dialog.getStyleClass().add("delete-label");
            break;
        default:
            // Do nothing
        }
    }
}
