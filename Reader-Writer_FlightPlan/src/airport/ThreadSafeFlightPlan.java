package airport;

import model.FlightPlan;
import model.FlightPlanRead;
import model.FlightPlanWrite;

public class ThreadSafeFlightPlan implements FlightPlanAccess
{
  private FlightPlan flightPlan;
  private int readers;
  private int writers;
  private int waitingWriter;// Writer preference

  public ThreadSafeFlightPlan(FlightPlan flightPlan)
  {
    this.flightPlan = flightPlan;
    this.readers = 0;
    this.writers = 0;
    this.waitingWriter = 0;// Writer preference
  }

  @Override public synchronized FlightPlanRead acquireRead()
  {
    while (writers > 0 || waitingWriter > 0)// Writer preference
    {
      String txt = " WAIT (readers" + readers + ", writers=" + writers + ")";
      System.out.println(Thread.currentThread().getName() + txt);
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
    String txt = " READING (readers" + readers + ", writers=" + writers + ")";
    System.out.println(Thread.currentThread().getName() + txt);

    return flightPlan;
  }

  @Override public synchronized void releaseRead()
  {
    readers--;
    if (readers == 0)
    {
      notifyAll();// Writer preference
    }
    String txt =
        " FINISHED READING (readers" + readers + ", writers=" + writers + ")";
    System.out.println(Thread.currentThread().getName() + txt);

  }

  @Override public synchronized FlightPlanWrite acquireWrite()
  {
    waitingWriter++;
    while (writers > 0 || readers > 0)
    {
      String txt = " WAIT (readers" + readers + ", writers=" + writers + ")";
      System.out.println(Thread.currentThread().getName() + txt);
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    waitingWriter--;// Writer preference
    writers++;
    String txt = " WRITING (readers" + readers + ", writers=" + writers + ")";
    System.out.println(Thread.currentThread().getName() + txt);

    return flightPlan;
  }

  @Override public synchronized void releaseWrite()
  {
    writers--;
    notifyAll();
    String txt =
        " FINISHED WRITING (readers" + readers + ", writers=" + writers + ")";
    System.out.println(Thread.currentThread().getName() + txt);
  }
}
