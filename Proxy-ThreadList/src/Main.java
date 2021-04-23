import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {
    while (true)
    {
      Scanner input = new Scanner(System.in);
      System.out.println("Input the type: 1,2,3");
      int type = input.nextInt();
      input.nextLine();
      ThreadSafeLinkedList<String> list = new ThreadSafeLinkedList<>(type);
      while (true)
      {
        System.out.println("Insert a new message");
        String body = input.nextLine();
        if (body.equals("exit"))
        {
          break;
        }
        list.add(body);
      }
      System.out.println(list.toString());

    }
  }
}
