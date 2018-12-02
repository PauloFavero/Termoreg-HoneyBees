package favero.moreira.sma.termoreg;
import java.util.Random;

public class Bee implements Runnable{
	
	private double dThreshold;
	private double dPrecision;
	private double dSupLim;
	private double dInfLim;
	private String sBeeId;
	private static int iId=0;
	private Thread t;
	
   public Bee(String name){
	this.init();
	this.sBeeId = name;
    t = new Thread(this, name);
    System.out.println("New thread: " + t);
    t.start();
   }
	
   
	
	public void run() 
    { 
        try
        { 
        	while(true) {
            // Displaying the thread that is running 
//            System.out.println ("Thread " + 
//                  Thread.currentThread().getId() + 
//                  " is running"); 
//            System.out.println("Bee Id: " + this.sBeeId);
        	}
  
        } 
        catch (Exception e) 
        { 
            // Throwing an exception 
            System.out.println ("Exception is caught"); 
        } 
    } 
	
	public void init() {
		
		this.dInfLim = 32.0;
		this.dSupLim = 36.0;
		this.dPrecision = 0.5;
		this.dThreshold = randomGenerator();
		Bee.iId += 1;
	}
	
	public double randomGenerator() {
		
		Random r = new Random();
		double randomValue = this.dInfLim + (this.dSupLim - this.dInfLim) * r.nextDouble();
		
		return randomValue;
	}
	
	public double getdThreshold() {
		return dThreshold;
	}
	public void setdThreshold(double dThreshold) {
		this.dThreshold = dThreshold;
	}
	public double getdPrecision() {
		return dPrecision;
	}
	public void setdPrecision(double dPrecision) {
		this.dPrecision = dPrecision;
	}
	public double getdSupLim() {
		return dSupLim;
	}
	public void setdSupLim(double dSupLim) {
		this.dSupLim = dSupLim;
	}
	public double getdInfLim() {
		return dInfLim;
	}
	public void setdInfLim(double dInfLim) {
		this.dInfLim = dInfLim;
	}

	
	
	

}
