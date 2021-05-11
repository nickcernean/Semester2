public class Person implements Runnable
{
  private PublicToilet publicToilet;

  public Person(PublicToilet publicToilet)
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
      System.out.println("Person>> Waiting to enter the cabin");
      spendTime(1000, 2000);
      System.out.println("Person>> Entered the cabin");
      publicToilet.stepIntoCabin();
      System.out.println("Person>> having a good time");
      spendTime(500, 1000);
      System.out.println("Person>> Leaving the cabin");
      publicToilet.leaveCabin();
    }

  }
}
