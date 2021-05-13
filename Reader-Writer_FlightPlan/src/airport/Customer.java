package airport;

import model.Flight;
import model.FlightPlanRead;

import java.util.ArrayList;

import static java.lang.Thread.sleep;

public class Customer implements Runnable
{
  private FlightPlanAccess access;

  public Customer(FlightPlanAccess access)
  {
    this.access = access;
  }

  private void doingSomething(String what, int minTime, int maxTime)
  {
    int time = (int) (Math.random() * (maxTime - minTime) + minTime);
    try
    {
      sleep(time);
    }
    catch (InterruptedException e)
    {
      e.printStackTrace();
    }
  }

  @Override public void run()
  {
    for (int i = 0; i < 10; i++)
    {
      doingSomething("Checking my money", 1000, 2000);
      FlightPlanRead flightPlan = access.acquireRead();
      ArrayList<Flight> flights = flightPlan.getFlights("London");
      String flightId = "";
      if (flights.size() > 0)
      {
        flightId = flights.get(0).getId();
      }
      Flight flight = flightPlan.getFlight(flightId);
      doingSomething("Reading", 1000, 2000);
      access.releaseRead();
    }
  }
}
