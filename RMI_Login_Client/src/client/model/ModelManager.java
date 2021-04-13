package client.model;

import client.mediator.Client;

import java.io.IOException;
import java.rmi.RemoteException;

public class ModelManager implements Model
{
  private Client client;

  public ModelManager() throws IOException
  {
    this.client = new Client();
  }

  @Override public void login(String userName, String password)
      throws IllegalStateException, IllegalArgumentException, RemoteException
  {
    client.login(userName, password);
  }
}
