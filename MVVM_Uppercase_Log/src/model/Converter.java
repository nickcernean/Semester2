package model;

import java.util.ArrayList;

public class Converter
{
  private ArrayList<String> list;

  public Converter()
  {
    this.list = new ArrayList<>();
  }

  public String toUpperCase(String txt)
  {
    if (txt == null || txt.isEmpty())
    {
      throw new IllegalArgumentException("Request is empty" );
    }
    return txt.toUpperCase();
  }

  public void addLog(String txt)
  {
    list.add(txt);
  }

  public int getLogSize()
  {
    return list.size();
  }
}
