public class LogLevelController implements Logger
{
  private ConsoleLogger consoleLogger;
  private String logLevel;

  public LogLevelController(String logLevel)
  {
    this.logLevel = logLevel;
    this.consoleLogger = new ConsoleLogger();
  }

  @Override public void log(String txt)
  {

    if (!(logLevel.equals("verbose") || logLevel.equals("sparse")))
    {
      logLevel = null;
    }
    else
    {
      if (logLevel.equals("verbose"))
      {

        consoleLogger.log(txt);
      }
      else if (logLevel.equals("sparse")&&txt.contains("error"))
      {
        consoleLogger.log(txt);
      }
    }
  }
}
