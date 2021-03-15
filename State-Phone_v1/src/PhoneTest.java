public class PhoneTest
{
  public static void main(String[] args)
  {

    Phone p1 = new Phone();

    p1.clickSoundButton();
    p1.receive("sound");

    p1.clickSoundButton();
    p1.receive("vibration");

    p1.clickSoundButton();
    p1.receive("silent");

    p1.clickSoundButton();
    p1.receive("sound");

  }
}
