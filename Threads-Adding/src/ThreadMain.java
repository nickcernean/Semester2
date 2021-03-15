import java.util.ArrayList;

public class ThreadMain
{
  public static void main(String[] args)
  {
    ArrayList<String> list = new ArrayList<>();
    Adding add = new Adding(list, "A", 3000);
    Adding add2 = new Adding(list, "B", 3000);
    Adding add3 = new Adding(list, "C", 3000);


    Thread t1 = new Thread(add,"t1");

    Thread t2 = new Thread(add2,"t2");
    Thread t3 = new Thread(add3,"t3");


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

    for (int i = 0; i < list.size(); i++)
    {
      System.out.println(list.get(i));
    }
    System.out.println(list.size());
  }
}
