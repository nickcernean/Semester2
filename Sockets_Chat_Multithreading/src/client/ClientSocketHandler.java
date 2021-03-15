package client;

import shared.Request;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ClientSocketHandler implements Runnable
{
  private ObjectInputStream inFromServer;
  private Socket socket;
  private Client client;
  private ObjectOutputStream outFromServer;

  public ClientSocketHandler(Socket socket, Client client) throws IOException
  {
    this.socket = socket;
    this.client = client;
    inFromServer = new ObjectInputStream(socket.getInputStream());
    outFromServer = new ObjectOutputStream(socket.getOutputStream());
  }

  @Override public void run()
  {
    try
    {
      int id = (int) inFromServer.readObject();
      client.setID(id);
      while (true)
      {
        String response = (String) inFromServer.readObject();
        client.responseReceived(response);
      }

    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }

  public void sendRequestToServer(String message, int id) throws IOException
  {
    Request request = new Request(id, message);
    outFromServer.writeObject(request);
  }
}
