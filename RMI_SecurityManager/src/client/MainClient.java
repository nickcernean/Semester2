package client;

import java.rmi.RemoteException;

public class MainClient
{
  public static void main(String[] args) throws RemoteException
  {
    if (System.getSecurityManager() == null)
    {
      System.setSecurityManager(new SecurityManager());
    }

    String host = "localhost";
    if (args.length > 0)
    {
      host = args[0];
    }
    RmiTaskClient client = new RmiTaskClient(host);
    client.start();
  }
}

