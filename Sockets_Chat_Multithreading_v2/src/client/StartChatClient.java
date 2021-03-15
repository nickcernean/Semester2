package client;

import java.io.IOException;

public class StartChatClient
{
  public static void main(String[] args)throws IOException,ClassNotFoundException
  {
    ChatClient chatClient=new ChatClient();
    chatClient.startClient();
  }
}
