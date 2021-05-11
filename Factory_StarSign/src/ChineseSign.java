import java.util.Arrays;

public class ChineseSign extends StarSign
{
  private static final String[] NAMES = {"Dragon", "Snake", "Horse", "Goat",
      "Monkey", "Rooster", "Dog", "Pig", "Rat", "Ox", "Tiger", "Rabbit",};
  private int year;

  public ChineseSign(int year)
  {
    super(getName(year));
    this.year = year;
  }

  public String getYears(int count, int fromYear)
  {

    return getName(fromYear + count);

  }

  private static String getName(int year)
  {
    int count = (year - 2000) % 12;

    switch (count)
    {
      case 0:
        return NAMES[0];
      case 1:
        return NAMES[1];
      case 2:
        return NAMES[2];
      case 3:
        return NAMES[3];
      case 4:
        return NAMES[4];
      case 5:
        return NAMES[5];
      case 6:
        return NAMES[6];
      case 7:
        return NAMES[7];
      case 8:
        return NAMES[8];
      case 9:
        return NAMES[9];
      case 10:
        return NAMES[10];
      default:
        return NAMES[11];
    }

  }

  @Override public String getDescription()
  {
    return Arrays.toString(NAMES);
  }
}
