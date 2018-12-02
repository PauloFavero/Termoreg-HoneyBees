package favero.moreira.sma.termoreg;

public class Hive {
	
	protected double temp;
	


	//create an object of SingleObject
	   private static Hive instance = new Hive();
	   

	   //make the constructor private so that this class cannot be
	   //instantiated
	   private Hive(){
		   
		   
		   
	   }

	   //Get the only object available
	   public static Hive getInstance(){
	      return instance;
	   }

		public double getTemp() {
			return temp;
		}

		public void setTemp(double temp) {
			this.temp = temp;
		}
	   
	   public void showMessage(){
	      System.out.println("I'm a hive with a lot of honey :) !");
	   }

}
