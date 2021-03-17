package viewmodel;

import model.TemperatureModel;

public class ViewModelFactory
{
  private TemperatureViewModel temperatureViewModel;

  public ViewModelFactory(TemperatureModel model)
  {
    this.temperatureViewModel =new TemperatureViewModel(model);
  }
  public TemperatureViewModel getTemperatureViewModel()
  {
    return temperatureViewModel;
  }
}
