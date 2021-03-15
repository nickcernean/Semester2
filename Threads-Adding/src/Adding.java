import java.util.ArrayList;

public class Adding implements Runnable
{
  private String id;
  private long sleepTime;
  private ArrayList<String> list;

  public Adding(ArrayList<String> list, String id, long sleepTime)
  {
    this.id = id;
    this.list = list;
    this.sleepTime = sleepTime;
  }

  @Override public void run()
  {
    update();
    System.out.println(list.toString());
  }

  public synchronized void update()
  {
    for (int i = 0; i < 3; i++)
    {

      try
      {
        Thread.sleep(sleepTime);
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
      list.add(id + "#" + 1);
      System.out
          .println(Thread.currentThread().getName() + " " + list.toString());
    }
  }
}