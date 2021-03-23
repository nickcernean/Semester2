package client;

import java.io.IOException;

public class MainClient
{
  public static void main(String[] args)
  {
    try
    {
      TaskListClient taskListClient = new TaskListClient("localhost", 5466);
      taskListClient.execute();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
