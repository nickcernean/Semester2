import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.rmi.UnknownHostException;
import java.util.Scanner;

public class MainMeth
{
  public static void main(String[] args)
      throws UnknownHostException, IOException
  {
    final int PORT = 5678;
    final String HOST = "localhost";
    Scanner input = new Scanner(System.in);
    Socket socket = new Socket(HOST, PORT);
    System.out.println("Connect to the server: ");
    BufferedReader in = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);

    String request = input.nextLine();

    out.println(request);

    String reply = in.readLine();
    System.out.println("Server>" + reply);

    String reques2 = input.nextLine();
    out.println(reques2);

    String reply2 = in.readLine();
    System.out.println("Server>"+reply2);

    String reques3 = input.nextLine();
    out.println(reques3);

    String reply3 = in.readLine();
    System.out.println("Server>"+reply3);




  }
}
