package lk.ijse.chatApplication.controller;

import javafx.animation.ScaleTransition;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.paint.Color;
import javafx.stage.FileChooser;
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
    private List<String> fileList;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    @FXML
    void initialize(){
        lblClientName.setText(HomeFormController.name);
        fileList=new ArrayList<>();
        fileList.add("*.jpg");
        fileList.add("*.doc");
        fileList.add("*.png");
        fileList.add("*.pdf");
        new Thread(()->{
            try {
                Socket socket = new Socket("localhost",3031);

                inputStream = new DataInputStream(socket.getInputStream());
                outputStream = new DataOutputStream(socket.getOutputStream());

                String message= "";
                while (!message.equals("finish")){
                    message = inputStream.readUTF();
                    System.out.println("server sent - "+message);

                }

            } catch (IOException e) {

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

    public void mouseClickOnAction(MouseEvent event) {
        try {
            outputStream.writeUTF(txtMessage.getText().trim());
            outputStream.flush();
            System.out.println("sent !");
        } catch (IOException e) {
            e.printStackTrace();
        }
    }

    public void txtMessageOnAction(ActionEvent event) {
    }

    public void btnimojiOnAction(MouseEvent event) {
    }

    public void btnfileOnAction(MouseEvent event) {
        FileChooser chooser =new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("choose allowed ",fileList));
        File file = chooser.showOpenDialog(null);

        if (file!=null){
            System.out.println(file.getAbsolutePath());
        }
    }
}
