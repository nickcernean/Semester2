package client;

import java.io.IOException;

public class MainClient
{
  public static void main(String[] args) throws IOException
  {
    ChatClient chatClient = new ChatClient("localhost", 2910);
    chatClient.execute();
  }
}
