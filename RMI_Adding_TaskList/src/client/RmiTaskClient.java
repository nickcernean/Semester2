package client;

import server.RemoteTaskList;
import server.Task;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.util.Scanner;

public class RmiTaskClient
{
  private RemoteTaskList server;

  public RmiTaskClient(String host)
  {
    try
    {
      server = (RemoteTaskList) Naming.lookup("rmi://"+host+":1099/Case");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void start() throws RemoteException
  {
    Scanner input = new Scanner(System.in);
    while (true)
    {
      System.out.println("1)ADD task \n 2)GET task\n 3)RETURN no of tasks");
      int menu = input.nextInt();
      switch (menu)
      {
        case 1:
          System.out.println("Input task body: ");
          String body = input.nextLine();

          input.nextLine();

          System.out.println("Input estimated time: ");
          long estimatedTime = input.nextLong();

          server.add(new Task(body, estimatedTime));
          break;
        case 2:
          System.out.println(server.get());
          break;
        case 3:
          System.out.println("Size is: " + server.size());
          break;
      }

    }

  }
}
