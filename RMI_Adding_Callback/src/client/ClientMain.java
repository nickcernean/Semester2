package client;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain
{
  public static void main(String[] args) throws RemoteException,
      MalformedURLException
  {

    RmiCallBackClient rmiClient = new RmiCallBackClient();
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
        rmiClient.send(line);
      }
      catch (RemoteException e)
      {
        e.printStackTrace();
      }

    }
  }
}
