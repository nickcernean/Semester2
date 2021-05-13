package protection;

import model.Flight;
import model.FlightPlanRead;

import java.util.ArrayList;

public class FlightPlanReadProxy implements FlightPlanRead
{
  private FlightPlanRead realSubject;
  private boolean hasReadAccess;

  public FlightPlanReadProxy(FlightPlanRead flightPlanRead)
  {
    this.realSubject = flightPlanRead;
    this.hasReadAccess = true;
  }

  @Override public Flight getFlight(String id)
  {
    if (hasReadAccess)
    {
      return realSubject.getFlight(id);
    }
    else
    {
      throw new IllegalStateException("No read access");
    }
  }

  @Override public ArrayList<Flight> getFlights(String to)
  {
    if (hasReadAccess)
    {
      return realSubject.getFlights(to);
    }
    else
    {
      throw new IllegalStateException("No read access");
    }
  }

  public void restrictAccess()
  {
    hasReadAccess = false;
  }
}
