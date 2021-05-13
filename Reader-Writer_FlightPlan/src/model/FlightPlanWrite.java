package model;

import model.FlightPlanRead;

import java.time.LocalDateTime;

public interface FlightPlanWrite extends FlightPlanRead
{
  void updateFlightDeparture(String flightId, LocalDateTime time);
  void removeFlightsBefore(LocalDateTime time);

}
