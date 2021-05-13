package protection;

import model.Flight;
import model.FlightPlanWrite;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightPlanWriteProxy implements FlightPlanWrite
{
  private FlightPlanWrite realSubject;
  private boolean hasReadAccess;

  public FlightPlanWriteProxy(FlightPlanWrite flightPlanWrite)
  {
    this.realSubject = flightPlanWrite;
    this.hasReadAccess = true;
  }

  public void restrictAccess()
  {
    hasReadAccess = false;
  }

  @Override public void updateFlightDeparture(String flightId,
      LocalDateTime time)
  {
    if (hasReadAccess)
    {
      realSubject.updateFlightDeparture(flightId, time);
    }
    else
    {
      throw new IllegalStateException("No write access");
    }
  }

  @Override public void removeFlightsBefore(LocalDateTime time)
  {
    if (hasReadAccess)
    {
      realSubject.removeFlightsBefore(time);
    }
    else
    {
      throw new IllegalStateException("No write access");
    }
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
}
