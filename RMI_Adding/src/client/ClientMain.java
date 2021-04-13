package client;

import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain
{
  public static void main(String[] args)
  {

    RmiClient rmiClient = new RmiClient();
    Scanner input = new Scanner(System.in);
    while (true)
    {
      System.out.println("Input a message");
      String line = input.nextLine();
      if (line.equals("exit"))
      {
        break;
      }
      try
      {
        rmiClient.sendMessage(line);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }

    }
  }
}
