import java.util.ArrayList;

public class Bar implements Runnable
{
  private ArrayList<Beer> beers;
  private int maxNoOfBeer;
  private boolean barIsOpen;

  public Bar(int maxNoOfBeers)
  {
    this.maxNoOfBeer = 20;
    this.beers = new ArrayList<>();
    this.barIsOpen = true;
  }

  public synchronized void placeBeer(Beer b)
  {
    while (beers.size() >= maxNoOfBeer)
    {
      try
      {
        System.out
            .println(b + "Full bar, waiting to empty. Total:" + getNoOfBeers());
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    beers.add(b);
    System.out
        .println(b + " has been added in the bar. Total:" + getNoOfBeers());
    notifyAll();
  }

  public synchronized void takeBeer()
  {
    while (beers.size() <= 0)
    {
      try
      {
        System.out.println("A beer is waited to be placed at the bar. Total:"
            + getNoOfBeers());
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    beers.remove(beers.size() - 1);
    System.out
        .println("A beer has been taken from the bar. Total:" + getNoOfBeers());
    notifyAll();
  }

  public synchronized int getNoOfBeers()
  {
    return beers.size();
  }

  @Override public void run()
  {
    while (barIsOpen)
    {
      try
      {
        Thread.sleep(10000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
      barIsOpen = false;
    }
    System.out.println("Bar is closed");
  }
}
