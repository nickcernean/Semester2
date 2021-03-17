package model;

import utility.observer.subject.UnnamedPropertyChangeSubject;

import java.beans.PropertyChangeListener;

public interface Model extends UnnamedPropertyChangeSubject
{
   String convert(String source) throws Exception;
    void addLog(String log);
  @Override void addListener(PropertyChangeListener listener);
  @Override void removeListener(PropertyChangeListener listener);
}
