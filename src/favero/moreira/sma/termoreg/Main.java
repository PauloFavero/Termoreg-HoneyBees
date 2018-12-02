package favero.moreira.sma.termoreg;
import favero.moreira.sma.termoreg.Hive;

public class Main {

	public static void main(String[] args) {

		// Get the only object available
		Hive hive = Hive.getInstance();
		GuiSettings guiWindow = new GuiSettings();

		guiWindow.getInfValue();
		guiWindow.getSupValue();
		
		System.out.println("Hive temperature: " + hive.getTemp());
		
	}
}
