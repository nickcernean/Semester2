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
      while (true)
      {
        System.out.println(in.readObject());
        Scanner input = new Scanner(System.in);
        String msg = input.next();

        out.writeObject(msg);
        System.out.println(in.readObject());

        String msg2 = input.next();
        if ("stop".equals(msg2))
        {
          return;
        }
        out.writeObject(msg2);

        System.out.println(in.readObject());
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }

  }
}
