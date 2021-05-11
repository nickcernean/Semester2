import java.util.ArrayList;
import java.util.List;

public class ToiletBuilding implements PublicToilet
{
  private Person person;
  private CleaningPerson cleaningPerson;
  private List<Object> cabins;

  public ToiletBuilding(int noOfCabins)
  {
    this.cabins = new ArrayList<>(noOfCabins);
    this.person = new Person(this);
    this.cleaningPerson = new CleaningPerson(this);
  }

  private synchronized int personInCabinCounter()
  {
    int count = 0;
    for (int i = 0; i < cabins.size(); i++)
    {
      if (cabins.contains(person))
      {
        count++;
      }
    }
    return count;
  }

  private synchronized int cleaningPersonCounter()
  {
    int count = 0;
    for (int i = 0; i < cabins.size(); i++)
    {
      if (cabins.contains(cleaningPerson))
      {
        count++;
      }
    }
    return count;
  }

  @Override synchronized public void stepIntoCabin()
  {
    while (cleaningPersonCounter() > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    cabins.add(person);
  }

  @Override public synchronized void leaveCabin()
  {
    cabins.remove(person);
    if (cleaningPersonCounter() == 0)
    {
      notify();
    }
  }

  @Override public synchronized void startCleaning()
  {
    while (personInCabinCounter() > 0 || cleaningPersonCounter() > 0)
    {
      try
      {
        wait();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
    cabins.add(cleaningPerson);
  }

  @Override public synchronized void endCleaning()
  {
    cabins.remove(cleaningPerson);
    notifyAll();
  }
}
