package client;

import server.RemoteMessageList;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class RmiClient
{
  private RemoteMessageList server;

  public RmiClient()
  {
    try
    {
      server = (RemoteMessageList) Naming.lookup("rmi://localhost:1099/Case");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  public void sendMessage(String text) throws RemoteException
  {
    server.addMessage(text);
  }
}
