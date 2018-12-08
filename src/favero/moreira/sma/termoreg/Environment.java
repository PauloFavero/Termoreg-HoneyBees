package favero.moreira.sma.termoreg;

public class Environment {

    private double dtemp;
    private static Environment instance;

    private Environment() {
        this.dtemp = 27.0;

    }

    public static Environment getInstance() {

        if (instance == null) {
            return instance = new Environment();
        } else {
            return instance;
        }


    }

    public double getTemp() {
        return dtemp;
    }

    public void setTemp(double dtemp) {
        this.dtemp = dtemp;
    }
}
