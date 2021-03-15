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
  private UserPackage userPackage;

  public UserClientHandler(Socket socket, Model model) throws IOException
  {
    this.model = model;
    this.socket = new Socket();
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    this.gson = new Gson();
    this.clientIp = "";
    this.running = true;
    this.userPackage = new UserPackage();
  }

  public void close()
  {

  }

  @Override public void run()
  {

  }
}
