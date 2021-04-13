package server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MainServer
{
  public static void main(String[] args) throws RemoteException,
      MalformedURLException
  {
    RemoteTaskList server = new RmiTaskServer();

  }
}
