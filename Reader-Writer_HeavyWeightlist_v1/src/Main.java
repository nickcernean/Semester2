public class Main
{
  public static void main(String[] args)
  {
    HeavyWeightList heavyWeightList = new HeavyWeightList(3, 5);
    ReadWriteAccess readWriteAccess = new ListAccess(heavyWeightList);

    Thread[] readers = new Thread[24];
    for (int i = 0; i < readers.length; i++)
    {
      Reader reader = new Reader(readWriteAccess);
      readers[i] = new Thread(reader, "Reader " + i);
      readers[i].start();
    }

    Thread[] writers = new Thread[2];
    for (int i = 0; i < writers.length; i++)

    {
      Writer writer = new Writer(readWriteAccess);
      writers[i] = new Thread(writer, "Writer " + i);
      writers[i].start();
    }
  }
}
