package client.model;

import client.mediator.UserClient;

import java.io.IOException;

public class ModelManager implements Model
{
  private UserClient userClient;

  public ModelManager() throws IOException
  {
    this.userClient = new UserClient("localhost", 2910);
  }

  @Override public void login(String userName, String password)
      throws IllegalStateException, IllegalArgumentException
  {
    userClient.login(userName, password);
  }
}
