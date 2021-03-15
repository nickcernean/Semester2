public class Counter
{
  private long value;

  public Counter()
  {
    value = 0;
  }

  public synchronized void increment()
  {
    value++;
  }

  public synchronized long getValue()
  {
    return value;
  }
}
