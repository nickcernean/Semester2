public class DoorTest
{
  public static void main(String[] args)
  {
    Door door1=new Door();
    System.out.println("Startup, the door is "+door1.status());

    door1.setState(new DoorStayOpen());
    door1.click();
    System.out.println(door1.status());
    door1.click();
    System.out.println(door1.status());
    door1.click();
    System.out.println(door1.status());

  }
}
