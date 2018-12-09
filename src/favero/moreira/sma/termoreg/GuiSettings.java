package favero.moreira.sma.termoreg;

import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.ValueAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.Millisecond;
import org.jfree.data.time.TimeSeries;
import org.jfree.data.time.TimeSeriesCollection;
import org.jfree.data.xy.XYDataset;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class GuiSettings extends JFrame implements ActionListener, ChangeListener {

    static final int TEMP_MIN = 0;
    static final int TEMP_MAX = 60;
    static final int ENV_INIT = 27;

    static final int SPEED_MIN = 1000;
    static final int SPEED_MAX = 0;//+1
    static final int SPEED_INIT = 500;

    public boolean update = false;

    //A Jpanel is a inner container that can be placed inside a JFrame
    private JPanel sliderPanel;
    private JPanel buttonPanel;
    private JPanel nbGroupsPanel;
    //A JSlider component
    private JSlider envTempSlider;
    private JSlider speedSimulationSlider;
    //trigger update button
    private JButton applyButton;
    private JButton resetButton;

    private JTextField nbGroupsTextField;

    //A JLabel contains a text label
    private JLabel labelSliderEnv;
    private JLabel labelGroups;
    private JLabel labelWarning;
    private JLabel labelSpeed;

    /**
     * The time series data.
     */
    private TimeSeries series;

    /**
     * The most recent value added.
     */
    private double lastValue = 100.0;


    public GuiSettings(final String title) {
        super(title);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        sliderPanel = new JPanel();
        buttonPanel = new JPanel();
        nbGroupsPanel = new JPanel();
        nbGroupsPanel.setSize(new Dimension(50,739));


        //Font settings for sliders
        Font font = new Font("Serif", Font.ITALIC, 15);

        //Text Fields
        nbGroupsTextField = new JTextField();
//        nbGroupsTextField.setColumns(4);

        //Sliders Configuration
        envTempSlider = new JSlider(JSlider.HORIZONTAL, TEMP_MIN, TEMP_MAX, ENV_INIT);
        sliderEnvConfig(envTempSlider, font);

        speedSimulationSlider = new JSlider(JSlider.HORIZONTAL, SPEED_MAX, SPEED_MIN, SPEED_INIT);
        sliderSimulationConfig(speedSimulationSlider,font);

        createLabels();

        //Buttons
        applyButton = new JButton("Apply");
        resetButton = new JButton("Reset");

        applyButton.addActionListener(this);
        resetButton.addActionListener(this);

        //Set the layout manager of the panel to be a grid of three rows and two columns
        sliderPanel.setLayout(new GridLayout(4, 1));
        sliderPanel.add(labelSliderEnv);
        sliderPanel.add(envTempSlider);

        sliderPanel.add(labelSpeed);
        sliderPanel.add(speedSimulationSlider);
        //FIRST_LINE_START	PAGE_START			FIRST_LINE_END
        //LINE_START			CENTER				LINE_END
        //LAST_LINE_START		PAGE_END			LAST_LINE_END

        //gridx == right
        //gridy == down
        groupsPanelConfig();

        buttonPanel.setLayout(new GridLayout(1, 2));
        buttonPanel.add(resetButton);
        buttonPanel.add(applyButton);
        buttonPanel.setPreferredSize(new Dimension(100, 100));


        this.series = new TimeSeries("Temperature", Millisecond.class);
        final TimeSeriesCollection dataset = new TimeSeriesCollection(this.series);
        final JFreeChart chart = createChart(dataset);

        final ChartPanel chartPanel = new ChartPanel(chart);


        final JPanel content = new JPanel(new GridLayout());
        content.add(chartPanel);
        //content.add(resetButton, BorderLayout.SOUTH);
        //content.add(applyButton, BorderLayout.SOUTH);
        chartPanel.setPreferredSize(new java.awt.Dimension(500, 270));

        //setContentPane(content);
        chartPanel.setName("Temperature Evolution");

        this.setLayout(new GridLayout(2, 2));
        //Add the mainPanel to this current JFrame
        this.add(nbGroupsPanel);
        this.add(sliderPanel);
        this.add(buttonPanel);
        this.add(chartPanel);

        this.pack();
        this.setVisible(true);
        makeFrameFullSize();

    }

    /**
     * Creates a sample chart.
     *
     * @param dataset the dataset.
     * @return A sample chart.
     */

    private JFreeChart createChart(final XYDataset dataset) {
        final JFreeChart result = ChartFactory.createTimeSeriesChart(
                "Hive Temperature",
                "Time",
                "Value",
                dataset,
                true,
                true,
                false
        );
        final XYPlot plot = result.getXYPlot();
        ValueAxis axis = plot.getDomainAxis();
        axis.setAutoRange(true);
        axis.setFixedAutoRange(60000.0);  // 60 seconds
        axis = plot.getRangeAxis();
        axis.setRange(15.0, 40.0);
        return result;
    }

    public void update(Hive hive) {
        this.lastValue = hive.getTemp();
        ;
        final Millisecond now = new Millisecond();
        System.out.println("Now = " + now.toString());
        this.series.add(new Millisecond(), this.lastValue);
    }

    public void sliderEnvConfig(JSlider slider, Font font) {
        slider.addChangeListener((ChangeListener) this);

        //Turn on labels at major tick marks.
        slider.setMajorTickSpacing(10);
        slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setFont(font);

    }

    public void sliderSimulationConfig(JSlider slider, Font font) {
        slider.addChangeListener((ChangeListener) this);

        //Turn on labels at major tick marks.
        slider.setMajorTickSpacing(100);
        //slider.setMinorTickSpacing(2);
        slider.setPaintTicks(true);
        slider.setPaintLabels(true);
        slider.setFont(font);

    }

    public void createLabels(){
        //Create the Slider labels.
        this.labelSliderEnv = new JLabel("Environment Temperature: " + this.envTempSlider.getValue()+ "\u00B0" + "C", JLabel.CENTER);
        this.labelSliderEnv.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.labelGroups = new JLabel("Number of groups", JLabel.CENTER);
        this.labelGroups.setAlignmentX(Component.CENTER_ALIGNMENT);

        this.labelSpeed = new JLabel("Cycle Update: " + this.speedSimulationSlider.getValue() + "ms", JLabel.CENTER);
        this.labelSpeed.setAlignmentX(Component.CENTER_ALIGNMENT);


        this.labelWarning = new JLabel(" Invalid Number", JLabel.CENTER);
        this.labelWarning.setAlignmentX(Component.CENTER_ALIGNMENT);
        this.labelWarning.setVisible(false);
    }

    public void groupsPanelConfig() {
//        this.nbGroupsPanel.setLayout(new GridBagLayout());
//        GridBagConstraints gbc = new GridBagConstraints();
//
//        gbc.gridx = 0;
//        gbc.gridy = 0;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.anchor = GridBagConstraints.PAGE_START;
//        this.nbGroupsPanel.add(labelGroups, gbc);
//
//
//        gbc.gridx = 0;
//        gbc.gridy = 1;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.anchor = GridBagConstraints.LINE_START;
//        this.nbGroupsPanel.add(this.nbGroupsTextField, gbc);
//
//        gbc.gridx = 2;
//        gbc.gridy = 1;
//        gbc.fill = GridBagConstraints.BOTH;
//        gbc.anchor = GridBagConstraints.LINE_END;
//        this.nbGroupsPanel.add(this.labelWarning, gbc);

        this.nbGroupsPanel.setLayout(new GridLayout(3,1));
        this.nbGroupsPanel.add(this.labelGroups);
        this.nbGroupsPanel.add(this.nbGroupsTextField);
        this.nbGroupsPanel.add(this.labelWarning);

    }

    private void makeFrameFullSize()
    {
        Dimension screenSize = Toolkit.getDefaultToolkit().getScreenSize();
        this.setSize(screenSize.width, screenSize.height);
    }

    public void stateChanged(ChangeEvent e) {
        JSlider source = (JSlider) e.getSource();

        if (!source.getValueIsAdjusting()) {
            int value = (int) source.getValue();
            System.out.println("Slider Value: " + value);
            if (source == this.envTempSlider) {
                Environment.setTemp(value);
            } else if (source == this.speedSimulationSlider) {
                if(value == 0){
                    value+=1;
                }
                SimulationParameters.setSpeedSimulation(value);
            }
        }
        else {

            if(source == this.envTempSlider){
                this.labelSliderEnv.setText("Environment Temperature: " + envTempSlider.getValue() + "\u00B0" + "C");
            }else{
                this.labelSpeed.setText("Cycle Update: " + speedSimulationSlider.getValue() + "ms");
            }
        }
    }

    @Override
    public void actionPerformed(ActionEvent e) {
        // TODO Auto-generated method stub
        if (e.getSource() == resetButton) {
            envTempSlider.setValue(27);
            Environment.setTemp(27);
            BeeGroups.setNumGroup(1);
            BeeGroups.setbAbleUpdate(true);
            BeeGroups.updateGroup();
            BeeGroups.setbAbleUpdate(false);
        } else if (e.getSource() == applyButton) {
            int value = Integer.parseInt(this.nbGroupsTextField.getText());
            if ((value <= 0) || (value > 5000)) {
                this.labelWarning.setVisible(true);
            } else {
                this.labelWarning.setVisible(false);
                BeeGroups.setNumGroup(value);
                BeeGroups.setbAbleUpdate(true);
                BeeGroups.updateGroup();
                BeeGroups.setbAbleUpdate(false);

            }
        }
    }
}
