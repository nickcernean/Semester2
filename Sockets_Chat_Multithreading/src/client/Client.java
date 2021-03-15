package client;

import java.io.IOException;
import java.net.Socket;

public class Client
{
  private int id;

  public static void main(String[] args)
  {
    Client c = new Client();
    c.runClient();
  }

  public void runClient()
  {
    try
    {
      Socket socket = new Socket("localhost", 2910);
      ClientSocketHandler csh = new ClientSocketHandler(socket, this);
      (new Thread(csh)).start();
      id++;
      while (true)
      {
        csh.sendRequestToServer("Hello from client number", id);
      }

    }
    catch (IOException e)

    {
      e.printStackTrace();
    }
  }

  public void setID(int id)
  {
    this.id = id;
  }

  public void responseReceived(String response)
  {
    System.out.println(response);
  }
}
