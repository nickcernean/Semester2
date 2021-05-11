import java.util.Arrays;
import java.util.Scanner;

public class BarSimulation
{
  public static void main(String[] args)
  {
    Scanner input = new Scanner(System.in);
    Bar bar = new RegularBar();
    while (true)
    {

      System.out.println("Menu:");
      System.out.println(Arrays.toString(bar.getDrinkTypes()));
      System.out.println("Enter a drink: ");
      String drinkInput = input.nextLine();

      System.out.println("Making the drink...");
      bar.makeDrink(drinkInput);
      sleeping();

      System.out.println("Drinking: "+bar.orderDrink(drinkInput).getName()+" "+bar.orderDrink(drinkInput).getDescription());

      sleeping();
    }

  }
  private static void sleeping()
  {
    try
    {
      Thread.sleep(3000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

}
