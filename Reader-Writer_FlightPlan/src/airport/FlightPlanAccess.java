package airport;

import model.FlightPlanRead;
import model.FlightPlanWrite;

public interface FlightPlanAccess
{
  FlightPlanRead acquireRead();
  void releaseRead();
  FlightPlanWrite acquireWrite();
  void releaseWrite();

}
