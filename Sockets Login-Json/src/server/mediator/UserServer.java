package server.mediator;

import server.model.Model;

import java.io.IOException;
import java.net.ServerSocket;
import java.net.Socket;

public class UserServer implements Runnable
{
  private final static int PORT = 2910;
  private boolean running;
  private ServerSocket welcomeSocket;
  private Model model;

  public UserServer(Model model) throws IOException
  {
    this.model = model;
    this.running = false;
    this.welcomeSocket = new ServerSocket(PORT);
  }

  public void close() throws IOException
  {
    welcomeSocket.close();

  }

  @Override public void run()
  {
    System.out.println("Starting server....");
    while (true)
    {
      try
      {
        System.out.println("Waiting for a client...");
        Socket socket = welcomeSocket.accept();
        Thread thread = new Thread(new UserClientHandler(socket, model));
        thread.start();
        running = true;
      }
      catch (IOException e)
      {
        e.printStackTrace();
      }
    }
  }
}
