package server;

import model.TaskList;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class TaskListServer
{

  private ServerSocket welcomeSocket;
  private TaskList taskList;

  public TaskListServer(TaskList taskList, int port) throws IOException
  {
    this.welcomeSocket = new ServerSocket(port);
    this.taskList = taskList;
  }

  public void execute() throws IOException
  {
    System.out.println("Starting server...");
    System.out.println("Waiting for clients...");
    while (true)
    {
      System.out.println("Client connected...");
      Socket socket = welcomeSocket.accept();
      TaskListCommunicationThreadHandler taskListCommunicationThreadHandler = new TaskListCommunicationThreadHandler(
          socket, taskList);
      Thread thread = new Thread(taskListCommunicationThreadHandler);
      thread.setDaemon(true);
      thread.start();
    }
  }
}
