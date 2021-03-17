package temperature.viewmodel;

import temperature.mediator.TemperatureModel;

public class ViewModelFactory
{
  private TemperatureViewModel viewModel;

  public ViewModelFactory(TemperatureModel model)
  {
    this.viewModel=new TemperatureViewModel(model);
  }

  public TemperatureViewModel getViewModel()
  {
    return viewModel;
  }
}
