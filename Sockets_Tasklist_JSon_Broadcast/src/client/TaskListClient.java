package client;

import com.google.gson.Gson;
import model.Task;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class TaskListClient
{
  private static final String RED = "\u001B[31m";
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Scanner input;
  public static final String RESET = "\u001B[0m";
  public static final String PURPLE = "\u001B[35m";
  private String receivedString;


  public TaskListClient(String host, int port) throws IOException
  {
    socket = new Socket(host, port);
    in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
    out = new PrintWriter(socket.getOutputStream(), true);
    input = new Scanner(System.in);
    this.receivedString = null;
    new Thread(new TaskListClientReceiver(this,in)).start();
    execute();
  }

  public void receive(String s)
  {
    if (s.equals("ADD") || s.equals("REMOVE"))
    {
      System.out.println("Broadcast> " + s);
    }
    else
    {
      receivedString = s;
      notify();
    }
  }

  private void writeToSever(String message)
  {
    out.println(message);
  }

  public void execute() throws IOException
  {
    Gson gson = new Gson();
    while (true)
    {

      System.out.println(
          "1)Add a task\n2)Get a task\n3)Get task size\nAny other number) Exit");
      int switcher = input.nextInt();// Input 1
      if (switcher == 1 || switcher == 2 || switcher == 3)
      {
        switch (switcher)
        {
          case 1:
            writeToSever("ADD");//Sending 1==Reading 1
            System.out.println("Enter the task: ");
            String inputTask = input.next(); //Writing 1

            input.nextLine();

            System.out.println("Enter the estimated time:");
            long time1 = input.nextLong();//Writing 2

            Task task = new Task(inputTask, time1);
            String json = gson.toJson(task);

            writeToSever(json);//Sending 3==Reading 3
            try
            {
              wait();
            }
            catch (InterruptedException e)
            {
              e.printStackTrace();
            }
            System.out.println(receivedString);
            break;
          case 2:
            writeToSever("GET");
            try
            {
              wait();
            }
            catch (InterruptedException e)
            {
              e.printStackTrace();
            }
            String reply = receivedString;
            if (!reply.equals("null"))
            {
              Task task1 = gson.fromJson(reply, Task.class);
              System.out.println(PURPLE + "Server> " + task1 + RESET);
            }
            else
            {
              System.out.println(RED + "Server> ADD a task please!" + RESET);
            }
            break;
          case 3:
            writeToSever("SIZE");
            try
            {
              wait();
            }
            catch (InterruptedException e)
            {
              e.printStackTrace();
            }
            System.out.println(receivedString);
            break;
        }
      }
      else
      {
        writeToSever("EXIT");
        System.out.println(receivedString);
        socket.close();
        break;
      }
    }
  }
}
