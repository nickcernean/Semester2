public class BurgerBarCustomer implements Runnable
{
  private int burgersToEat;
  private String name;
  private Burgerbar burgerbar;

  public BurgerBarCustomer(String name, Burgerbar burgerbar, int burgersToEat)
  {
    this.name = name;
    this.burgersToEat = burgersToEat;
    this.burgerbar = burgerbar;
  }

  @Override public void run()
  {

    for(int i=0;i<burgersToEat;i++)
    {
      burgerbar.eatBurger(name);
      try
      {
        Thread.sleep(7000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
