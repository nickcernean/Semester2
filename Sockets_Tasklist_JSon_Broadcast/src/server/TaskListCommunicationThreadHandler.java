package server;

import com.google.gson.Gson;
import model.Task;
import model.TaskList;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class TaskListCommunicationThreadHandler
    implements Runnable, PropertyChangeListener
{
  private BufferedReader in;
  private PrintWriter out;
  private String ip;
  private TaskList taskList;
  public static final String PURPLE = "\u001B[35m";
  public static final String RESET = "\u001B[0m";

  public TaskListCommunicationThreadHandler(Socket socket, TaskList taskList)
      throws IOException
  {
    this.taskList = taskList;
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    taskList.addListener(this);
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
    Gson gson = new Gson();
    while (true)
    {
      String message2 = readFromClient();///Reading 1
      if (message2.equals("ADD") || message2.equals("GET") || message2
          .equals("SIZE"))
      {
        switch (message2)
        {
          case "ADD":
            String taskData = readFromClient();//Reading 2
            Task task = gson.fromJson(taskData, Task.class);
            System.out.println(taskData);
            taskList.add(task);
            writeToClient(PURPLE + "Server> Task has been added" + RESET);
            break;
          case "GET":
            Task task1 = taskList.getAndRemoveNextTask();
            if (task1 != null)
            {
              String json = gson.toJson(task1);
              writeToClient(json);
            }
            else
            {
              writeToClient("null");
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

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    switch (evt.getPropertyName())
    {
      case "ADD":
        out.println(evt.getNewValue());
        break;
      case "REMOVE":
        out.println(evt.getNewValue());
        break;
      case "SIZE":
        out.println(evt.getNewValue());
        break;
    }

  }
}

