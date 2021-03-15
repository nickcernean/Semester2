package server;

import server.ChatServer;

import java.io.IOException;

public class StartChatServer
{
  public static void main(String[] args) throws IOException
  {
    ChatServer cs=new ChatServer();
    cs.start();
  }
}
