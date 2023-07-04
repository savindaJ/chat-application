package lk.ijse.chatApplication.controller;

import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.TextArea;

public class ServerFormController {
    @FXML
    private TextArea areaDetail;

    @FXML
    private Button btnStop;

    @FXML
    void initialize(){
        new Thread(()->{

        });
    }

    public void btnStopOnAction(ActionEvent actionEvent) {

    }
}
