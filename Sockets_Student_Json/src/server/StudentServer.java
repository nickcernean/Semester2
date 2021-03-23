package server;

import javax.swing.text.AsyncBoxView;
import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StudentServer
{
  public static void main(String[] args) throws IOException
  {
    final int PORT = 6789;
    System.out.println("Starting server....");
    ServerSocket welcomeSocket = new ServerSocket(PORT);
    while (true)
    {
      System.out.println("Waiting for a client...");
      Socket socket = welcomeSocket.accept();
      Thread clientThread=new Thread(new ClientHandler(socket));
      clientThread.start();

    }
  }
}
