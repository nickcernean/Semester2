package server;

import shared.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private Server server;
  private Socket socket;
  private int id;


  public ServerSocketHandler(Socket socket, int id, Server server)
  {
    this.socket = socket;
    this.id = id;
    this.server = server;
  }

  @Override public void run()
  {
    try
    {
      ObjectOutputStream outFromServer = new ObjectOutputStream(
          socket.getOutputStream());
      ObjectInputStream inFromClient = new ObjectInputStream(
          socket.getInputStream());
      outFromServer.writeObject(id);
      while (true)
      {
        Request message = (Request) inFromClient.readObject();
        String response = server
            .messageReceived(message.getMessage(), message.getID());
        outFromServer.writeObject(response);
      }
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }

  }

}

