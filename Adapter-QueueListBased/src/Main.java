import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {
    while (true)
    {
      Scanner input = new Scanner(System.in);
      QueueListBased<Object> list = new QueueListBased<Object>();
      while (true)
      {
        System.out.println("Insert a new message");
        String body = input.nextLine();
        if (body.equals("exit"))
        {
          break;
        }
        list.enqueue(body);
      }
      System.out.println(list.toString());

    }
  }
}
