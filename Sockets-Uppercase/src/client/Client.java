package client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class Client
{
  public static void main(String[] args)
  {
    try
    {
      Socket socket = new Socket("localhost", 2910);
      ObjectOutputStream outToServer = new ObjectOutputStream(
            socket.getOutputStream());
        ObjectInputStream inFromServer = new ObjectInputStream(
            socket.getInputStream());
        System.out.println("Write message to server...");
        Scanner in = new Scanner(System.in);
        String msg = in.next();

        outToServer.writeObject(msg);
        String response = (String) inFromServer.readObject();
        System.out.println(response);
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}
