import utility.collection.BoundedArrayQueue;
import utility.collection.QueueADT;

public class SynchronizedQueue<T> implements Buffer<T>
{
  private QueueADT<T> queue;

  public SynchronizedQueue(int capacity)
  {
    queue = new BoundedArrayQueue<>(capacity);
  }

  @Override public synchronized void put(T element)
  {
    queue.enqueue(element);
  }

  @Override public synchronized T take()
  {
    return queue.dequeue();
  }

  @Override public synchronized T look()
  {
    return queue.first();
  }

  @Override public synchronized boolean isEmpty()
  {
    return queue.isEmpty();
  }

  @Override public synchronized boolean isFull()
  {
    return queue.isFull();
  }

  @Override public synchronized int size()
  {
    return queue.size();
  }

  @Override public String toString()
  {
    return "SynchronizedQueue: " + queue;
  }
}
