import java.io.BufferedWriter;
import java.io.FileWriter;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.Map;

public class Log
{
  private ArrayList<LogLine> logLines;
  private static Map<String, Log> allLogs = new HashMap<>();
  private String filename;

  private Log()
  {
    this.filename = null;
    logLines = new ArrayList<>();
  }

  public void addLog(String text)
  {


    logLines.add(new LogLine(text));
//    addToFile(new LogLine(text));
    System.out.println(text);
  }

  public static Log getLog(String key)
  {
    Log instance = allLogs.get(key);
    if (instance == null)
    {
      synchronized (allLogs)
      {
        instance = allLogs.get(key);
        if (instance == null)
        {
          instance = new Log();
          allLogs.put(key, instance);
        }
      }
    }
    return instance;
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
      filename = "Log-" + log.getDateTime().getSortableDate() + ".txt";
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
