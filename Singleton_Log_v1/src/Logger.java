import java.io.*;

public class Logger
{
  private File file;
  private static Logger logger;

  private Logger()
  {
    file = new File("LogFile.txt");
  }

  public static Logger getInstance()
  {
    if (logger == null)
    {
      logger = new Logger();
    }
    return logger;
  }

  public void log(String txt)
  {
    try
    {
      Writer out=new BufferedWriter(new FileWriter(file,true));
      out.append(txt);
      out.flush();
      out.close();
    }
    catch (IOException e)
    {
      e.printStackTrace();
    }

  }
}
