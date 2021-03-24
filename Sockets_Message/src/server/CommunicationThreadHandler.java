package server;

import client.Message;
import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;

public class CommunicationThreadHandler implements Runnable
{
  private String ip;
  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;

  public CommunicationThreadHandler(Socket socket) throws IOException
  {
    this.socket = new Socket();
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
  }

  private String readFromClient()
  {
    String message = null;
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

  private void close()
  {
    try
    {
      socket.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    Gson gson = new Gson();
    while (true)
    {
      String fromClient = readFromClient();
      Message message = gson.fromJson(fromClient, Message.class);
      if (message.getBody().equals("EXIT"))
      {
        close();
        System.out.println("Client disconnected");
        break;
      }
      else
      {
        System.out.println(fromClient);
      }
    }

  }
}
