package model;

import java.time.LocalDateTime;
import java.util.ArrayList;

public class FlightPlan implements FlightPlanWrite
{
  private ArrayList<Flight> flights;

  public FlightPlan()
  {
    this.flights = new ArrayList<>();
    createDummyData();
  }

  private void createDummyData()
  {
  }

  @Override public void updateFlightDeparture(String flightId,
      LocalDateTime time)
  {

    for (int i = 0; i < flights.size(); i++)
    {
      if (flights.get(i).getId().equals(flightId))
      {
        flights.get(i).setDepartureTime(time);
      }
    }
  }

  @Override public void removeFlightsBefore(LocalDateTime time)
  {
    for (int i = 0; i < flights.size(); i++)
    {
      if (flights.get(i).getDepartureTime().isBefore(time))
      {
        flights.remove(i);
      }
    }
  }

  @Override public Flight getFlight(String id)
  {
    for (int i = 0; i < flights.size(); i++)
    {
      if (flights.get(i).getId().equals(id))
      {
        return flights.get(i);
      }
    }
    return null;
  }

  @Override public ArrayList<Flight> getFlights(String to)
  {
    ArrayList<Flight> flightsTo = new ArrayList<>();
    for (int i = 0; i < flights.size(); i++)
    {
      if (flights.get(i).getTo().equals(to))
      {
        flightsTo.add(flights.get(i));
      }
    }
    return flightsTo;
  }
}
