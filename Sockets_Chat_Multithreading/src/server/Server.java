package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
  public static void main(String[] args)
  {
    Server server = new Server();
    server.startServer();
  }

  private void startServer()
  {
    try

    {
      ServerSocket welcomeSocket = new ServerSocket(2910);
      System.out.println("Server started");
      int id = 0;
      while (true)
      {
        Socket socket = welcomeSocket.accept();
        System.out.println("Client connected");
        ServerSocketHandler ssh = new ServerSocketHandler(socket, id, this);
        (new Thread(ssh)).start();
        id++;
      }
    }
    catch (IOException e)

    {
      e.printStackTrace();
    }
  }

  public String messageReceived(String message,int id)
  {
    System.out.println("Received from client:" + message);
    String idos= String.valueOf(id);
    String response="And hello to you, client number "+idos;
    return response;
  }
}

