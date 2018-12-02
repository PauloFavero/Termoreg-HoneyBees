package favero.moreira.sma.termoreg;

public class Main {

	public static void main(String[] args) {

		// Get the only object available
		Hive hive = Hive.getInstance();
		GuiSettings guiWindow = new GuiSettings();

		// show the message
		hive.showMessage();
		hive.setTemp(35.0);
			
		System.out.println("Hive temperature: " + hive.getTemp());
		
		for (int i = 0; i < 2; i++) {
			
			Bee b = new Bee(Integer.toString(i));
			
		}
	}

}
