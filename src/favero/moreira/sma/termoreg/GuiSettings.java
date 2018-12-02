package favero.moreira.sma.termoreg;

import java.awt.*;

import java.awt.event.*;
import javax.swing.*;
import javax.swing.event.ChangeListener;
import javax.swing.event.ChangeEvent;



public class GuiSettings extends JFrame implements ActionListener, ChangeListener{
	
	static final int TEMP_MIN = 32;
	static final int TEMP_MAX = 36;
	static final int INF_INIT = 32;
	static final int SUP_INIT = 36;
	
	public double infValue;
	public double supValue;
	
	public boolean update = false;
	
	//A Jpanel is a inner container that can be placed inside a JFrame
	private JPanel mainPanel;
	private JPanel sliderPanel;
	private JPanel buttonPanel;
	//A JSlider component 
	private JSlider infLimSlider;
	private JSlider supLimSlider;
	//trigger update button
	private JButton applyButton;
	private JButton resetButton;
	
	//A JLabel contains a text label 
	private JLabel labelSliderInf;
	private JLabel labelSliderSup;
	
	
	public GuiSettings() {
		super("Simulation Settings");
		
		mainPanel   = new JPanel();
		sliderPanel = new JPanel();
		buttonPanel = new JPanel();
		
		//Font settings for sliders
		Font font = new Font("Serif", Font.ITALIC, 15);
		
		//Sliders Configuration
		
		infLimSlider = new JSlider(JSlider.HORIZONTAL,TEMP_MIN, TEMP_MAX, INF_INIT);
		supLimSlider =  new JSlider(JSlider.HORIZONTAL,TEMP_MIN, TEMP_MAX, SUP_INIT);
		
		//Create the label.
		labelSliderInf = new JLabel("Threshold Min", JLabel.CENTER);
		labelSliderInf.setAlignmentX(Component.CENTER_ALIGNMENT);
		labelSliderSup = new JLabel("Threshold Max", JLabel.CENTER);
		labelSliderSup.setAlignmentX(Component.CENTER_ALIGNMENT);
		
		
		infLimSlider.addChangeListener((ChangeListener) this);
		supLimSlider.addChangeListener((ChangeListener) this);

		//Turn on labels at major tick marks.
		infLimSlider.setMajorTickSpacing(2);
		infLimSlider.setMinorTickSpacing(1);
		infLimSlider.setPaintTicks(true);
		infLimSlider.setPaintLabels(true);
		
		//Turn on labels at major tick marks.
		supLimSlider.setMajorTickSpacing(2);
		supLimSlider.setMinorTickSpacing(1);
		supLimSlider.setPaintTicks(true);
		supLimSlider.setPaintLabels(true);
		
		infLimSlider.setFont(font);
		supLimSlider.setFont(font);
		
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

		
		this.setLayout(new GridLayout(2,1));
		//Add the mainPanel to this current JFrame
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
	        	Bee.setdInfLim((double)value);
	        }
	        else {
	        	Bee.setdSupLim((double)value);
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
	    	
	    	Bee.setdInfLim((double)infLimSlider.getValue());
	    	Bee.setdSupLim((double)supLimSlider.getValue());
	    	}
	    else if(e.getSource() == applyButton) {
	    	
	    	for (int i = 0; i < Hive.getInstance().getBee().length; i++) {
	    		Hive.getInstance().getBee()[i].changeThreshold();
				
			}
	    	
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
}
