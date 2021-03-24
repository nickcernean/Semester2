package client;

import com.google.gson.Gson;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class ChatClient
{
  private Scanner input;
  private BufferedReader in;
  private PrintWriter out;
  private Socket socket;

  public ChatClient(String host, int port) throws IOException
  {
    this.socket = new Socket(host, port);
    this.input = new Scanner(System.in);
    this.in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    this.out = new PrintWriter(socket.getOutputStream(), true);
  }

  public void execute()
  {
    Gson gson = new Gson();
    while (true)
    {
      System.out.println("Input a message body: ");
      String keyInput = input.nextLine();
      Message message = new Message(keyInput);
      String json = gson.toJson(message);
      writeToSever(json);
      if (keyInput.equals("EXIT"))
      {
        close();
        break;
      }
    }
  }

  private void writeToSever(String message)
  {
    out.println(message);
  }

  public void close()
  {
    try
    {
      socket.close();
      input.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }
  }
}
