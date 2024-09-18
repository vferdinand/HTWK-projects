package server;

import java.io.*;
import java.net.Socket;

public class ClientHandler implements Runnable {
    private ChatServer chatServer;
    private Socket connectionToClient;

    private String address;
    private String name;
    private BufferedReader fromClientReader;
    private PrintWriter toClientWriter;

    public ClientHandler(ChatServer chatServer, Socket connectionToClient) {
        this.chatServer = chatServer;
        this.connectionToClient = connectionToClient;

        address = connectionToClient.getInetAddress().getHostAddress();
        name = "Client";
        
        new Thread(this).start();
    }

    @Override
    public void run() {
        try {
           fromClientReader = new BufferedReader(new InputStreamReader(connectionToClient.getInputStream()));
           toClientWriter = new PrintWriter(new OutputStreamWriter(connectionToClient.getOutputStream()));
           
           name = fromClientReader.readLine();
        	chatServer.broadcastMessage(address + name + "connected.");
        	
        } catch (IOException e) {
            e.printStackTrace();
        } finally {
            chatServer.removeClient(this);
            chatServer.broadcastMessage(address + " disconnected.");

            if (fromClientReader != null) {
                try {
                    fromClientReader.close();
                } catch (IOException e) {
                    e.printStackTrace();
                }
            }
            if (toClientWriter != null) {
                toClientWriter.close();
            }
        }
    }

	public void sendMessage(String message) {
		toClientWriter.println(message);
		toClientWriter.flush();
	}
}
