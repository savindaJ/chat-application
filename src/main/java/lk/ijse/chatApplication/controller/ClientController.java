package lk.ijse.chatApplication.controller;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.scene.text.TextFlow;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.File;
import java.io.IOException;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ClientController {
    public Label lblClientName;
    public TextField txtMessage;
    public ImageView btnSend;
    public VBox msgVboxAp;
    private List<String> fileList;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private String clientName;

    @FXML
    void initialize(){
        lblClientName.setText(HomeFormController.name);
        clientName = lblClientName.getText();
        fileList=new ArrayList<>();
        fileList.add("*.jpg");
        fileList.add("*.doc");
        fileList.add("*.png");
        fileList.add("*.pdf");

        new Thread(()->{
            try (Socket socket = new Socket("localhost",3031);){

                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());

                String message = "";

                while (!message.equals("finish")){

                    message = inputStream.readUTF();

                    String msg = message;
                    String[] words = msg.split(" ");
                    String clientName = words[0];


                    StringBuilder nameWithoutMsg = new StringBuilder();
                    for (int i = 1; i < words.length; i++) {
                        nameWithoutMsg.append(words[i]+" ");
                    }

                    HBox hBox = new HBox(12); //12


                    if (clientName.equalsIgnoreCase(lblClientName.getText())){
                        String myMsg = "Me "+nameWithoutMsg;
                        Label label = new Label();

                        label.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
                        label.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                        label.setStyle("-fx-font-size: 15");
                        label.setText(myMsg);
                        hBox.setAlignment(Pos.BOTTOM_RIGHT);
                        hBox.getChildren().add(label);
                        Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                    }else {

                        String riciveMsg = message;

                        Label label = new Label();
                        label.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                        label.setBorder(new Border(new BorderStroke(Color.ALICEBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                        label.setStyle("-fx-font-size: 15");
                        label.setText(riciveMsg);
                        hBox.setAlignment(Pos.BOTTOM_LEFT);
                        hBox.getChildren().add(label);
                        Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                    }
                }

            } catch (IOException e) {
                HBox hBox = new HBox();
                Label label = new Label();
                label.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                label.setBorder(new Border(new BorderStroke(Color.ALICEBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                label.setStyle("-fx-font-size: 15");
                label.setText("left client");
                hBox.setAlignment(Pos.CENTER);
                hBox.getChildren().add(label);
                Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
            }
        }).start();
    }

    public void mouseEnterAnim(MouseEvent event) {
        if (event.getSource() instanceof javafx.scene.image.ImageView) {
            javafx.scene.image.ImageView icon = (ImageView) event.getSource();

            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1.2);
            scaleT.setToY(1.2);
            scaleT.play();

            DropShadow glow = new DropShadow();
//            glow.setColor(Color.valueOf("#EF233C"));
            glow.setColor(Color.CORNFLOWERBLUE);
            glow.setWidth(15);
            glow.setHeight(15);
            glow.setRadius(15);
            icon.setEffect(glow);
        }
    }

    public void mouseExitAnim(MouseEvent event) {
        if (event.getSource() instanceof ImageView) {
            ImageView icon = (ImageView) event.getSource();
            ScaleTransition scaleT = new ScaleTransition(Duration.millis(200), icon);
            scaleT.setToX(1);
            scaleT.setToY(1);
            scaleT.play();
            icon.setEffect(null);
        }
    }

    public void mouseClickOnAction() {
        if (txtMessage.getText().equals("finish")){
            try {
                outputStream.writeUTF(txtMessage.getText());
                outputStream.flush();
                Stage stage = (Stage) btnSend.getScene().getWindow();
                stage.close();
                return;
            } catch (IOException e) {
                e.printStackTrace();
            }
        }
        try {
            outputStream.writeUTF(clientName+" :"+txtMessage.getText());
            outputStream.flush();
        } catch (IOException e) {
            e.printStackTrace();
        }
        txtMessage.clear();
    }

    public void txtMessageOnAction(ActionEvent event) {
        mouseClickOnAction();
    }

    public void btnimojiOnAction(MouseEvent event) {
    }

    public void btnfileOnAction(MouseEvent event) {
        FileChooser chooser =new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("choose allowed ",fileList));
        File file = chooser.showOpenDialog(null);

        if (file!=null){
            String path = file.getAbsolutePath();
        }
    }
}
