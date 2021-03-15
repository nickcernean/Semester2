public class Burgerbar
{
  private int numberOfBurgers;
  private int maxNumberOfBurgers;

  public Burgerbar(int maxNumberOfBurgers)
  {
    this.numberOfBurgers = 0;
    this.maxNumberOfBurgers = maxNumberOfBurgers;
  }

  public synchronized void makeBurger(String employeeName)
  {
    while (numberOfBurgers >= maxNumberOfBurgers)
    {
      try
      {
        System.out.println(
            employeeName + "is waiting to create a burger. Total:"
                + numberOfBurgers);
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    numberOfBurgers++;
    System.out
        .println(employeeName + "made a burgers. Total:" + numberOfBurgers);
    notifyAll();
  }

  public synchronized void eatBurger(String who)
  {
    while (numberOfBurgers <= 0)
    {
      try
      {
        System.out
            .println(who + "is waiting for a burger. Total:" + numberOfBurgers);
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    numberOfBurgers--;
    System.out.println(who + "is eating a burger. Total:" + numberOfBurgers);
    notifyAll();
  }

  public synchronized int getNumberOfBurgers()
  {
    return numberOfBurgers;
  }
}
