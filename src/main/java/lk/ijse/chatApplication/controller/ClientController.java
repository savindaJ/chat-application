package lk.ijse.chatApplication.controller;

import javafx.animation.ScaleTransition;
import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.effect.DropShadow;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.*;
import javafx.scene.paint.Color;
import javafx.scene.text.Text;
import javafx.stage.FileChooser;
import javafx.stage.Stage;
import javafx.util.Duration;

import java.io.*;
import java.net.Socket;
import java.nio.charset.Charset;
import java.util.ArrayList;
import java.util.List;

public class ClientController {
    public Label lblClientName;
    public TextField txtMessage;
    public ImageView btnSend;
    public VBox msgVboxAp;
    public Label lblimg1;
    public Label lblimg2;
    public Label lblimg3;
    public Label lblimg4;
    public Label lblimg5;
    public Label lblimg6;
    private List<String> fileList;

    private DataInputStream inputStream;
    private DataOutputStream outputStream;

    private String clientName;
    private PrintWriter writer;

    @FXML
    void initialize(){
        setImoji();
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
                writer = new PrintWriter(socket.getOutputStream(), false);

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

                    HBox hBox = new HBox(12);

                    String mymsg = message;
                    String[] chec = msg.split(" ");
                    String names = words[0];

                    byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x8C};
                    byte[] emojiByteCode1 = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x8D};
                    byte[] emojiByteCode2 = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x8F};
                    byte[] emojiByteCode3 = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x93};
                    byte[] emojiByteCode4 = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x98};
                    byte[] emojiByteCode5 = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x82};

                    String img1 = new String(emojiByteCode, Charset.forName("UTF-8"));
                    String img2 = new String(emojiByteCode1, Charset.forName("UTF-8"));
                    String img3 = new String(emojiByteCode2, Charset.forName("UTF-8"));
                    String img4 = new String(emojiByteCode3, Charset.forName("UTF-8"));
                    String img5 = new String(emojiByteCode4, Charset.forName("UTF-8"));
                    String img6 = new String(emojiByteCode5, Charset.forName("UTF-8"));

                    if (clientName.equalsIgnoreCase(lblClientName.getText())){

                        if (chec[1].equals("imoji")){
                            if (chec[2].equals("imoji01")){
                                String myMsg = "Me "+img1;
                                Label label = new Label();
                                label.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
                                label.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                                label.setStyle("-fx-font-size: 20");
                                label.setText(myMsg);
                                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                                hBox.getChildren().add(label);
                                Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                            }else if(chec[2].equals("imoji02")){
                                String myMsg = "Me "+img2;
                                Label label = new Label();
                                label.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
                                label.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                                label.setStyle("-fx-font-size: 20");
                                label.setText(myMsg);
                                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                                hBox.getChildren().add(label);
                                Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                            }else if(chec[2].equals("imoji03")){
                                String myMsg = "Me "+img3;
                                Label label = new Label();
                                label.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
                                label.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                                label.setStyle("-fx-font-size: 20");
                                label.setText(myMsg);
                                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                                hBox.getChildren().add(label);
                                Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                            }else if(chec[2].equals("imoji04")){
                                String myMsg = "Me "+img4;
                                Label label = new Label();
                                label.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
                                label.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                                label.setStyle("-fx-font-size: 20");
                                label.setText(myMsg);
                                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                                hBox.getChildren().add(label);
                                Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                            }else if(chec[2].equals("imoji05")){
                                String myMsg = "Me "+img5;
                                Label label = new Label();
                                label.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
                                label.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                                label.setStyle("-fx-font-size: 20");
                                label.setText(myMsg);
                                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                                hBox.getChildren().add(label);
                                Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                            }else {
                                String myMsg = "Me "+img6;
                                Label label = new Label();
                                label.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
                                label.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                                label.setStyle("-fx-font-size: 20");
                                label.setText(myMsg);
                                hBox.setAlignment(Pos.BOTTOM_RIGHT);
                                hBox.getChildren().add(label);
                                Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                            }
                        }else {
                            String myMsg = "Me "+nameWithoutMsg;
                            Label label = new Label();
                            label.setBackground(new Background(new BackgroundFill(Color.SILVER, CornerRadii.EMPTY, Insets.EMPTY)));
                            label.setBorder(new Border(new BorderStroke(Color.CORNFLOWERBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                            label.setStyle("-fx-font-size: 15");
                            label.setText(myMsg);
                            hBox.setAlignment(Pos.BOTTOM_RIGHT);
                            hBox.getChildren().add(label);
                            Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                        }



                    }else if(! chec[1].equals("imoji")){

                        String riciveMsg = message;

                        Label label = new Label();
                        label.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                        label.setBorder(new Border(new BorderStroke(Color.ALICEBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                        label.setStyle("-fx-font-size: 15");
                        label.setText(riciveMsg);
                        hBox.setAlignment(Pos.BOTTOM_LEFT);
                        hBox.getChildren().add(label);
                        Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                    }else {
                        if (chec[2].equals("imoji01")){
                            String imojiRecuve = chec[0]+":"+img1;
                            Label label = new Label();
                            label.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                            label.setBorder(new Border(new BorderStroke(Color.ALICEBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                            label.setStyle("-fx-font-size: 20");
                            label.setText(imojiRecuve);
                            hBox.setAlignment(Pos.BOTTOM_LEFT);
                            hBox.getChildren().add(label);
                            Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                        }else if(chec[2].equals("imoji02")){
                            String imojiRecuve = chec[0]+":"+img2;
                            Label label = new Label();
                            label.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                            label.setBorder(new Border(new BorderStroke(Color.ALICEBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                            label.setStyle("-fx-font-size: 20");
                            label.setText(imojiRecuve);
                            hBox.setAlignment(Pos.BOTTOM_LEFT);
                            hBox.getChildren().add(label);
                            Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                        }else if(chec[2].equals("imoji03")){
                            String imojiRecuve = chec[0]+":"+img3;
                            Label label = new Label();
                            label.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                            label.setBorder(new Border(new BorderStroke(Color.ALICEBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                            label.setStyle("-fx-font-size: 20");
                            label.setText(imojiRecuve);
                            hBox.setAlignment(Pos.BOTTOM_LEFT);
                            hBox.getChildren().add(label);
                            Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                        }else if(chec[2].equals("imoji04")){
                            String imojiRecuve = chec[0]+":"+img4;
                            Label label = new Label();
                            label.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                            label.setBorder(new Border(new BorderStroke(Color.ALICEBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                            label.setStyle("-fx-font-size: 20");
                            label.setText(imojiRecuve);
                            hBox.setAlignment(Pos.BOTTOM_LEFT);
                            hBox.getChildren().add(label);
                            Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                        }else if(chec[2].equals("imoji05")){
                            String imojiRecuve = chec[0]+":"+img5;
                            Label label = new Label();
                            label.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                            label.setBorder(new Border(new BorderStroke(Color.ALICEBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                            label.setStyle("-fx-font-size: 20");
                            label.setText(imojiRecuve);
                            hBox.setAlignment(Pos.BOTTOM_LEFT);
                            hBox.getChildren().add(label);
                            Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                        }else {
                            String imojiRecuve = chec[0]+":"+img6;
                            Label label = new Label();
                            label.setBackground(new Background(new BackgroundFill(Color.GREENYELLOW, CornerRadii.EMPTY, Insets.EMPTY)));
                            label.setBorder(new Border(new BorderStroke(Color.ALICEBLUE, BorderStrokeStyle.SOLID, null, new BorderWidths(1))));
                            label.setStyle("-fx-font-size: 20");
                            label.setText(imojiRecuve);
                            hBox.setAlignment(Pos.BOTTOM_LEFT);
                            hBox.getChildren().add(label);
                            Platform.runLater(()->msgVboxAp.getChildren().addAll(hBox));
                        }

                    }
                }

            } catch (IOException e) {
                System.out.println("disconnected !");
            }
        }).start();
    }

    private void setImoji() {
        byte[] emojiByteCode = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x8C};
        byte[] emojiByteCode1 = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x8D};
        byte[] emojiByteCode2 = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x8F};
        byte[] emojiByteCode3 = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x93};
        byte[] emojiByteCode4 = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x98};
        byte[] emojiByteCode5 = new byte[]{(byte)0xF0, (byte)0x9F, (byte)0x98, (byte)0x82};
        //https://apps.timwhitlock.info/emoji/tables/unicode#emoji-modal

//        String emoji = new String(emojiByteCode, Charset.forName("UTF-8"));

        lblimg1.setText(new String(emojiByteCode, Charset.forName("UTF-8")));
        lblimg2.setText(new String(emojiByteCode1, Charset.forName("UTF-8")));
        lblimg3.setText(new String(emojiByteCode2, Charset.forName("UTF-8")));
        lblimg4.setText(new String(emojiByteCode3, Charset.forName("UTF-8")));
        lblimg5.setText(new String(emojiByteCode4, Charset.forName("UTF-8")));
        lblimg6.setText(new String(emojiByteCode5, Charset.forName("UTF-8")));
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

        if (event.getSource() instanceof javafx.scene.control.Label) {
            javafx.scene.control.Label icon = (Label) event.getSource();

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

        if (event.getSource() instanceof Label) {
            Label icon = (Label) event.getSource();
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

    public void btnfileOnAction(MouseEvent event) throws IOException {
        FileChooser chooser =new FileChooser();
        chooser.getExtensionFilters().add(new FileChooser.ExtensionFilter("choose allowed ",fileList));
        File file = chooser.showOpenDialog(null);

        if (file!=null){
            String path = file.getAbsolutePath();
            writer.println(clientName+ " " + "img" + path);
            writer.flush();
            System.out.println("flushed !");
        }
    }

    public void imoji1OnAction(MouseEvent event) throws IOException {
        String imoji = clientName + " imoji" + " imoji01";
        outputStream.writeUTF(imoji);
        outputStream.flush();
    }

    public void imoji2OnAction(MouseEvent event) throws IOException {
        String imoji = clientName + " imoji" + " imoji02";
        outputStream.writeUTF(imoji);
        outputStream.flush();
    }

    public void imoji3OnAction(MouseEvent event) throws IOException {
        String imoji = clientName + " imoji" + " imoji03";
        outputStream.writeUTF(imoji);
        outputStream.flush();
    }

    public void imoji4OnAction(MouseEvent event) throws IOException {
        String imoji = clientName + " imoji" + " imoji04";
        outputStream.writeUTF(imoji);
        outputStream.flush();
    }

    public void imoji5OnAction(MouseEvent event) throws IOException {
        String imoji = clientName + " imoji" + " imoji05";
        outputStream.writeUTF(imoji);
        outputStream.flush();

    }

    public void imoji6OnAction(MouseEvent event) throws IOException {
        String imoji = clientName + " imoji" + " imoji06";
        outputStream.writeUTF(imoji);
        outputStream.flush();
    }

}
