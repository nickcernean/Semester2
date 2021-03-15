public class Customer implements Runnable
{
  private Bar bar;
  private int timeToDrink;

  public Customer(int timeToDrink, Bar bar)
  {
    this.bar = bar;
    this.timeToDrink = timeToDrink;
  }

  @Override public void run()
  {
    for (int i = 0; i < 3; i++)
    { drink();
      bar.takeBeer();
    }
  }
  public void drink()
  {
    try
    {
      Thread.sleep(timeToDrink);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
