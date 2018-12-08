package favero.moreira.sma.termoreg;

import javax.swing.*;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/*import javax.swing.Timer;
import org.jfree.chart.ChartFactory;
import org.jfree.chart.ChartPanel;
import org.jfree.chart.JFreeChart;
import org.jfree.chart.axis.DateAxis;
import org.jfree.chart.plot.XYPlot;
import org.jfree.data.time.DynamicTimeSeriesCollection;
import org.jfree.data.time.Second;
*/

//Tutoriel JAVAFX code.makery


public class GuiSettings extends JFrame implements Runnable, ActionListener, ChangeListener{
	
	static final int TEMP_MIN = 32;
	static final int TEMP_MAX = 36;
	static final int INF_INIT = 32;
	static final int SUP_INIT = 36;
	static final int BEES_MIN = 1;
	static final int BEES_MAX = 5000;

	public double infValue;
	public double supValue;
	
	public boolean update = false;
	
	//A Jpanel is a inner container that can be placed inside a JFrame
	private JPanel mainPanel;
	private JPanel sliderPanel;
	private JPanel buttonPanel;
	private JPanel nbGroupsPanel;
	//A JSlider component 
	private JSlider infLimSlider;
	private JSlider supLimSlider;
	private JSlider nbGroupsSlider;
	//trigger update button
	private JButton applyButton;
	private JButton resetButton;
	private JButton setGroups;

	private JTextField nbGroups;
	
	//A JLabel contains a text label 
	private JLabel labelSliderInf;
	private JLabel labelSliderSup;
	private JLabel labelGroupsSlider;
	
	
	public GuiSettings() {
		super("Simulation Settings");
		
		mainPanel   = new JPanel();
		sliderPanel = new JPanel();
		buttonPanel = new JPanel();
		nbGroupsPanel = new JPanel();
		
		//Font settings for sliders
		Font font = new Font("Serif", Font.ITALIC, 15);
		
		//Sliders Configuration
		infLimSlider = new JSlider(JSlider.HORIZONTAL,TEMP_MIN, TEMP_MAX, INF_INIT);
		supLimSlider =  new JSlider(JSlider.HORIZONTAL,TEMP_MIN, TEMP_MAX, SUP_INIT);
		nbGroupsSlider =  new JSlider(JSlider.HORIZONTAL,BEES_MIN, BEES_MAX, 1);
		
		//Create the Slider labels.
		labelSliderInf = new JLabel("Threshold Min", JLabel.CENTER);
		labelSliderInf.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelSliderSup = new JLabel("Threshold Max", JLabel.CENTER);
		labelSliderSup.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelGroupsSlider = new JLabel("Set the groupement of bees", JLabel.CENTER);
		labelGroupsSlider.setAlignmentX(Component.CENTER_ALIGNMENT);

		
		infLimSlider.addChangeListener((ChangeListener) this);
		supLimSlider.addChangeListener((ChangeListener) this);
		nbGroupsSlider.addChangeListener((ChangeListener) this);

		//Turn on labels at major tick marks.
		infLimSlider.setMajorTickSpacing(2);
		infLimSlider.setMinorTickSpacing(1);
		infLimSlider.setPaintTicks(true);
		infLimSlider.setPaintLabels(true);

		//Turn on labels at major tick marks.
		nbGroupsSlider.setMajorTickSpacing(500);
		nbGroupsSlider.setMinorTickSpacing(100);
		nbGroupsSlider.setPaintTicks(true);
		nbGroupsSlider.setPaintLabels(true);
		
		//Turn on labels at major tick marks.
		supLimSlider.setMajorTickSpacing(2);
		supLimSlider.setMinorTickSpacing(1);
		supLimSlider.setPaintTicks(true);
		supLimSlider.setPaintLabels(true);
		
		infLimSlider.setFont(font);
		supLimSlider.setFont(font);
		nbGroupsSlider.setFont(font);
		
		//Buttons

		applyButton = new JButton("Apply");
		resetButton = new JButton("Reset");
		
		applyButton.addActionListener(this); 
		resetButton.addActionListener(this);
		
		//Set the layout manager of the panel to be a grid of three rows and two columns 
		sliderPanel.setLayout(new GridLayout(2,2));
		sliderPanel.add(labelSliderInf);
		sliderPanel.add(labelSliderSup);
		sliderPanel.add(infLimSlider);
		sliderPanel.add(supLimSlider);

		nbGroupsPanel.setLayout(new GridLayout(2,1));
		nbGroupsPanel.add(labelGroupsSlider);
		nbGroupsPanel.add(nbGroupsSlider);

		
		buttonPanel.setLayout(new GridLayout(1,2));
		buttonPanel.add(resetButton);
		buttonPanel.add(applyButton);
		
//		mainPanel.setLayout(new GridLayout(3,2));	
//		
//		//Add the textField in the first position in the grid
//		mainPanel.add(infLimSlider);
//		//Add the textField in the second position in the grid
//		mainPanel.add(supLimSlider);
//		//Add the button in the third position in the grid
//		mainPanel.add(applyButton);

		
		this.setLayout(new GridLayout(3,1));
		//Add the mainPanel to this current JFrame
		this.add(nbGroupsSlider);
		this.add(sliderPanel);
		this.add(buttonPanel);
		
		this.pack();
		
		this.setVisible(true);
	
	}	
	
	public void stateChanged(ChangeEvent e) {
	    JSlider source = (JSlider)e.getSource();
	    
	    if (!source.getValueIsAdjusting()) {
	        int value = (int)source.getValue();
	        System.out.println("Slider Value: " +  value);
	        if(source == infLimSlider ) {
	        	//Bee.setdInfLim((double)value);
	        }
			else if(source == nbGroupsSlider ) {
				BeeGroups.setNumGroup(value);
			}
	        else {
	        	//Bee.setdSupLim((double)value);
	        }
	        
	    }
	}

	@Override
	public void actionPerformed(ActionEvent e) {
		// TODO Auto-generated method stub
	    if(e.getSource() == resetButton) {
	    	infLimSlider.setValue(32);
	    	supLimSlider.setValue(36);
	    	
	    	this.infValue = (double)infLimSlider.getValue();
	    	this.supValue = (double)supLimSlider.getValue();
	    	
	    	//Bee.setdInfLim((double)infLimSlider.getValue());
	    	//Bee.setdSupLim((double)supLimSlider.getValue());
	    	}
	    else if(e.getSource() == applyButton) {
				BeeGroups.updateGroup();
	    }
	}

	
	public double getInfValue() {
		return infValue;
	}

	private void setInfValue(double infValue) {
		this.infValue = infValue;
	}

	public double getSupValue() {
		return supValue;
	}

	private void setSupValue(double supValue) {
		this.supValue = supValue;
	}

	@Override
	public void run() {

	}
}
