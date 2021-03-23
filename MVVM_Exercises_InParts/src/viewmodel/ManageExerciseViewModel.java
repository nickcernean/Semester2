package viewmodel;

import javafx.beans.property.*;
import model.Exercise;
import model.Model;

public class ManageExerciseViewModel
{
  private SimpleStringProperty errorProperty;
  private SimpleStringProperty headerProperty;
  private SimpleStringProperty topicProperty;
  private IntegerProperty numberProperty;
  private IntegerProperty sessionProperty;
  private ObjectProperty<Boolean> completedProperty;
  private ObjectProperty<Boolean> editableProperty;
  private Model model;
  private ViewState viewState;

  public ManageExerciseViewModel(Model model, ViewState viewState)
  {
    this.model = model;
    this.viewState = viewState;
    this.errorProperty = new SimpleStringProperty();
    this.headerProperty = new SimpleStringProperty("Add exercise");
    this.topicProperty = new SimpleStringProperty();
    this.numberProperty = new SimpleIntegerProperty();
    this.sessionProperty = new SimpleIntegerProperty();
    this.completedProperty = new SimpleObjectProperty(false);
    this.editableProperty = new SimpleObjectProperty(true);

  }

  public void reset()
  {
    errorProperty.set(null);
    if (viewState.getNumber() != null)
    {
      Exercise exercise = model.getExercise(viewState.getNumber());
      numberProperty.set(exercise.getExerciseNumber());
      sessionProperty.set(exercise.getSessionNumber());
      topicProperty.set(exercise.getTopic());
      completedProperty.set(exercise.isCompleted());
      if (viewState.isRemove())
      {
        headerProperty.set("Remove exercise");
        editableProperty.set(false);

      }
      else
      {
        headerProperty.set("Edit exercise");
        editableProperty.set(true);

      }
    }
    else
    {
      numberProperty.set(0);
      sessionProperty.set(0);
      topicProperty.set(null);
      completedProperty.set(false);
      headerProperty.set("Add exercise");
      editableProperty.set(true);
    }

  }

  private Exercise createExerciseObject()
  {
    Exercise exercise = new Exercise(sessionProperty.get(),
        numberProperty.get(), topicProperty.get());
    exercise.setCompleted(completedProperty.get());
    return exercise;
  }

  public boolean accept()
  {

    try
    {
      if (headerProperty.get().contains("Add"))
      {
        model.addExercise(createExerciseObject());
      }
      else if (headerProperty.get().contains("Edit"))
      {
        model.editExercise(viewState.getNumber(), createExerciseObject());
      }
      else if (headerProperty.get().contains("Remove"))
      {
        model.removeExercise(createExerciseObject().getNumber());
      }
      return true;
    }
    catch (Exception e)
    {
      errorProperty.set(e.getMessage());
      return false;
    }
  }

  public StringProperty getErrorProperty()
  {
    return errorProperty;
  }

  public StringProperty getHeaderProperty()
  {
    return headerProperty;
  }

  public StringProperty getTopicProperty()
  {
    return topicProperty;
  }

  public IntegerProperty getNumberProperty()
  {
    return numberProperty;
  }

  public IntegerProperty getSessionProperty()
  {
    return sessionProperty;
  }

  public ObjectProperty<Boolean> getCompletedProperty()
  {
    return completedProperty;
  }

  public ObjectProperty<Boolean> getEditableProperty()
  {
    return editableProperty;
  }
}
