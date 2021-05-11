public class CleaningPerson implements Runnable
{
  private PublicToilet publicToilet;

  public CleaningPerson(PublicToilet publicToilet)
  {
    this.publicToilet = publicToilet;
  }

  private void spendTime(int min, int max)
  {
    long time = (long) (Math.random() + (max - min) + min);
    try
    {
      Thread.sleep(time);
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
      System.out.println("Cleaning person>> Waiting to clean");
      spendTime(1000, 2000);
      System.out.println("Cleaning person>> Started cleaning");
      publicToilet.startCleaning();
      spendTime(3000, 4000);
      System.out.println("Cleaning person>> Finished cleaning");
      publicToilet.endCleaning();
    }
  }
}
