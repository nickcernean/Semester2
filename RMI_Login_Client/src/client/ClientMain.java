package client;

import client.mediator.Client;

import java.rmi.RemoteException;
import java.util.Scanner;

public class ClientMain
{
  public static void main(String[] args)
  {
    Client client = new Client();
    Scanner input = new Scanner(System.in);

    while (true)
    {
      System.out.println("Input username: ");
      String username = input.nextLine();

      System.out.println("Input password: ");
      String password = input.nextLine();
      try
      {
        client.login(username, password);
        break;
      }
      catch (RemoteException e)
      {
        System.out.println(e.getMessage());
      }

    }
  }
}
