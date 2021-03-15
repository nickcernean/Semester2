import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.Scanner;

public class MainMethod
{
  public static void main(String[] args) throws IOException
  {
    final int PORT = 5678;
    Scanner input = new Scanner(System.in);

    ServerSocket welcomeSocket = new ServerSocket(PORT);
    boolean infinite = true;
    while (infinite)
    {
      System.out.println("Wait for the client...");
      Socket socket = welcomeSocket.accept();
      String client=socket.getInetAddress().getHostAddress();
      System.out.println(client+"connected");
      BufferedReader in = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
      String data = in.readLine();
      if (!data.equals("connect"))
      {
        out.println("Disconnected");
        socket.close();
      }
      else
      {
        out.println("Username!");
       String user=in.readLine();
        out.println("Password?");
        String password=in.readLine();
        out.println("Approved");
      }
      socket.close();
    }


  }

}
