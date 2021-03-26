package client.viewmodel;

import client.model.Model;
import javafx.beans.property.SimpleStringProperty;
import javafx.beans.property.StringProperty;

public class ConvertViewModel
{
  private StringProperty request;
  private StringProperty reply;
  private StringProperty error;
  private Model model;

  public ConvertViewModel(Model model)
  {
    this.model = model;
    this.request = new SimpleStringProperty();
    this.reply = new SimpleStringProperty();
    this.error = new SimpleStringProperty();
  }

  public void convert()
  {
    try
    {
        String replyText = model.convert(request.get());
        reply.set(replyText);
        error.set(null);
        request.set(null);
    }
    catch (Exception e)
    {
      error.set(e.getMessage());
    }
  }

  public StringProperty errorProperty()
  {
    return error;
  }

  public StringProperty requestProperty()
  {
    return request;
  }

  public StringProperty replyProperty()
  {
    return reply;
  }
}
