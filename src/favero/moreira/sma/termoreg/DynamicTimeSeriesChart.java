package favero.moreira.sma.termoreg;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;

import javax.swing.*;
import java.text.SimpleDateFormat;

public class DynamicTimeSeriesChart extends JPanel {

    private DynamicTimeSeriesCollection dataset;
    private JFreeChart chart;

    public DynamicTimeSeriesChart(final String title) {

        dataset = new DynamicTimeSeriesCollection(1, 60, new Second());
        dataset.setTimeBase(new Second(0, 0, 0, 1, 1, 2000)); // date 1st jan 0 mins 0 secs

        dataset.addSeries(new float[1], 0, title);
        chart = null;
        chart = ChartFactory.createTimeSeriesChart(
                title, "Time", "Temperature", dataset, true,
                true, false);
        final XYPlot plot = chart.getXYPlot();
        plot.clearAnnotations();
        DateAxis axis = (DateAxis) plot.getDomainAxis();
        //axis = plot.getRangeAxis();
        axis.setRange(25.0, 40.0);
        axis.setAutoRange(true); ////set true to move graph with time.
        axis.setDateFormatOverride(new SimpleDateFormat("mm.ss.SS"));
        final ChartPanel chartPanel = new ChartPanel(chart);
        setLayout(new BoxLayout(this, BoxLayout.PAGE_AXIS));
        add(chartPanel);

    }

    //TODO Add tick labels
    public void update(float value, int tickTime) {
        float[] newData = new float[1];
        newData[0] = value;
        dataset.advanceTime();
        dataset.appendData(newData);
    }

}