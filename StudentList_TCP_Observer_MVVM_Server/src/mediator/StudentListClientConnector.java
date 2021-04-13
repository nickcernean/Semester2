package mediator;

import model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class StudentListClientConnector implements Runnable
{
  private final int PORT = 9876;
  private Model model;
  private boolean running;
  private ServerSocket welcomeSocket;
  public StudentListClientConnector(Model model)
  {
    this.model = model;
  }

  @Override public void run()
  {
    try
    {
      System.out.println("Starting Server...");
      welcomeSocket = new ServerSocket(PORT);

      running = true;
      while (running)
      {
        System.out.println("Waiting for a client...");
        Socket socket = welcomeSocket.accept();
        Thread client = new Thread(new StudentListClientHandler(socket, model));
        client.setDaemon(true);
        client.start();
      }
    }
    catch (IOException e)
    {
      System.out.println("Error: " + e.getMessage());
    }
  }

  public void close()
  {
    running = false;
    try
    {
      welcomeSocket.close();
    }
    catch (Exception e)
    {
      //
    }
  }
}
