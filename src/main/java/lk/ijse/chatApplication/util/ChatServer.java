package lk.ijse.chatApplication.util;

import java.io.*;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class ChatServer {
    private static Map<String, Socket> connectedClients = new HashMap<>();
    private static List<Socket> socketList = new ArrayList<>();


    public static void main(String[] args) {
        Socket clientSocket;
        try {
            ServerSocket serverSocket = new ServerSocket(3031);
            System.out.println("Server started. Waiting for client connections...");

            while (true) {
                clientSocket = serverSocket.accept();
                System.out.println("Client connected: " + clientSocket);

                Socket finalClientSocket = clientSocket;
                Thread clientThread = new Thread(() -> {
                    try {

                        DataInputStream  inputStream = new DataInputStream(finalClientSocket.getInputStream());

                        int port = finalClientSocket.getPort();
                        // Store the client's socket in the connectedClients map
                        socketList.add(finalClientSocket);
                        connectedClients.put(port+"my port", finalClientSocket);

                        String incomingMessage = "";
                        System.out.println(incomingMessage);
                        while (!(incomingMessage = inputStream.readUTF()).equals("finish")) {
                            System.out.println("Received from client " + port + ": " + incomingMessage);

                            for(Socket socket:socketList){
                                DataOutputStream outputStream = new DataOutputStream(socket.getOutputStream());
                                outputStream.writeUTF(incomingMessage);
                                outputStream.flush();
                            }

                        }

                        // Remove the client from the connectedClients map
                        socketList.remove(finalClientSocket);
                        connectedClients.remove(port);

                        // Close the connection
                        finalClientSocket.close();
                        System.out.println("Client disconnected: " + port);
                    } catch (IOException e) {
                        System.out.println("disconnected !");
                        System.exit(0);
                    }
                });

                // Start the client thread
                clientThread.start();
            }
        } catch (IOException e) {
            e.printStackTrace();
        }
    }
}
