

import java.util.ArrayList;
import java.util.List;

public class ProjectGlossary
{
  private List<GlossaryItem> items;
  private static ProjectGlossary instance;
  private static Object lock = new Object();

  private ProjectGlossary()
  {
    this.items = new ArrayList<>();
  }

  public static ProjectGlossary getInstance()
  {
    if(instance==null)
    {
      synchronized (lock)
      {
        if (instance==null)
        {
          instance=new ProjectGlossary();
        }
      }
    }
    return instance;
  }

  public int size()
  {
    return items.size();
  }

  public GlossaryItem[] getAll()
  {
    GlossaryItem[] array = new GlossaryItem[items.size()];
    return items.toArray(array);
  }

  public String getDefinition(String phrase)
  {
    for (GlossaryItem item : items)
    {
      if (item.getPhrase().equalsIgnoreCase(phrase))
      {
        return item.getDefinition();
      }
    }
    return null;
  }

  public void addItem(String phrase, String definition)
  {
    if (getDefinition(phrase) != null)
    {
      throw new IllegalStateException(
          "Glossary phrase already exist: " + phrase);
    }
    items.add(new GlossaryItem(phrase, definition));
  }

  public void removeItem(String phrase)
  {
    items.remove(new GlossaryItem(phrase, getDefinition(phrase)));
  }

  public String toString()
  {
    String s = "";
    if (items.size() == 0)
    {
      s += "[Empty]";
    }
    for (int i = 0; i < items.size(); i++)
    {
      s += "-" + items.get(i);
      if (i < items.size() - 1)
      {
        s += "\n";
      }
    }
    return s;
  }
}