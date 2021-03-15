package external;

import view.TemperatureViewController;

import java.time.LocalTime;
import java.time.format.DateTimeFormatter;

public class RunnableClock implements Runnable
{
  private DateTimeFormatter timeFormatter;
  private TemperatureViewController temperatureViewController;


  public RunnableClock(TemperatureViewController temperatureViewController)
  {

    this.timeFormatter = DateTimeFormatter.ofPattern("HH:mm:ss");
    this.temperatureViewController = temperatureViewController;
  }

  @Override public void run()
  {
    while (true)
    {
      LocalTime time = LocalTime.now();
      String timeString = time.format(timeFormatter);
      sleep();
      System.out.println(timeString);
      temperatureViewController.showTime(timeString);
    }
  }

  private void sleep()
  {
    try
    {
      Thread.sleep(1000);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }
}
