public class Mailbox implements Runnable
{
  private long maxFrequency;
  private int count;
  private static final long RUNTIME = 100000;

  public Mailbox(int count)
  {
    this.maxFrequency = 0;
    this.count = count;
  }

  private void waitingForMails()
  {
    try
    {
      Thread.sleep(RUNTIME / count);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    while (maxFrequency<=count)
    {
      System.out.println("New mail in your mailbox...");
      waitingForMails();
      maxFrequency++;
    }
  }

}
