package favero.moreira.sma.termoreg;

public class Hive {
	
	protected double temp;
	private int maxOfBees;
	private Bee[] bee;
	private int iNumGroups=1;
    private double heatCoeffTransfer;

    //create an object of SingleObject
    private static Hive instance;

    public Bee[] getBee() {
        return bee;
    }

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private Hive(){
		   this.setMaxOfBees(5);
		   this.heatCoeffTransfer = 10.0;
		   this.bee =  new Bee[this.maxOfBees];
		   this.setTemp(15.);
		   //System.out.println ("Hive's temperature right now is " + this.temp);
		   for(int i  = 0; i<this.maxOfBees; i++) {
			   this.bee[i] = new Bee(this, (int)(maxOfBees%iNumGroups));
		   }
	   }

    //Get the only object available
    public static Hive getInstance() {
        if (instance == null) {
            instance = new Hive();
        }
        return instance;
    }

    public double getTemp() {
        return temp;
    }

    public void setTemp(double temp) {
        this.temp += temp;
    }


    public void showMessage() {
        System.out.println("I'm a hive ");
    }

    public void setMaxOfBees(int nBees) {
        this.maxOfBees = nBees;

    }

    public double getHeatCoeffTransfer() {
        return heatCoeffTransfer;
    }

    public void setHeatCoeffTransfer(double heatCoeffTransfer) {
        this.heatCoeffTransfer = heatCoeffTransfer;
    }
}
