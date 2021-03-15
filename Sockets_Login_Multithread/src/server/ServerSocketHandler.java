package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{
  private Socket socket;

  public ServerSocketHandler(Socket socket)
  {
    this.socket = socket;
  }

  @Override public void run()
  {
    try
    {
      ObjectInputStream inFromClient = new ObjectInputStream(
          socket.getInputStream());
      ObjectOutputStream outToClient = new ObjectOutputStream(
          socket.getOutputStream());

      String msg = (String) inFromClient.readObject();
      System.out.println("Received: " + msg);
      msg = msg.toUpperCase();
      System.out.println("Sending msg: " + msg);
      outToClient.writeObject(msg);
      System.out.println("Client disconnected...");
    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}
