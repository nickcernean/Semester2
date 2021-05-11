import java.util.Queue;
import java.util.concurrent.ArrayBlockingQueue;

public class CookieJar
{
  private int cookieCountWhenToBake;
  private int numberOfCookiesInTheOven;
  private int cookiePlateSize;
  private Queue<Cookie> cookies;

  public CookieJar(int jarSize, int cookieCountWhenToBake, int cookiePlateSize)
  {
    this.cookieCountWhenToBake = cookieCountWhenToBake;
    this.cookiePlateSize = cookiePlateSize;
    this.numberOfCookiesInTheOven = cookiePlateSize;
    this.cookies = new ArrayBlockingQueue<>(jarSize);
  }

  public void startBaking()
  {
    for (int i = 0; i <= numberOfCookiesInTheOven; i++)
    {
      cookies.add(new Cookie("Oreo"));
    }
  }

  public int finishedBaking()
  {
    return cookieCountWhenToBake;
  }

  public synchronized void eat()
  {
    cookies.remove();
  }
}
