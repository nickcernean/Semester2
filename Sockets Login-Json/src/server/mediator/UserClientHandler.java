package server.mediator;

import com.google.gson.Gson;
import server.model.Model;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class UserClientHandler implements Runnable
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private boolean running;
  private Gson gson;
  private String clientIp;
  private Model model;

  public UserClientHandler(Socket socket, Model model) throws IOException
  {
    this.model = model;
    this.socket = new Socket();
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    this.gson = new Gson();
    this.clientIp = "";
    this.running = false;

  }

  public void close() throws IOException
  {
    running = false;
    socket.close();
  }

  @Override public void run()
  {
    running = true;
    try
    {
      if (running)
      {
        String fromClient = in.readLine();
        System.out.println("Server> " + fromClient);
        UserPackage userPackage = gson.fromJson(fromClient, UserPackage.class);
        model.addUser(userPackage.getUser(), userPackage.getPassword());
        out.println("Success : you are now logged in");
        close();
      }
    }
    catch (IOException e)
    {
      running = true;
      e.printStackTrace();
      out.println("Server> " + e.getMessage());
    }

  }
}
