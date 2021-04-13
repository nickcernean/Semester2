package client;

import server.RemoteMessageList;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.Remote;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class RmiCallBackClient implements RemoteSender
{
  private RemoteMessageList server;

  public RmiCallBackClient() throws RemoteException, MalformedURLException
  {
    try
    {
      server = (RemoteMessageList) Naming.lookup("rmi://localhost:1099/Case");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    UnicastRemoteObject.exportObject(this,0);
    Naming.rebind("Case",this);
  }

  public void send(String text) throws RemoteException
  {
    server.addMessage(text,this);
  }

  @Override public void replyMessage(String message) throws RemoteException
  {
    System.out.println(message);
  }
}
