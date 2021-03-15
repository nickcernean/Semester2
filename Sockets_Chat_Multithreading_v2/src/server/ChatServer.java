package server;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class ChatServer
{
  public void start() throws IOException
  {
    ServerSocket serverSocket = new ServerSocket(2910);
    ConnectionPool pool = new ConnectionPool();
    System.out.println("Server started...");
    while (true)
    {
      Socket socket = serverSocket.accept();

      ServerSocketHandler ssh = new ServerSocketHandler(socket, pool);
      pool.addConenction(ssh);

      Thread t1 = new Thread(ssh);
      t1.start();

    }

  }
}