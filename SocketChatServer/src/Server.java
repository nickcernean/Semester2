import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.Arrays;

public class Server
{

  public static void main(String[] args) throws IOException
  {
    ArrayList<String> serverArrayList = new ArrayList<>();
    final int PORT = 5677;

    ServerSocket welcomesocket = new ServerSocket(PORT);
    System.out.println("Wait for the client...");
    while (true)
    {
      Socket socket = welcomesocket.accept();
      String client = socket.getInetAddress().getHostAddress();
      System.out.println(client);

      BufferedReader in = new BufferedReader(
          new InputStreamReader(socket.getInputStream()));
      PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

      String data = in.readLine();

      if (data.equals("connect"))
      {
        out.println("Connected");
        while (true)
        {
          data = in.readLine();
          if (data.equals("disconnect"))
          {
            break;
          }
          serverArrayList.add(data);
        }
        break;
      }
      else
      {
        out.println("Disconnected");
        socket.close();
      }
      out.println(Arrays.toString(serverArrayList.toArray()));

    }
  }
}
