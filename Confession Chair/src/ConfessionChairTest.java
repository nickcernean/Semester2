public class ConfessionChairTest
{
  public static void main(String[] args)
  {
    ConfessionChair confessionChair = new ConfessionChair();
    Churchgoer churchgoer = new Churchgoer(confessionChair);
    Churchgoer churchgoer1 = new Churchgoer(confessionChair);
    Churchgoer churchgoer2 = new Churchgoer(confessionChair);

    Thread t1 = new Thread(churchgoer,"Thread 1");
    Thread t2 = new Thread(churchgoer1,"Thread 2");
    Thread t3 = new Thread(churchgoer2,"Thread 3");

    t1.start();
    t2.start();
    t3.start();

    try
    {
      t1.join();
      t2.join();
      t3.join();
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
    System.out.println("Finished");

  }

}
