import java.util.Arrays;
import java.util.Date;

public class ZodiacSign extends StarSign
{
  private static final String[] NAMES = {"Aries", "Taurus", "Gemini", "Cancer",
      "Leo", "Virgo", "Libra", "Scorpius", "Sagittarius", "Capricornus",
      "Aquarius", "Pisces"};
  private Date date;

  public ZodiacSign(String name)
  {
    super(convertName(name));
    setDateInterval();
  }

  public Date getDateInterval()
  {
    return date;
  }

  private void setDateInterval()
  {
    this.date = new Date();
  }

  private static String convertName(String name)
  {
    for (int i = 0; i <= NAMES.length; i++)
    {
      if (name.contains(NAMES[i]))
      {
        return NAMES[i];
      }
    }
    return null;
  }

  @Override public String getDescription()
  {
    return Arrays.toString(NAMES);
  }

}
