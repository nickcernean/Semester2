public class CounterDecrementer implements Runnable
{
  private Counter counter;
  private int updates;

  public CounterDecrementer(Counter counter, int updates)
  {
    this.counter = counter;
    this.updates = updates;
  }

  @Override public void run()
  {
    for (int i = 0; i < updates; i++)
    {
      counter.decrement();
    }
    System.out.println(Thread.currentThread().getName()+":"+counter.getValue());
  }
}