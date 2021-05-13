import static java.lang.Thread.sleep;

public class RedCar implements Runnable
{
  private Bridge bridge;

  public RedCar(Bridge bridge)
  {
    this.bridge = bridge;
  }

  private void simulatePassing(int noOfSeconds)
  {
    try
    {
      sleep((long) 1000 * noOfSeconds);
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
      bridge.enterFromTheLeft();
      simulatePassing(3);
      bridge.exitToTheRight();
    }
  }
}
