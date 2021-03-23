package server;

import server.mediator.UserServer;
import server.model.Model;
import server.model.ModelManager;

import java.io.IOException;

public class MainServer
{
  public static void main(String[] args) throws IOException
  {
    Model model = new ModelManager();
    UserServer userServer = new UserServer(model);
    new Thread(userServer).start();
  }
}
