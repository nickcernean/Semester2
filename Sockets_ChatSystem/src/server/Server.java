package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class Server
{
  public static void main(String[] args)
  {
    while (true)
    {
      try
      {
        ServerSocket welcomeSocket = new ServerSocket(2910);
        System.out.println("Server started>>");
        Socket socket = welcomeSocket.accept();
        System.out.println("Client connected");
        ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
        ObjectOutputStream out = new ObjectOutputStream(
            socket.getOutputStream());
        Scanner input = new Scanner(System.in);
        while (true)
        {
          System.out.println(in.readObject());
          System.out.println("Write your response: ");
          String msg = input.nextLine();
          out.writeObject(msg);
        }
      }
      catch (IOException | ClassNotFoundException e)
      {
        e.printStackTrace();
      }
    }
  }
}
