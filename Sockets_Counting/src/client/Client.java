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

      ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
      ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
      Scanner input = new Scanner(System.in);

      String i1 = input.next();
      out.writeObject(i1);
      String i2 = input.next();
      out.writeObject(i2);
      System.out.println("Result: " + in.readObject());

    }
    catch (IOException| ClassNotFoundException e)
    {
      e.printStackTrace();
    }

  }
}
