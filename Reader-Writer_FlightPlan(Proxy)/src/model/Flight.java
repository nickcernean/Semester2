package model;

import java.time.LocalDateTime;

public class Flight
{
  private String id;
  private String from;
  private String to;
  private LocalDateTime departureTime;

  public Flight(String id, String from, String to, LocalDateTime time)
  {
    this.id = id;
    this.from = from;
    this.to = to;
    setDepartureTime(time);
  }

  public String getId()
  {
    return id;
  }

  public String getFrom()
  {
    return from;
  }

  public String getTo()
  {
    return to;
  }

  public LocalDateTime getDepartureTime()
  {
    return departureTime;
  }

  public void setDepartureTime(LocalDateTime departureTime)
  {
    this.departureTime = departureTime;
  }

  private Flight copy()
  {

    return new Flight(id, from, to, departureTime);
  }

  @Override public String toString()
  {
    return "Flight: " + id + "\n From: " + from + "\n To: " + to
        + "\n Departure time: " + departureTime;
  }
}
