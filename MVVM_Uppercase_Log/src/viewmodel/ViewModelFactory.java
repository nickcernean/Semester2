package viewmodel;

import model.Model;

public class ViewModelFactory
{
  private ConvertViewModel convertViewModel;
  private LogViewModel logViewModel;

  public ViewModelFactory(Model model)
  {
    this.convertViewModel = new ConvertViewModel(model);
    this.logViewModel = new LogViewModel(model);
  }

  public LogViewModel getLogViewModel()
  {
    return logViewModel;
  }

  public ConvertViewModel getConvertViewModel()
  {
    return convertViewModel;
  }

}
