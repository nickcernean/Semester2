import java.io.DataInputStream;
import java.io.DataOutputStream;
import java.io.IOException;
import java.net.Socket;
import java.net.UnknownHostException;
import java.util.Scanner;

public class TaskListClient
{
  private Socket socket;
  DataInputStream in;
  DataOutputStream out;
  Scanner input;

  public TaskListClient(String host, int port)
      throws IOException, UnknownHostException
  {
    socket = new Socket(host, port);
    input = new Scanner(System.in);
    in = new DataInputStream(socket.getInputStream());
    out = new DataOutputStream(socket.getOutputStream());
  }

  public void execute() throws IOException
  {
    while (true)
    {
      if (!in.readUTF().equals("disconnect"))
      {
        System.out.println(
            "1)Add a task\n2)Get a task\n3)Get task size\nAny other number) Exit");
        int switcher = input.nextInt();
        String cases = input.nextLine();
        switch (switcher)
        {
          case 1:
            System.out.println("Enter the task:");
            out.writeUTF(cases);
            System.out.println("Enter estimated time:");
            out.writeUTF(cases);
            break;
          case 2:
            out.writeUTF("GET");
            break;
          case 3:
            out.writeUTF("SIZE");
            break;
        }
      }
      else
      {
        break;
      }
    }
  }
}
