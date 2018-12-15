package favero.moreira.sma.termoreg;

import org.jfree.data.time.TimeSeries;

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
    private JButton stopButton;

    private JTextField nbGroupsTextField;

    //A JLabel contains a text label
    private JLabel labelSliderEnv;
    private JLabel labelGroups;
    private JLabel labelWarning;
    private JLabel labelSpeed;


    //final JFreeChart chart;
    final DynamicTimeSeriesChart chart;

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
        nbGroupsPanel.setSize(new Dimension(50, 739));


        //Font settings for sliders
        Font font = new Font("Serif", Font.ITALIC, 15);

        //Text Fields
        nbGroupsTextField = new JTextField();
        nbGroupsTextField.setColumns(5);
        nbGroupsTextField.setHorizontalAlignment(10);

        //Sliders Configuration
        envTempSlider = new JSlider(JSlider.HORIZONTAL, TEMP_MIN, TEMP_MAX, ENV_INIT);
        sliderEnvConfig(envTempSlider, font);

        speedSimulationSlider = new JSlider(JSlider.HORIZONTAL, SPEED_MAX, SPEED_MIN, SPEED_INIT);
        sliderSimulationConfig(speedSimulationSlider, font);

        createLabels();

        //Buttons
        applyButton = new JButton("Apply");
        resetButton = new JButton("Reset");
        stopButton = new JButton("Stop");

        applyButton.addActionListener(this);
        resetButton.addActionListener(this);
        stopButton.addActionListener(this);

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
        buttonPanel.add(stopButton);
        buttonPanel.setPreferredSize(new Dimension(100, 20));

        chart = new DynamicTimeSeriesChart("Hive Temperature");

        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BoxLayout(mainPanel, BoxLayout.Y_AXIS));

        //Add the mainPanel to this current JFrame
        this.add(mainPanel);
        mainPanel.add(nbGroupsPanel);
        mainPanel.add(sliderPanel);
        mainPanel.add(chart);
        mainPanel.add(buttonPanel);


        this.pack();
        this.setVisible(true);
        makeFrameFullSize();

    }

//    /**
//     * Creates a sample chart.
//     *
//     * @param dataset the dataset.
//     * @return A sample chart.
//     */

//    private JFreeChart createChart(final XYDataset dataset) {
//        final JFreeChart result = ChartFactory.createTimeSeriesChart(
//                "Hive Temperature",
//                "Time",
//                "Value",
//                dataset,
//                true,
//                true,
//                false
//        );
//        final XYPlot plot = result.getXYPlot();
//
//        DateAxis xaxis = (DateAxis)plot.getDomainAxis();
//        xaxis.setAutoRange(true);
//        //xaxis.setFixedAutoRange(60000.0);  // 60 seconds
//        xaxis.setRange(25.0, 40.0);
//        xaxis.setAutoRange(true); ////set true to move graph with time.
//        xaxis.setTickUnit(new DateTickUnit(DateTickUnit.SECOND, 2, new SimpleDateFormat("ss")));
//
//
//        XYItemRenderer renderer = plot.getRenderer();
//        renderer.setSeriesPaint(0, Color.RED);
//        renderer.setSeriesPaint(1, Color.GREEN);
//        return result;
//    }
//
//    public void update(Hive hive, int tickTime) {
//        this.lastValue = hive.getTemp();
//        ;
//        final Millisecond now = new Millisecond();
//        System.out.println("Now = " + now.toString());
//
//        final XYPlot plot = this.getChart().getXYPlot();
//        DateAxis xaxis = (DateAxis)plot.getDomainAxis();
//
//        xaxis.setTickUnit(new DateTickUnit(DateTickUnit.SECOND, tickTime, new SimpleDateFormat("ss")));
//        this.series.add(new Millisecond(), this.lastValue);
//    }

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

    public void createLabels() {
        //Create the Slider labels.
        this.labelSliderEnv = new JLabel("Environment Temperature: " + this.envTempSlider.getValue() + "\u00B0" + "C", JLabel.CENTER);
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

        this.nbGroupsPanel.setLayout(new GridLayout(3, 1));
        this.nbGroupsPanel.add(this.labelGroups);
        this.nbGroupsPanel.add(this.nbGroupsTextField);
        this.nbGroupsPanel.add(this.labelWarning);

    }

    private void makeFrameFullSize() {
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
                if (value == 0) {
                    value += 1;
                }
                SimulationParameters.setSpeedSimulation(value);
                SimulationParameters.setDelayPerception();
            }
        } else {

            if (source == this.envTempSlider) {
                this.labelSliderEnv.setText("Environment Temperature: " + envTempSlider.getValue() + "\u00B0" + "C");
            } else {
                this.labelSpeed.setText("Cycle Update: " + speedSimulationSlider.getValue() + "ms");
            }
        }
    }

    public DynamicTimeSeriesChart getChart() {
        return chart;
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
            if ((value > 0) && (value <= 5000)) {
                this.labelWarning.setVisible(false);
                BeeGroups.setNumGroup(value);
                BeeGroups.setbAbleUpdate(true);
                BeeGroups.updateGroup();
                BeeGroups.setbAbleUpdate(false);

            } else {
                this.labelWarning.setVisible(true);

            }
        } else if (e.getSource() == stopButton) {
            if (stopButton.getText().equals("Stop")) {
                SimulationParameters.stopSimulation();
                SimulationParameters.setSimulationStop(true);
                stopButton.setText("Start");
            } else {
                SimulationParameters.setSimulationStop(false);
                SimulationParameters.startSimulation();
                stopButton.setText("Stop");
            }
        }
    }
}
