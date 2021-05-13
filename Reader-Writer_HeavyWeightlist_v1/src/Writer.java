import java.util.Random;

public class Writer implements Runnable
{
  private ReadWriteAccess readWriteAccess;

  public Writer(ReadWriteAccess lock)
  {
    this.readWriteAccess = lock;
  }

  @Override public void run()
  {int value=new Random().nextInt(10-5)+5;
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
      readWriteAccess.acquireWrite().write(5);
      readWriteAccess.releaseWrite(1);
      i++;
    }
  }
}
