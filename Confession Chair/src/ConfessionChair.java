public class ConfessionChair
{
  private String sin;

  public ConfessionChair()
  {
    this.sin = null;
  }

  public synchronized void EnterConfessionBooth(String sin)
  {

    while (this.sin == null)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    this.sin = sin;
  }

  public synchronized int leaveConfessionBooth()
  {
    sin = null;
    notify();
    return (int) (Math.random()*10)+5;
  }
}
