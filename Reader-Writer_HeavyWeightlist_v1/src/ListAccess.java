public class ListAccess implements ReadWriteAccess
{
  private int readers;
  private int writers;
  private int waitingWriters;
  private HeavyWeightList heavyWeightList;

  public ListAccess(HeavyWeightList list)
  {
    this.heavyWeightList = list;
    this.readers = 0;
    this.writers = 0;
    this.waitingWriters = 0;
  }

  @Override public synchronized ReadList acquireRead()
  {
    while (writers > 0 || waitingWriters > 0)
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
    readers++;
    System.out.println(
        "Reading>> " + Thread.currentThread().getName() + " \n Waiting: "
            + waitingWriters + " Reading: " + readers + " Writing: " + writers);
    return heavyWeightList;
  }

  @Override public synchronized void releaseRead()
  {
    readers--;
    System.out.println("Released reading!");
    if (readers == 0)
    {
      notify();
      System.out.println("There are no readers!");
    }
  }

  @Override public synchronized ReadWriteList acquireWrite()
  {
    waitingWriters++;
    while (readers > 0 || writers > 0)
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

    waitingWriters--;
    writers++;

    System.out.println(
        "Writing>> " + Thread.currentThread().getName() + " \n Waiting: "
            + waitingWriters + " Reading: " + readers + " Writing: " + writers);

    return heavyWeightList;

  }

  @Override public synchronized void releaseWrite(int I)
  {
    writers--;
    System.out.println("Released writing!");

    if (waitingWriters == 0)
    {
      notifyAll();
      System.out.println("There are no waiting writers!");
    }
  }
}
