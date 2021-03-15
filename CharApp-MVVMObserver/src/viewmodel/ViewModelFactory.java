package viewmodel;

import model.ModelFactory;
import viewmodel.barchart.BarChartViewModel;
import viewmodel.piechart.PieChartViewModel;
import viewmodel.textrepresentation.TextViewModel;

public class ViewModelFactory
{
  private PieChartViewModel pieChartViewModel;
  private TextViewModel textViewModel;
  private BarChartViewModel barChartViewModel;

  public ViewModelFactory(ModelFactory modelFactory)
  {
    pieChartViewModel = new PieChartViewModel(modelFactory.getDataModel());
    textViewModel = new TextViewModel(modelFactory.getDataModel());
    barChartViewModel=new BarChartViewModel(modelFactory.getDataModel());
  }

  public PieChartViewModel getPieChartViewModel()
  {
    return pieChartViewModel;
  }

  public TextViewModel getTextViewModel()
  {
    return textViewModel;
  }

  public BarChartViewModel getBarChartViewModel()
  {
    return barChartViewModel;
  }
}
