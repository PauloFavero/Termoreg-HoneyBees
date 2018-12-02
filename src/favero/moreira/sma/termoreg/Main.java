package favero.moreira.sma.termoreg;
import favero.moreira.sma.termoreg.Hive;

public class Main {

	public static void main(String[] args) {

		// Get the only object available
		Hive hive = Hive.getInstance();
		GuiSettings guiWindow = new GuiSettings();

		
		// show the message

		//hive.showMessage();
		//hive.setTemp(35.0);
		//hive.setMaxOfBees(5);
		
		System.out.println("Hive temperature: " + hive.getTemp());
		
	}
}
