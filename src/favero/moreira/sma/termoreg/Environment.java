package favero.moreira.sma.termoreg;

public class Environment {

    private static double dtemp;
    private static Environment instance;

    private Environment() {
        this.dtemp = 30.0;

    }

    public static Environment getInstance() {

        if (instance == null) {
            return instance = new Environment();
        } else {
            return instance;
        }


    }

    public static double getTemp() {
        return dtemp;
    }

    public static void setTemp(double dtemp) {
        Environment.dtemp = dtemp;
    }
}
