public class MainTest
{
  public static void main(String[] args)
  {
    Bar bar = new Bar(20);
    Thread tbar = new Thread(bar);
    tbar.start();
    Thread[] bartenderThreads = new Thread[3];
    for (int i = 0; i < bartenderThreads.length; i++)
    {
      Bartender bartender = new Bartender(1000, bar);
      bartenderThreads[i] = new Thread(bartender);
      bartenderThreads[i].setDaemon(true);
      bartenderThreads[i].start();
    }
    Thread[] customerThreads = new Thread[5];
    for (int i = 0; i < customerThreads.length; i++)
    {
      Customer customer = new Customer(3000, bar);
      customerThreads[i] = new Thread(customer);
      customerThreads[i].setDaemon(true);
      customerThreads[i].start();
    }

    try
    {
      tbar.join();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
