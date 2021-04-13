package client.mediator;

import client.model.Model;
import server.mediator.RemoteModel;

import java.rmi.Naming;
import java.rmi.RemoteException;

public class Client implements Model
{
  //  private User user;
  private RemoteModel remoteModel;

  public Client()
  {
    try
    {
      remoteModel = (RemoteModel) Naming.lookup("rmi://localhost:1099/Case");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
  }

  @Override public void login(String userName, String password)
      throws IllegalStateException, IllegalArgumentException, RemoteException
  {
    remoteModel.addUser(userName, password);
  }
}
