package server.viewmodel;

import server.model.Model;

public class ViewModelFactory
{

  private LogViewModel logViewModel;

  public ViewModelFactory(Model model)
  {

    this.logViewModel = new LogViewModel(model);
  }

  public LogViewModel getLogViewModel()
  {
    return logViewModel;
  }


}
