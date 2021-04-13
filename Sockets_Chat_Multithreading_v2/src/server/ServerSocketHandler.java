package server;

import shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;

public class ServerSocketHandler implements Runnable
{

  public String userName;
  private Socket socket;
  private ObjectInputStream inputStream;
  private ObjectOutputStream outputStream;
  private ConnectionPool pool;

  public ServerSocketHandler(Socket socket, ConnectionPool pool)
      throws IOException
  {
    this.socket = socket;
    this.pool = pool;
    inputStream = new ObjectInputStream(socket.getInputStream());
    outputStream = new ObjectOutputStream(socket.getOutputStream());
  }

  @Override

  public void run()
  {
    try
    {
      userName = (String) inputStream.readObject();
      while (true)
      {
        Message message = (Message) inputStream.readObject();
        String body = message.getMessageBody();

        System.out.println(message);

        if (body.equalsIgnoreCase("exit"))
        {
          pool.removeConnection(this);
          outputStream.writeObject(message);
          socket.close();
          break;
        }
        pool.broadcast(message);
      }

    }
    catch (Exception e)
    {

    }

  }

  public void sendMessageToClient(Message msg)
  {
    try
    {
      outputStream.writeObject(msg);
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  public String getClientName()
  {
    return userName;
  }
}
