package lk.ijse.chatApplication.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.input.MouseEvent;

public class ClientController {
    public Label lblClientName;
    public TextField txtMessage;

    @FXML
    void initialize(){
        lblClientName.setText(HomeFormController.name);
    }

    public void mouseEnterAnim(MouseEvent event) {
    }

    public void mouseExitAnim(MouseEvent event) {
    }

    public void mouseClickOnAction(MouseEvent event) {
    }

    public void txtMessageOnAction(ActionEvent event) {
    }

    public void btnimojiOnAction(MouseEvent event) {
    }

    public void btnfileOnAction(MouseEvent event) {
    }
}
