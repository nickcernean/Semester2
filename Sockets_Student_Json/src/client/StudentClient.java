package client;

import com.google.gson.Gson;
import shared.Message;

import java.io.BufferedReader;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Scanner;

public class StudentClient
{
  public static void main(String[] args) throws IOException
  {
    final int PORT = 6789;
    final String HOST = "localhost";

    Gson gson = new Gson();
    Socket socket = new Socket(HOST, PORT);
    Scanner inFromUser = new Scanner(System.in);
    BufferedReader inFromServer = new BufferedReader(
        new InputStreamReader(socket.getInputStream()));
    PrintWriter out = new PrintWriter(socket.getOutputStream(), true);
    System.out.println("Enter your name: ");
    String name = inFromUser.nextLine();
    System.out.println("Enter your student number: ");
    int number = inFromUser.nextInt();
    inFromUser.close();

    Student student = new Student(number, name);
    System.out.println("Student object: " + student);

    String json = gson.toJson(student);
    out.println(json);

    String serverReply = inFromServer.readLine();
    System.out.println("Server>" + serverReply);

    Message reply = gson.fromJson(serverReply, Message.class);
    System.out.println("Message: " + reply);
    socket.close();
  }

}
