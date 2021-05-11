public class Main
{
  public static void main(String[] args)
  {
    Log log1 = Log.getLog("Key1");
    Log log2 = Log.getLog("Key2");
    Log log3 = Log.getLog("Key3");
    Log log4 = Log.getLog("Key3");


    log1.addLog("hello");
    log1.addLog("Aloha");
    log2.addLog("Heil Hydra");
    log3.addLog("Auf fiderzain");

    System.out.println("log1: "+log1);
    System.out.println("log2: "+log2);
    System.out.println("log3: "+log3);
    System.out.println("log4: "+log4);


  }
}
