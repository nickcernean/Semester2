public class Program implements Runnable
{
  private String program;
  private long maxFrequency;
  private String action;
  private int count;
  private static final long RUNTIME = 100000;

  public Program(String program, String action, int count)
  {
    this.maxFrequency = count;
    this.program = program;
    this.action = action;
    this.count = count;

  }

  private void normalOperation()
  {
    try
    {
      Thread.sleep(RUNTIME / count);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    while (maxFrequency<=count)
    {
      System.out.println(program + " wants to " + action);
      normalOperation();
      maxFrequency++;
    }
  }

}
