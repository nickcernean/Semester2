package server.mediator;

import server.model.Model;

import java.net.MalformedURLException;
import java.rmi.Naming;
import java.rmi.RemoteException;
import java.rmi.server.UnicastRemoteObject;

public class Server implements RemoteModel
{
  private Model model;

  public Server(Model model)
  {
    this.model = model;

  }

  public void start() throws MalformedURLException, RemoteException
  {
    UnicastRemoteObject.exportObject(this, 0);
    Naming.rebind("Case", this);
  }


  @Override public void addUser(String userName, String password)
      throws RemoteException
  {
    model.addUser(userName, password);
  }
}
