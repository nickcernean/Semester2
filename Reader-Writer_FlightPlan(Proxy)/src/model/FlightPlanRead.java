package model;

import java.util.ArrayList;

public interface FlightPlanRead
{
  Flight getFlight(String id);
  ArrayList<Flight> getFlights(String to);
}
