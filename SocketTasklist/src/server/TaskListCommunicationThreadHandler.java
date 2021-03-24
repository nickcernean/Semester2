package server;

import model.Task;
import model.TaskList;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TaskListCommunicationThreadHandler implements Runnable
{
  private BufferedReader in;
  private PrintWriter out;
  private String ip;
  private TaskList taskList;
  public static final String PURPLE = "\u001B[35m";
  public static final String RESET = "\u001B[0m";
  public static final String RED = "\u001B[31m";

  public TaskListCommunicationThreadHandler(Socket socket, TaskList taskList)
      throws IOException
  {
    this.taskList = taskList;
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
  }

  private String readFromClient()
  {
    String message = "okay";
    try
    {
      message = in.readLine();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
    return message;
  }

  private void writeToClient(String message)
  {
    out.println(message);
  }

  @Override public void run()
  {
    while (true)
    {
      String message2 = readFromClient();///Reading 1
      if (message2.equals("ADD") || message2.equals("GET") || message2
          .equals("SIZE"))
      {
        switch (message2)
        {
          case "ADD":
            String taskName = readFromClient();//Reading 2
            System.out.println(taskName);

            String time1 = readFromClient();//Reading 3
            long estimatedTime = Integer.parseInt(time1);
            System.out.println(estimatedTime);

            taskList.add(new Task(taskName, estimatedTime));
            writeToClient(PURPLE + "Server> Task has been added" + RESET);
            break;
          case "GET":
            Task getTask = taskList.getAndRemoveNextTask();
            if (getTask != null)
            {
              writeToClient(
                  PURPLE + "Server> " + getTask.getText() + ": " + getTask
                      .getEstimatedTime() + RESET);
            }
            else
            {
              writeToClient(RED + "ADD a task please!" + RESET);
            }
            break;
          case "SIZE":
            writeToClient(
                PURPLE + "Server> TaskList size: " + taskList.size() + RESET);
        }
      }
      else
      {
        writeToClient("Disconnected from server...");
        break;
      }

    }
  }
}

