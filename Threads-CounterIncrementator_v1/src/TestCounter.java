public class TestCounter
{
  public static void main(String[] args)
  {
     Counter counter=new Counter();

     CounterIncrementer inc=new CounterIncrementer(counter,200000);
     CounterIncrementer inc2=new CounterIncrementer(counter,200000);

    Thread t1=new Thread(inc,"Incrementor one");
    Thread t2=new Thread(inc2,"Incrementor two");
    t1.start();
    t2.start();
    try
    {
      t1.join();
      t2.join();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    System.out.println(Thread.currentThread().getName()+":"+counter.getValue());

  }
}
