import utility.collection.BoundedArrayQueue;
import utility.collection.QueueADT;

public class BlockingQueue<T> implements Buffer<T>
{
  private QueueADT<T> queueADT;

  public BlockingQueue(int capacity)
  {
    this.queueADT = new BoundedArrayQueue<>(capacity);
  }

  @Override public synchronized void put(T element)
  {
    while (queueADT.isFull())
    {
      try
      {
        System.out.println("Waiting...to put");
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    try
    {
      queueADT.enqueue(element);
      System.out.println("Element added>>");
    }
    catch (IllegalArgumentException e)
    {
      System.out.println("The added element is null!");
    }
    notifyAll();
  }

  @Override public synchronized T take()
  {
    while (queueADT.isEmpty())
    {
      try
      {
        System.out.println("Waiting...to take");
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
      queueADT.dequeue();
      System.out.println("Element taken>>");
      notifyAll();
    }
    return null;
  }

  @Override public synchronized T look()
  {
    if (!queueADT.isEmpty())
    {
      return queueADT.first();
    }
    return null;
  }

  @Override public synchronized boolean isEmpty()
  {
    return queueADT.isEmpty();
  }

  @Override public synchronized boolean isFull()
  {
    return queueADT.isFull();
  }

  @Override public synchronized int size()
  {
    return queueADT.size();
  }

  @Override public synchronized String toString()
  {
    return super.toString();
  }
}
