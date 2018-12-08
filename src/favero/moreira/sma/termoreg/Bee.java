package favero.moreira.sma.termoreg;
import java.util.Random;

public class Bee{
	
	private Hive hHive;
	// private double dThreshold;
	private double dPrecision;
	private int iGroup;
	private static double dSupLim;
	private static double dInfLim;
	//private String sBeeId;
	private int iId;
	static double dTempBees;

   public Bee(Hive hive, int iGroup){
	this.hHive = hive;
	this.iGroup = iGroup;
	this.dTempBees = 0;
   }
	/*
	public void run(){
        try{
        	while(true) {
            // Displaying the thread that is running 
            System.out.println ("Thread " +
                  Thread.currentThread().getId() + 
                  " is running");
            System.out.println("Bee Id: " + this.sBeeId);
        	System.out.println ("Hive's temperature right now is " +
                        this.hHive.temp);
        	this.perception();
        	}
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught");
        }
    }*/
	
	public void perception() {
		if(isHot()) {
			this.makeItCold();
		}
		else if(isCold()) {
			this.makeItHot();
		}
	}
	
	private boolean isHot() {
		if((this.hHive.temp + this.dPrecision) > this.dSupLim)
			return true;
		else return false;
	}
	
	private boolean isCold() {
		if((this.hHive.temp - this.dPrecision) < this.dInfLim)
			return true;
		else return false;
	}
	
	private void makeItCold() {
		this.dTempBees += -0.0005;
//		System.out.println ("Bee " + 
//                this.iId + 
//                " is cooling the hive");
	}
	
	private void makeItHot() {
		this.dTempBees += 0.0005;
//		System.out.println ("Bee " +
//                this.iId +
//                " is heating the hive");
	}
	/*
	public void init() {

		this.dPrecision = 0.5;
		//this.dThreshold = randomGenerator();
		this.iGroup =
		System.out.println ("Bee iGroup " +
                this.iGroup);
		this.iId = (int)java.lang.Thread.currentThread().getId();
	}*/
	
	public double randomGenerator() {
		
		Random r = new Random();
		double randomValue = dInfLim + (dSupLim - dInfLim) * r.nextDouble();
		
		return randomValue;
	}

	public double getdPrecision() {
		return this.dPrecision;
	}
	public void setdPrecision(double dPrecision) {
		this.dPrecision = dPrecision;
	}
	
	public static double getdSupLim() {
		return dSupLim;
	}

	public static void setdSupLim(double dSupLim) {
		Bee.dSupLim = dSupLim;
	}

	public static double getdInfLim() {
		return dInfLim;
	}

	public static void setdInfLim(double dInfLim) {
		Bee.dInfLim = dInfLim;
	}

}
