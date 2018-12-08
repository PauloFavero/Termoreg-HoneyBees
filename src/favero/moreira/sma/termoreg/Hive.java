package favero.moreira.sma.termoreg;

public class Hive {
	
	protected double temp;
	private int maxOfBees;
	private Bee[] bee;


	public Bee[] getBee() {
		return bee;
	}


	//create an object of SingleObject
	   private static Hive instance;
	   

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private Hive(){
		   this.setMaxOfBees(5);
		   this.bee = new Bee[this.maxOfBees];
		   
		   this.setTemp(15.);
		   //System.out.println ("Hive's temperature right now is " + this.temp);
		   for(int i  = 0; i<this.maxOfBees; i++) {
			   this.bee[i] = new Bee(this);
		   }
	   }

	   //Get the only object available
	   public static Hive getInstance(){
		  if(instance == null ) {
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
		
	   
		public void showMessage(){
	      System.out.println("I'm a hive ");
	   }

		public void setMaxOfBees(int nBees) {
			this.maxOfBees = nBees;
			
		}

}
