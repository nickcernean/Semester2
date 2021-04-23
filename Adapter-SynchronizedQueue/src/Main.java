import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {
    while (true)
    {
      Scanner input = new Scanner(System.in);
      System.out.println("Input capacity");
      int cap = input.nextInt();
      input.nextLine();
      SynchronizedQueue<Object> queue = new SynchronizedQueue<Object>(cap);
      while (true)
      {
        System.out.println("Insert a new message: ");
        String body = input.nextLine();
        if (body.equals("exit"))
        {
          break;
        }
        try
        {
          queue.put(body);
        }
        catch (Exception e)
        {
          System.out.println(e.getMessage()+" a new queue started");
        }
      }
      System.out.println(queue.toString());

    }
  }
}
