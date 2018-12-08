package favero.moreira.sma.termoreg;
import java.util.Random;

public class Bee{
	
	private Hive hHive;
	private double dPrecision;

	private int iGroup;
	private double dSupLim = 36.0;
	private double dInfLim = 32.0;
	//private String sBeeId;
	private int iId;
	 protected static double dTempBees;

   public Bee(Hive hive, int iGroup){
	this.hHive = hive;
	this.iGroup = iGroup;
	this.dTempBees = 0;
	this.dSupLim =36.0;
	this.dInfLim =32.0;
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

	public double getdPrecision() {
		return this.dPrecision;
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
	public void setiGroup(int iGroup) {
		this.iGroup = iGroup;
	}
	public void setConfig(int iGroup, double dNewInfLim, double dNewSupLim) {
		setiGroup(iGroup);
		setdInfLim(dNewInfLim);
		setdSupLim(dNewSupLim);
	}
}
