package client;

import shared.Message;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient
{
  private ObjectInputStream in;
  private Socket socket;

  public void startClient() throws IOException, ClassNotFoundException
  {
    socket = new Socket("localhost", 2910);
    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    in = new ObjectInputStream(socket.getInputStream());

    Thread thread1 = new Thread(() -> listenToServer());
    thread1.setDaemon(true);
    thread1.start();

    Scanner scanner = new Scanner(System.in);
    System.out.println("Insert username: ");
    String username = scanner.nextLine();
    out.writeObject(username);
    while (true)
    {
      System.out.println("Input>");
      String msg = scanner.nextLine();
      Message m = new Message(msg, username);
      out.writeObject(m);
      if (msg.equalsIgnoreCase("exit"))
      {
        break;
      }

    }
  }

  private void listenToServer()
  {

    try
    {
      while (true)
      {
        Message response = (Message) in.readObject();
        if (response.getMessageBody().equalsIgnoreCase("exit"))
        {
          socket.close();
          break;
        }
        System.out.println(response);
      }

    }
    catch (IOException | ClassNotFoundException e)
    {
      e.printStackTrace();
    }
  }
}
