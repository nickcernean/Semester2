package server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;

public class Server
{
  public static void main(String[] args)
      throws IOException, ClassNotFoundException
  {
    ServerSocket welcomeSocket = new ServerSocket(2910);
    System.out.println("Server started>>");

    Socket socket = welcomeSocket.accept();
    System.out.println("Client connected");
    ObjectInputStream in = new ObjectInputStream(socket.getInputStream());
    ObjectOutputStream out = new ObjectOutputStream(socket.getOutputStream());
    String  i1 = (String)in.readObject();
    System.out.println("Received: " + i1);
    String i2 =(String) in.readObject();
    System.out.println("Received: " + i2);

    int result = Integer.valueOf(i1)+Integer.valueOf(i2);
    System.out.println("Result: " + result);
    String toSend=""+result;
    out.writeObject(toSend);

  }
}
