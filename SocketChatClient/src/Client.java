import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class Client
{
  public static void main(String[] args)
      throws UnknownHostException, IOException
  {
    final int PORT = 5677;
    final String HOST = "localhost";
    Scanner input = new Scanner(System.in);
    Socket socket = new Socket(HOST, PORT);
    System.out.println("Connect to server...");
    BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

    String request = input.nextLine();
    out.println(request);

    String reply = in.readLine();
    System.out.println("Server>" + reply);
    if (reply.equals("Connected"))
    {
      while (true)
      {
        System.out.println("Input new message:");
        String request2 = input.nextLine();
        if (request2.equals("disconnect"))
        {
          out.println("disconnect");
          System.out.println("Disconnected");
          break;
        }
        out.println(request2);
      }
    }
  }

}

