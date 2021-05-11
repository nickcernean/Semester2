import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;

public class Log
{
  private ArrayList<LogLine> logLines;
  private static Log log;
  private static Object lock = new Object();

  private Log()
  {

  }

  public void addLog(String text)
  {
    logLines = new ArrayList<>();

    logLines.add(new LogLine(text));
    //    addToFile(new LogLine(text));
    System.out.println(text);
  }

  public static Log getAll()
  {
    if (log == null)
    {
      synchronized (lock)
      {
        if (log == null)
        {
          log = new Log();
        }
      }
    }
    return log;
  }

  private void addToFile(LogLine log)
  {
    if (log == null)
    {
      return;
    }
    BufferedWriter out = null;
    try
    {
      String filename = "Log-" + log.getDateTime().getSortableDate() + ".txt";
      out = new BufferedWriter(new FileWriter(filename, true));
      out.write(log + "\n");
    }
    catch (Exception e)
    {
      e.printStackTrace();
    }
    finally
    {
      try
      {
        out.close();
      }
      catch (Exception e)
      {
        e.printStackTrace();
      }
    }
  }

  @Override public String toString()
  {
    return "Log: " + "\n" + logLines;
  }
}
