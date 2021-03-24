package client;

import java.io.BufferedReader;
import java.io.IOException;

public class TaskListClientReceiver implements Runnable
{
  private BufferedReader in;
  private TaskListClient taskListClient;

  public TaskListClientReceiver(TaskListClient taskListClient,
      BufferedReader in)
  {
    this.taskListClient=taskListClient;
    this.in = in;
  }

  @Override public void run()
  {
    while (true)
    {
      try
      {
        taskListClient.receive(in.readLine());
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
