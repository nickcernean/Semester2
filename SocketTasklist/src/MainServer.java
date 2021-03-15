import model.Task;
import model.TaskList;

import java.io.IOException;

public class MainServer
{
  public static void main(String[] args)
  {
    final int PORT=5466;
    TaskList taskList=new TaskList();
    try
    {
      TaskListServer taskListServer=new TaskListServer(taskList,PORT);
      taskListServer.execute();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }
}
