import java.beans.PropertyChangeListener;
import java.beans.PropertyChangeSupport;

public class FootballGame implements PropertyChangeSubject
{
  private String homeTeam;
  private String awayTeam;
  private int homeTeamGoal;
  private int awayTeamGoal;
  PropertyChangeSupport support;

  public FootballGame(String homeTeam, String awayTeam)
  {
    this.homeTeam = homeTeam;
    this.awayTeam = awayTeam;
    this.homeTeamGoal = 0;
    this.awayTeamGoal = 0;
    this.support = new PropertyChangeSupport(this);
  }

  public String getHomeTeam()
  {
    return homeTeam;
  }

  public String getAwayTeam()
  {
    return awayTeam;
  }

  public void scoreGoal(String team)
  {
    if (team.equals(homeTeam))
    {
      int oldvalue = homeTeamGoal;
      homeTeamGoal++;
      support.firePropertyChange("Home", oldvalue, homeTeamGoal);
      support.firePropertyChange("SCORE", oldvalue, homeTeamGoal);

    }
    else if (team.equals(awayTeam))
    {
      int oldvalue = awayTeamGoal;
      awayTeamGoal++;
      support.firePropertyChange("Away", oldvalue, awayTeamGoal);
      support.firePropertyChange("SCORE", oldvalue, homeTeamGoal);
    }
  }

  public String getScore()
  {
    return homeTeamGoal + " - " + awayTeamGoal;
  }

  public String endGame()
  {
    return getScore();
  }

  @Override public void addProperrtyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null || "".equals(eventName))
    {
      addPropertyChangeListener(listener);
    }
    else
    {
      support.addPropertyChangeListener(eventName, listener);
    }
  }

  @Override public void addPropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.addPropertyChangeListener(listener);
  }

  @Override public void removeProperrtyChangeListener(String eventName,
      PropertyChangeListener listener)
  {
    if (eventName == null || "".equals(eventName))
    {
      removePropertyChangeListener(listener);
    }
    else
    {
      support.removePropertyChangeListener(eventName, listener);
    }
  }

  @Override public void removePropertyChangeListener(
      PropertyChangeListener listener)
  {
    support.removePropertyChangeListener(listener);
  }
}