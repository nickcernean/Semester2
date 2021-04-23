public class ConsoleLogger implements Logger
{
  public ConsoleLogger()
  {
  }

  @Override public void log(String txt)
  {
    System.out.println(txt);
  }
}
