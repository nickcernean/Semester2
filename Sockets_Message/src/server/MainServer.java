package server;

import java.io.IOException;

public class MainServer
{
  public static void main(String[] args)
  {
    try
    {
      ChatServer chatServer = new ChatServer(2910);
      chatServer.execute();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
