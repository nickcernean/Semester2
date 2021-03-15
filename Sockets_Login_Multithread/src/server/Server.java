package server;

import java.io.IOException;
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
        System.out.println("Client connected...");
        ServerSocketHandler ssh = new ServerSocketHandler(socket);
        (new Thread(ssh)).start();
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();

    }

  }
}
