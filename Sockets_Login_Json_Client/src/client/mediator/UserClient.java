package client.mediator;

import client.model.Model;
import com.google.gson.Gson;

import java.io.*;
import java.net.Socket;

public class UserClient implements Model
{
  private Socket socket;
  private BufferedReader in;
  private PrintWriter out;
  private Gson gson;

  public UserClient(String HOST, int PORT) throws IOException
  {
    this.socket = new Socket();
    this.in = new BufferedReader(
        new InputStreamReader(new DataInputStream(socket.getInputStream())));
    this.out = new PrintWriter(socket.getOutputStream(), true);
    this.gson = new Gson();
  }

  public void disconnect() throws IOException
  {
    socket.close();
  }

  @Override public void login(String userName, String password)
      throws IllegalStateException, IllegalArgumentException
  {
    User user = new User(userName, password);
    String json = gson.toJson(user);
    out.println(json);

    try
    {
      String serverResponse = in.readLine();
      if (!serverResponse.startsWith("Success"))
      {
        throw new IllegalArgumentException("Not logged in!");
      }
    }
    catch (IOException e)
    {
      e.printStackTrace();
      System.out.println(e.getMessage());
    }
  }
}
