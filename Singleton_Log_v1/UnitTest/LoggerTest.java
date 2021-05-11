import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

public class LoggerTest
{

  @Test public void testSingletoneOneInstance()
  {
    Logger logger1 = Logger.getInstance();
    Logger logger2 = Logger.getInstance();

    System.out.println("ref1=" + logger1);
    System.out.println("ref2=" + logger2);
    assertEquals(logger1.toString(), logger2.toString());

  }

}