package server.viewmodel;

import javafx.application.Platform;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import server.model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;

public class LogViewModel implements PropertyChangeListener
{
  private Model model;
  private ObservableList<String> logs;
  private StringProperty inputField;

  public LogViewModel(Model model)
  {
    this.model = model;
    this.model.addListener(this);
    this.logs = FXCollections.observableArrayList();
  }

  public ObservableList<String> getLogs()
  {
    return logs;
  }

  public void clear()
  {
    logs.setAll("");
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      logs.add(0, evt.getNewValue() + "");
    });
  }

  public void setMessage()
  {
    model.addMessage(getMessageProperty().get());
  }

  public StringProperty getMessageProperty()
  {
    return inputField;
  }
}
