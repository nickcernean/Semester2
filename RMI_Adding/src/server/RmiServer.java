package server;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;
import java.util.ArrayList;

public class RmiServer implements RemoteMessageList
{
  private ArrayList<String> messagesList;

  public RmiServer()
  {
    messagesList = new ArrayList<>();
  }

  public void start() throws RemoteException, MalformedURLException
  {
    UnicastRemoteObject.exportObject(this,0);
    Naming.rebind("Case",this);
  }

  @Override public void addMessage(String message) throws RemoteException
  {
    System.out.println(message);
  }
}
