public class Churchgoer implements Runnable
{
  private ConfessionChair confessionChair;

  public Churchgoer(ConfessionChair confessionChair)
  {
    this.confessionChair = confessionChair;
  }

  @Override public void run()
  {
    for (int i = 0; i < 3; i++)
    {
      System.out.println("Entered the confession chair");
      confessionChair.EnterConfessionBooth("sin");
      sleep1();
      confessionChair
          .leaveConfessionBooth();
      System.out.println("Sin confessed, say Ave Maria" +confessionChair
          .leaveConfessionBooth() + " times");
      try
      {
        Thread.sleep(1000);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }

  public void sleep1()
  {
    try
    {
      Thread.sleep(1000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }


}
