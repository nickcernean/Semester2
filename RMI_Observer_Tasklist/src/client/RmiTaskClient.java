package client;

import server.RemoteTaskList;
import server.Task;
import utility.observer.event.ObserverEvent;
import utility.observer.listener.RemoteListener;

import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.Scanner;

public class RmiTaskClient implements RemoteListener<Task, Task>
{
  private RemoteTaskList server;

  public RmiTaskClient(String host) throws Exception
  {
    server = (RemoteTaskList) Naming.lookup("rmi://" + host + ":1099/Case");
    UnicastRemoteObject.exportObject(this, 0);
    server.addListener(this);

  }

  public void start() throws RemoteException
  {
    Scanner input = new Scanner(System.in);
    while (true)
    {
      System.out.println("1)ADD task \n 2)GET task\n 3)RETURN no of tasks");
      int menu = input.nextInt();
      input.nextLine();
      switch (menu)
      {
        case 1:
          System.out.println("Input task body: ");

          String body = input.nextLine();

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

  @Override public void propertyChange(ObserverEvent<Task, Task> event)
  {
    System.out.println("Server added: " + event.getValue2());
  }
}
