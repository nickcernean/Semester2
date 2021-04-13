package server;

import server.mediator.Server;
import server.model.ModelManager;

import java.net.MalformedURLException;
import java.rmi.RemoteException;
import java.rmi.registry.LocateRegistry;
import java.rmi.registry.Registry;
import java.rmi.server.ExportException;

public class ServerMain
{
  public static void main(String[] args)
      throws MalformedURLException, RemoteException
  {
    startRegistry();
    Server server = new Server(new ModelManager());
    server.start();
    System.out.println("Server started");
  }

  public static void startRegistry() throws RemoteException
  {
    try
    {
      Registry reg = LocateRegistry.createRegistry(1099);
      System.out.println("Registry started...");
    }
    catch (ExportException e)
    {
      System.out.println("Registry already started? " + e.getMessage());
    }
  }
}
