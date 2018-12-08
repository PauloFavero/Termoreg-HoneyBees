package favero.moreira.sma.termoreg;
import java.util.Random;

public class Bee{
	
	private Hive hHive;
	private double dThreshold;
	private double dPrecision;
	private static double dSupLim = 36.;
	private static double dInfLim = 32.;
	//private String sBeeId;
	private int iId;
	
   public Bee(Hive hive){
	this.hHive = hive;
    this.init();
   }
	
	public void run(){
        try{
        	while(true) {
            // Displaying the thread that is running 
            /*System.out.println ("Thread " + 
                  Thread.currentThread().getId() + 
                  " is running");
            System.out.println("Bee Id: " + this.sBeeId);*/
//        	System.out.println ("Hive's temperature right now is " + 
//                        this.hHive.temp);
        	this.perception();
        	}
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught");
        }
    }
	
	public void perception() {
		if(isHot()) {
			this.makeItCold();
		}
		else if(isCold()) {
			this.makeItHot();
		}
	}
	
	private boolean isHot() {
		if((this.hHive.temp + this.dPrecision) > this.dThreshold)
			return true;
		else return false;
	}
	
	private boolean isCold() {
		if((this.hHive.temp - this.dPrecision) < this.dThreshold)
			return true;
		else return false;
	}
	
	private void makeItCold() {
		this.hHive.setTemp(-0.0005);
//		System.out.println ("Bee " + 
//                this.iId + 
//                " is cooling the hive");
	}
	
	private void makeItHot() {
		this.hHive.setTemp(0.0005);
//		System.out.println ("Bee " + 
//                this.iId + 
//                " is heating the hive");
	}
	
	public void init() {

		this.dPrecision = 0.5;
		this.dThreshold = randomGenerator();
		System.out.println ("Bee dThreshold " + 
                this.dThreshold);
		this.iId = (int)java.lang.Thread.currentThread().getId();
	}
	
	public void changeThreshold() {
		this.dThreshold = randomGenerator();
	}
	
	public double randomGenerator() {
		
		Random r = new Random();
		double randomValue = dInfLim + (dSupLim - dInfLim) * r.nextDouble();
		
		return randomValue;
	}
	
	public double getdThreshold() {
		return dThreshold;
	}
	public void setdThreshold(double dThreshold) {
		this.dThreshold = dThreshold;
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
