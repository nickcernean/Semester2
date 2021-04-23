import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);

    while (true)
    {
      System.out.println("Input log level filter: 'verbose' OR 'sparse' ");
      String loglevel = input.nextLine();
      Logger logger = new LogLevelController(loglevel);
      while (true)
      {
        System.out.println("Input messages to the log:");
        String message = input.nextLine();
        if (message.equals("exit"))
        {
          break;
        }
        logger.log(message);
      }
    }
  }
}
