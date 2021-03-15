import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class FotballFan implements PropertyChangeListener
{
  private String myName;
  private String myTeam;
  private FootballGame game;

  public FotballFan(String myName, FootballGame game, boolean homeTeamFan)
  {
    this.myName = myName;
    this.game = game;
    if (homeTeamFan)
    {
      this.myTeam = game.getHomeTeam();
    }
    else
    {
      this.myTeam = game.getAwayTeam();
    }
    cheer(myTeam);
  }

  public void cheer(String team)
  {
    System.out.println(myName + "> Come on " + team);
  }

  public void reactOnGoal(String team)
  {
    if (team.equals(myTeam))
    {
      System.out.println(myName + "> Jubiiii (" + team + " scored)");
    }
    else
    {
      System.out.println(myName + "> Boooo (" + team + " scored)");
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
      reactOnGoal(myTeam);
  }
}