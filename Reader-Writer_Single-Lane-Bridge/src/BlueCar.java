import static java.lang.Thread.sleep;

public class BlueCar implements Runnable
{
  private Bridge bridge;

  public BlueCar(Bridge bridge)
  {
    this.bridge = bridge;
  }

  private void simulatePassing(int
      noOfSeconds)
  {
    try
    {
    sleep((long)1000*noOfSeconds);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    while (true)
    {
      bridge.enterFromTheRight();
      simulatePassing(3);
      bridge.exitToTheLeft();
    }
  }
}
