package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
  public static void main(String[] args)
      throws IOException, ClassNotFoundException
  {
    ServerSocket welcomeSocket = new ServerSocket(2910);
    System.out.println("Server started>>");
    while (true)
    {
      Socket socket = welcomeSocket.accept();
      System.out.println("Client connected");
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      while (true)
      {
        out.writeObject("How can I help?");
        String request = (String) in.readObject();
        if ("UPPERCASE".equals(request))
        {
          out.writeObject("Argument?");
          String arg = (String) in.readObject();
          System.out.println("Received: " + arg);
          out.writeObject(arg.toUpperCase());
        }
        else if ("lowercase".equals(request))
        {
          out.writeObject("Argument?");
          String arg = (String) in.readObject();
          System.out.println("Received: " + arg);
          out.writeObject(arg.toLowerCase());
        }
        else if ("stop".equals(request))
        {
          break;
        }
        else
        {
          break;
        }
      }
    }
  }
}
