package favero.moreira.sma.termoreg;

public class Main {

	public static void main(String[] args) {
		
		public double dTempAmb;

		// Get the only object available
		Hive hive = Hive.getInstance();

		// show the message
		hive.showMessage();
		hive.setTemp(35.0);
		
		System.out.println("Hive temperature: " + hive.getTemp());
		
		for(int b = 0; b<5000; b++) {
			Bee bee = new Bee();
		    bee.start();
		}
		
	}

}
