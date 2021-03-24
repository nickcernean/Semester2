package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer
{
  private ServerSocket welcomeSocket;

  public ChatServer(int port) throws IOException
  {
    this.welcomeSocket = new ServerSocket(port);
  }

  public void execute() throws IOException
  {
    System.out.println("Starting server...");
    System.out.println("Waiting for clients...");
    while (true)
    {
      Socket socket = welcomeSocket.accept();
      System.out.println("Client connected...");
      CommunicationThreadHandler communicationThreadHandler = new CommunicationThreadHandler(
          socket);
      Thread thread = new Thread(communicationThreadHandler);
      thread.setDaemon(true);
      thread.start();
    }
  }
}
