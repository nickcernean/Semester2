public class BurgerBarTest
{
  public static void main(String[] args)
  {
    Burgerbar burgerbar = new Burgerbar(10);

    Thread[] customerThreads = new Thread[5];
    for (int i = 0; i < customerThreads.length; i++)
    {
      BurgerBarCustomer customer = new BurgerBarCustomer("Customer" + (i + 1),
          burgerbar, 3);
      customerThreads[i] = new Thread(customer);
      customerThreads[i].start();
    }

    Thread[] employeeThreads = new Thread[2];
    for (int i = 0; i < employeeThreads.length; i++)
    {
      BurgerBarEmployee employee = new BurgerBarEmployee("Employee" + (i + 1),
          burgerbar);
      employeeThreads[i] = new Thread(employee);
      employeeThreads[i].setDaemon(true);
      employeeThreads[i].start();
    }

    for (int i = 0; i < customerThreads.length; i++)
    {
      try
      {
        customerThreads[i].join();
      }
      catch (InterruptedException e)
      {
        e.printStackTrace();
      }
    }
  }
}
