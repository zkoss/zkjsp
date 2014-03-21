package org.zkoss.jspdemo.charts;

import org.zkoss.chart.*;
import org.zkoss.zk.ui.Component;
import org.zkoss.zk.ui.select.SelectorComposer;
import org.zkoss.zk.ui.select.annotation.Wire;
import org.zkoss.zul.*;

public class ChartsComposer extends SelectorComposer<Component>{
    @Wire
    Charts chart;

    @Override
    public void doAfterCompose(Component comp) throws Exception {
        super.doAfterCompose(comp);
        
        chart.setModel(LineBasicData.getCategoryModel());
        
        chart.getTitle().setX(-20);
    
        chart.getSubtitle().setX(-20);
    
        chart.getYAxis().setTitle("Temperature (°C)");
        PlotLine plotLine = new PlotLine();
        plotLine.setValue(0);
        plotLine.setWidth(1);
        plotLine.setColor("#808080");
        chart.getYAxis().addPlotLine(plotLine);

        chart.getTooltip().setValueSuffix("°C");

        Legend legend = chart.getLegend();
        legend.setLayout("vertical");
        legend.setAlign("right");
        legend.setVerticalAlign("middle");
        legend.setBorderWidth(0);
    }
    
    static class LineBasicData {
        private static final CategoryModel model;
        static {
            model = new SimpleCategoryModel();
            model.setValue("Tokyo", "Jan", 7.0);
            model.setValue("Tokyo", "Feb", 6.9);
            model.setValue("Tokyo", "Mar", 9.5);
            model.setValue("Tokyo", "Apr", 14.5);
            model.setValue("Tokyo", "May", 18.2);
            model.setValue("Tokyo", "Jun", 21.5);
            model.setValue("Tokyo", "Jul", 25.2);
            model.setValue("Tokyo", "Aug", 26.5);
            model.setValue("Tokyo", "Sep", 23.3);
            model.setValue("Tokyo", "Oct", 18.3);
            model.setValue("Tokyo", "Nov", 13.9);
            model.setValue("Tokyo", "Dec", 9.6);
            model.setValue("New York", "Jan", -0.2);
            model.setValue("New York", "Feb", 0.8);
            model.setValue("New York", "Mar", 5.7);
            model.setValue("New York", "Apr", 11.3);
            model.setValue("New York", "May", 17.0);
            model.setValue("New York", "Jun", 22.0);
            model.setValue("New York", "Jul", 24.8);
            model.setValue("New York", "Aug", 24.1);
            model.setValue("New York", "Sep", 20.1);
            model.setValue("New York", "Oct", 14.1);
            model.setValue("New York", "Nov", 8.6);
            model.setValue("New York", "Dec", 2.5);
            model.setValue("Berlin", "Jan", -0.9);
            model.setValue("Berlin", "Feb", 0.6);
            model.setValue("Berlin", "Mar", 3.5);
            model.setValue("Berlin", "Apr", 8.4);
            model.setValue("Berlin", "May", 13.5);
            model.setValue("Berlin", "Jun", 17.0);
            model.setValue("Berlin", "Jul", 18.6);
            model.setValue("Berlin", "Aug", 17.9);
            model.setValue("Berlin", "Sep", 14.3);
            model.setValue("Berlin", "Oct", 9.0);
            model.setValue("Berlin", "Nov", 3.9);
            model.setValue("Berlin", "Dec", 1.0);
            model.setValue("London", "Jan", 3.9);
            model.setValue("London", "Feb", 4.2);
            model.setValue("London", "Mar", 5.7);
            model.setValue("London", "Apr", 8.5);
            model.setValue("London", "May", 11.9);
            model.setValue("London", "Jun", 15.2);
            model.setValue("London", "Jul", 17.0);
            model.setValue("London", "Aug", 16.6);
            model.setValue("London", "Sep", 14.2);
            model.setValue("London", "Oct", 10.3);
            model.setValue("London", "Nov", 6.6);
            model.setValue("London", "Dec", 4.8);
        }
        
        public static CategoryModel getCategoryModel() {
            return model;
        }
    }    
}
