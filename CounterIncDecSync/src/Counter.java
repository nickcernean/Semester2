public class Counter
{
  private long value;

  public Counter()
  {
    this.value = 0;
  }

  public synchronized void increment()
  {
    value++;

  }

  public synchronized void decrement()
  {
    value--;
  }

  public synchronized long getValue()
  {
    return value;
  }
}
