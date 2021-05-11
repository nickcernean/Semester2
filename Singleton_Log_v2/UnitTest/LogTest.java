import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

class LogTest
{
  @Test void testGetAllLogMethod()
  {
   Log logger1 = Log.getAll();
   Log logger2 = Log.getAll();

   logger1.addLog("helo");
   logger1.addLog("Aloha");
   logger2.addLog("hello");
   System.out.println(logger1);
   System.out.println(logger2);

   assertEquals(logger1.toString(),logger2.toString());
  }

}