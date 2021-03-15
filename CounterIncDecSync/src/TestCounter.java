public class TestCounter
{
  public static void main(String[] args)
  {
    Counter counter = new Counter();

    CounterIncrementer inc = new CounterIncrementer(counter, 200);
    CounterIncrementer inc2 = new CounterIncrementer(counter, 200);

    CounterDecrementer dec = new CounterDecrementer(counter, 200);
    CounterDecrementer dec2 = new CounterDecrementer(counter, 200);

    Thread t1 = new Thread(inc, "Incrementator 1");
    Thread t2 = new Thread(inc2, "Incrementator 2");

    Thread t3 = new Thread(dec, "Decrementator 2");
    Thread t4 = new Thread(dec2, "Decrementator 1");

    t1.start();
    t2.start();
    t3.start();
    t4.start();
    try
    {
      t1.join();
      t2.join();
      t3.join();
      t4.join();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName()+":"+counter.getValue());

  }
}
