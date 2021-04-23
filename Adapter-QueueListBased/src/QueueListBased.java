import utility.collection.CircularLinkedList;
import utility.collection.ListADT;
import utility.collection.QueueADT;

public class QueueListBased<T> implements QueueADT<T>
{
  private ListADT<T> list;

  public QueueListBased()
  {
    this.list = new CircularLinkedList<T>();
    ;
  }

  @Override public synchronized void enqueue(T element)
  {
    list.add(element);
  }

  @Override public synchronized T dequeue()
  {
    return list.remove(0);
  }

  @Override public synchronized T first()
  {
    return list.get(0);
  }

  @Override public synchronized int indexOf(T element)
  {
    return list.indexOf(element);
  }

  @Override public synchronized boolean isEmpty()
  {
    return list.isEmpty();
  }

  @Override public synchronized boolean isFull()
  {
    return list.isFull();
  }

  @Override public synchronized int size()
  {
    return list.size();
  }

  @Override public synchronized int capacity()
  {
    if (list.isFull())
    {
      return Integer.MAX_VALUE;
    }
    return -1;
  }

  @Override public String toString()
  {
    return "QueueListBased: "+ list;
  }
}
