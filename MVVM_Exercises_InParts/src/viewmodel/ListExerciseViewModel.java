package viewmodel;

import javafx.application.Platform;
import javafx.beans.property.ObjectProperty;
import javafx.beans.property.SimpleObjectProperty;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import model.Exercise;
import model.Model;

import java.beans.PropertyChangeEvent;
import java.beans.PropertyChangeListener;
import java.util.ArrayList;

public class ListExerciseViewModel implements PropertyChangeListener
{
  private ObservableList<SimpleExerciseViewModel> list;
  private ObjectProperty<SimpleExerciseViewModel> selectedExerciseProperty;
  private StringProperty errorProperty;
  private Model model;
  private ViewState viewState;

  public ListExerciseViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.model.addListener(this);
    this.selectedExerciseProperty = new SimpleObjectProperty<>();
    this.errorProperty = new SimpleStringProperty();
    this.list = FXCollections.observableArrayList();
    loadFromModel();
  }

  private void loadFromModel()
  {
    this.list.clear();
    ArrayList<Exercise> exercises = model.getAllExercises();
    for (int i = 0; i < exercises.size(); i++)
    {
      list.add(new SimpleExerciseViewModel(exercises.get(i)));
    }
  }

  public void clear()
  {
    errorProperty.set(null);
  }

  public void addEdit()
  {
    viewState.setRemove(false);
    if (selectedExerciseProperty.get() != null)
    {
      viewState
          .setNumber(selectedExerciseProperty.get().getNumberProperty().get());
    }
    else
    {
      viewState.removeNumber();
    }
  }

  public boolean remove()
  {
    if (selectedExerciseProperty.get() != null)
    {
      viewState
          .setNumber(selectedExerciseProperty.get().getNumberProperty().get());
      viewState.setRemove(true);
      return true;
    }
    else
    {
      viewState.setRemove(false);
      errorProperty.set("No selection");
      return false;
    }
  }

  public ObservableList<SimpleExerciseViewModel> getAll()
  {
    return list;
  }

  public void setSelected(SimpleExerciseViewModel vm)
  {
    selectedExerciseProperty.set(vm);
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  private void addSimpleExercise(Exercise exercise)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (exercise.getNumber().compareTo(list.get(i).getNumberProperty().get())
          < 0)
      {
        list.add(i, new SimpleExerciseViewModel(exercise));
        return;
      }
    }
    list.add(new SimpleExerciseViewModel(exercise));
  }

  private void removeSimpleExercise(String number)
  {
    for (int i = 0; i < list.size(); i++)
    {
      if (list.get(i).getNumberProperty().get().equals(number))
      {
        list.remove(i);
        break;
      }
    }
  }

  @Override public void propertyChange(PropertyChangeEvent evt)
  {
    Platform.runLater(() -> {
      switch (evt.getPropertyName())
      {
        case "Add":
          addSimpleExercise((Exercise) evt.getNewValue());
          break;
        case "Remove":
          removeSimpleExercise((String) evt.getOldValue());
        case "Edit":
          removeSimpleExercise((String) evt.getOldValue());
          addSimpleExercise((Exercise) evt.getNewValue());
          break;
      }
      });
  }
}
