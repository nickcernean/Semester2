public class Bartender implements Runnable
{
  private Bar bar;
  private int timeToServe;

  public Bartender(int timeToServe, Bar bar)
  {
    this.timeToServe = timeToServe;
    this.bar = bar;

  }

  @Override public void run()
  {
    while (true)
    {
      serve();
      bar.placeBeer(new Beer());
    }
  }

  public void serve()
  {
    try
    {
      Thread.sleep(timeToServe);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
