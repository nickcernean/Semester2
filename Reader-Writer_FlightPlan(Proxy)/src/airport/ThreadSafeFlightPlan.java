package airport;

import model.FlightPlan;
import model.FlightPlanRead;
import model.FlightPlanWrite;
import protection.FlightPlanReadProxy;
import protection.FlightPlanWriteProxy;

import java.util.HashMap;
import java.util.Map;

public class ThreadSafeFlightPlan implements FlightPlanAccess
{
  private FlightPlan flightPlan;
  private int readers;
  private int writers;
  private int waitingWriter;// Writer preference
  private Map<Thread, FlightPlanReadProxy> proxiesRead;
  private Map<Thread, FlightPlanWriteProxy> proxiesWrite;

  public ThreadSafeFlightPlan(FlightPlan flightPlan)
  {
    this.flightPlan = flightPlan;
    this.readers = 0;
    this.writers = 0;
    this.waitingWriter = 0;// Writer preference
    this.proxiesRead = new HashMap<>();
    this.proxiesWrite = new HashMap<>();
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

    FlightPlanReadProxy proxy = new FlightPlanReadProxy(flightPlan);
    proxiesRead.put(Thread.currentThread(), proxy);

    return proxy;
  }

  @Override public synchronized void releaseRead()
  {
    readers--;
    if (readers == 0)
    {
      notifyAll();// Writer preference
    }
    FlightPlanReadProxy proxy = proxiesRead.get(Thread.currentThread());
    if (proxy != null)
    {
      proxy.restrictAccess();
      proxiesRead.remove(Thread.currentThread());
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

    FlightPlanWriteProxy proxy = new FlightPlanWriteProxy(flightPlan);
    proxiesWrite.put(Thread.currentThread(), proxy);


    return flightPlan;
  }

  @Override public synchronized void releaseWrite()
  {
    writers--;
    notifyAll();

    FlightPlanWriteProxy proxy = proxiesWrite.get(Thread.currentThread());
    if (proxy != null)
    {
      proxy.restrictAccess();
      proxiesWrite.remove(Thread.currentThread());
    }
    String txt =
        " FINISHED WRITING (readers" + readers + ", writers=" + writers + ")";
    System.out.println(Thread.currentThread().getName() + txt);
  }
}
