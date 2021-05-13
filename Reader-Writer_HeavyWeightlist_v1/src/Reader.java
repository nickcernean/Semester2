import java.util.Random;

public class Reader implements Runnable
{
  private ReadWriteAccess readWriteAccess;

  public Reader(ReadWriteAccess lock)
  {
    this.readWriteAccess = lock;
  }

  @Override public void run()
  { int value=new Random().nextInt(10-1)+1;
    int i = 0;
    while (i <= 2)
    {
//      try
//      {
//        wait(value);
//      }
//      catch (InterruptedException e)
//      {
//        e.printStackTrace();
//      }
      readWriteAccess.acquireRead().read();
      readWriteAccess.releaseRead();
      i++;
    }
  }
}
