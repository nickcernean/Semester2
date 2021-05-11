public class Main
{
  public static void main(String[] args)
  {
    PublicToilet publicToilet = new ToiletBuilding(5);
    Thread[] persons = new Thread[15];
    for (int i = 0; i < persons.length; i++)
    {
      Person person = new Person(publicToilet);
      persons[i] = new Thread(person, "Person " + i);
      persons[i].start();
    }

    Thread[] cleaner = new Thread[3];
    for (int i = 0; i < cleaner.length; i++)
    {
      CleaningPerson cleaningPerson = new CleaningPerson(publicToilet);
      cleaner[i] = new Thread(cleaningPerson, "Cleaner " + i);
      cleaner[i].start();
    }
  }
}
