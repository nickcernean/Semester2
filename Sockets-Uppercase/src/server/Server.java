package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
  public static void main(String[] args)
  {
    try
    {
      final int PORT = 2910;
      ServerSocket welcomeSocket = new ServerSocket(PORT);
      while (true)
      {
        System.out.println("Server started...");
        Socket socket = welcomeSocket.accept();
        ObjectInputStream inFromClient = new ObjectInputStream(
            socket.getInputStream());
        ObjectOutputStream outToClient = new ObjectOutputStream(
            socket.getOutputStream());
        String msg = (String) inFromClient.readObject();
        System.out.println("Received: " + msg);
        msg = msg.toUpperCase().trim();
        System.out.println("Sending msg: " + msg);
        outToClient.writeObject(msg);
        System.out.println("Client disconnected...");
      }
    }

    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }

  }
}
