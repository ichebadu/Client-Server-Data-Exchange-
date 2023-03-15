package org.example;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server {
    private ServerSocket serverSocket;

    public Server(ServerSocket serverSocket){
        this.serverSocket = serverSocket;
    }

    public void startServer(){
        try{
            while(!serverSocket.isClosed()){
                System.out.println("waiting for Client to connect");
                Socket socket = serverSocket.accept();
                System.out.println("New Client Connected");

                ClientHandler clientHandler = new ClientHandler(socket);

                Thread thread = new Thread(clientHandler);
                thread.start();
            }
        }catch(IOException e){
            System.out.println("IOException: " + e);
        }
    }
    public void closedServerSocket() {
        try {
            if (serverSocket != null) {
                serverSocket.close();
            }
            } catch(IOException e){
                System.out.println(e);
            }
        }
    public  static void main(String[] args) throws IOException {
        ServerSocket serverSocket = new ServerSocket(5002);
        Server server = new Server(serverSocket);
        server.startServer();
    }
}
