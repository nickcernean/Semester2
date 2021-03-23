package client;

import client.model.Model;
import client.model.ModelManager;

import java.io.IOException;
import java.util.Scanner;

public class MainClient
{
  public static void main(String[] args) throws IOException
  {
    Model model = new ModelManager();
    Scanner input = new Scanner(System.in);
    while (true)
    {
      try
      {
        System.out.println("Username: ");
        String username=input.nextLine();
        System.out.println("Password: ");
        String password=input.nextLine();
        model.login(username,password);
      }
      catch (IllegalStateException | IllegalArgumentException e)
      {
        System.out.println(e.getMessage());
      }
    }
  }
}
