import model.TaskList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskListServer
{
  private TaskList taskList;
  private ServerSocket welcomeSocket;
  private TaskListCommunicationThreadHandler taskListCommunicationThreadHandler;

  public TaskListServer(TaskList taskList, int port) throws IOException
  {
    this.welcomeSocket = new ServerSocket(port);
    Socket socket = welcomeSocket.accept();
    this.taskList = new TaskList();
    taskListCommunicationThreadHandler = new TaskListCommunicationThreadHandler(
        socket, taskList);
  }

  public void execute() throws IOException
  {
    while (true)
    {
      Thread thread = new Thread(taskListCommunicationThreadHandler);
      thread.setDaemon(true);
      thread.start();
    }
  }
}
