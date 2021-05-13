import java.util.ArrayDeque;
import java.util.Queue;

public class Bridge implements Lane
{
  private int writers;
  private int readers;
  private Queue<Thread> queue;

  public Bridge()
  {
    readers = 0;
    writers = 0;
    this.queue = new ArrayDeque<>();
  }

  @Override public synchronized void enterFromTheLeft()
  {
    queue.offer(Thread.currentThread());
    while (queue.peek() != Thread.currentThread())
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
    while (writers > 0)
    {
      try
      {
        System.out.println( Thread.currentThread().getName()+"  waiting to enter on the bridge!");
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    readers++;
    System.out.println(Thread.currentThread().getName()+" entered from left on the bridge");
    queue.remove();
    notifyAll();
  }

  @Override public synchronized void exitToTheRight()
  {
    readers--;
    System.out.println(Thread.currentThread().getName()+" exit to the right from the bridge");
    if (readers == 0)
    {
      notifyAll();
    }

  }

  @Override public synchronized void enterFromTheRight()
  {
    queue.offer(Thread.currentThread());
    while (queue.peek() != Thread.currentThread())
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
    while (readers > 0 || writers > 0)
    {
      try
      {
        System.out.println( Thread.currentThread().getName()+"  waiting to enter on the bridge!");
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }

    }
    writers++;
    System.out.println(Thread.currentThread().getName()+"  entered from right on the bridge");

    queue.remove();
  }

  @Override public synchronized void exitToTheLeft()
  {
    writers--;

    System.out.println(Thread.currentThread().getName()+" exit to the left from the bridge");
    notifyAll();
  }
}
