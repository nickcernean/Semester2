import java.util.Scanner;

public class Main
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    while (true)
    {
      System.out.println("Input person type: ");
      String person=input.nextLine();
      try
      {
        ZooVisitator zooVisitator= new ZooVisitator(person);
        zooVisitator.feed();
        zooVisitator.view();
        zooVisitator.pet();
      }
      catch (Exception e)
      {
        System.out.println("Ex"+e.getMessage());
      }


    }

  }
}
