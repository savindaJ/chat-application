package lk.ijse.chatApplication.controller;

import javafx.application.Platform;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;

import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

public class ServerFormController {
    private static final List<Socket> socketList = new ArrayList<>();
    static DataOutputStream outputStream = null;
    @FXML
    private Label lblNumClients;
    @FXML
    private TextArea areaDetail;

    @FXML
    void initialize(){
       new Thread(()->{
           Socket clientSocket;
           try (ServerSocket serverSocket = new ServerSocket(3031)){

//               System.out.println("Server started. Waiting for client connections...");
               areaDetail.setStyle("-fx-text-fill: red");
                areaDetail.appendText("Server started. Waiting for client connections...");
               while (true) {
                   clientSocket = serverSocket.accept();
//                   System.out.println("Client connected: " + clientSocket);
                   areaDetail.setStyle("-fx-text-fill: green");
                   areaDetail.appendText("\n"+"Client connected: " + clientSocket);
                   Socket finalClientSocket = clientSocket;
                   Thread clientThread = new Thread(() -> {
                       try {
                           DataInputStream  inputStream = new DataInputStream(finalClientSocket.getInputStream());
                           int port = finalClientSocket.getPort();
                           socketList.add(finalClientSocket);
                            setNumberOfClients();
                           String incomingMessage = "";
//                           System.out.println(incomingMessage);
                           while (!(incomingMessage = inputStream.readUTF()).equals("finish")) {
//                            System.out.println("Received from client " + port + ": " + incomingMessage);
//                               areaDetail.appendText("\n"+"Received from client " + port + ": " + incomingMessage);

                               for(Socket socket:socketList){
                                   sentMessage(socket,incomingMessage);
                               }
                           }
                           socketList.remove(finalClientSocket);
                           finalClientSocket.close();
//                           System.out.println("Client disconnected: " + port);
                           areaDetail.setStyle("-fx-text-fill: green");
                           areaDetail.appendText("\n"+"Client disconnected: " + port);
                           setNumberOfClients();
                       } catch (IOException e) {
                           areaDetail.setStyle("-fx-text-fill: green");
                           areaDetail.appendText("\n"+"Client disconnected ! ");
//                           System.out.println("disconnected !");
                           System.exit(0);
                       }
                   });

                   clientThread.start();
               }
           } catch (IOException e) {
               e.printStackTrace();
           }
       }).start();

    }

    private void setNumberOfClients() {
        Platform.runLater(() -> lblNumClients.setText(String.valueOf(socketList.size())));
    }

    private static void sentMessage(Socket socket, String incomingMessage) throws IOException {
        try {
            outputStream = new DataOutputStream(socket.getOutputStream());
        } catch (IOException e) {
            e.printStackTrace();
        }
        outputStream.writeUTF(incomingMessage);
        outputStream.flush();
    }

    public void btnStopOnAction(ActionEvent actionEvent) {
        System.exit(0);
    }

}
