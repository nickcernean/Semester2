package server;

import java.net.MalformedURLException;
import java.rmi.RemoteException;

public class MainServer
{
  public static void main(String[] args)
      throws RemoteException, MalformedURLException
  {
    if (System.getSecurityManager() == null)
    {
      System.setSecurityManager(new SecurityManager());
    }
    RemoteTaskList server = new RmiTaskServer();

  }
}
