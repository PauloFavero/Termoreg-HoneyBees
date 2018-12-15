package favero.moreira.sma.termoreg;

public class Hive {

    protected double temp;
    private int maxOfBees;
    private Bee[] bee;
    private int iNumGroups = 1;
    private double heatCoeffTransfer;

    //create an object of SingleObject
    private static Hive instance;

    public Bee[] getBee() {
        return bee;
    }

    /***************************************************************************
     * Method name: Hive
     * Method description: Private Constructor for Singleton Pattern
     * @param
     * @param
     * @return Void
     ***************************************************************************/
    private Hive() {
        this.setMaxOfBees(5000);
        this.heatCoeffTransfer = 10.0;
        this.bee = new Bee[this.maxOfBees];
        this.setTemp(25.);
        //System.out.println ("Hive's temperature right now is " + this.temp);
        for (int i = 0; i < this.maxOfBees; i++) {
            this.bee[i] = new Bee(this, (int) (i % iNumGroups));
        }

    }

    /***************************************************************************
     * Method name: getInstance
     * Method description: Returns the unique instance of Hive
     * @param
     * @param
     * @return Hive
     ***************************************************************************/
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
        this.temp = temp;
    }

    /***************************************************************************
     * Method name: update
     * Method description: set heterogeneous configuration of the bees
     * @param iNumGroups
     * @param dNewInfLim
     * @param dNewSupLim
     * @return Void
     ***************************************************************************/
    public void update(int iNumGroups, double dNewInfLim[], double dNewSupLim[]) {
        for (int i = 0; i < maxOfBees; i++) {
            bee[i].setConfig(i % iNumGroups, dNewInfLim[i % iNumGroups], dNewSupLim[i % iNumGroups]);
        }
    }

    public void setMaxOfBees(int nBees) {
        this.maxOfBees = nBees;

    }

    /***************************************************************************
     * Method name: getHeatCoeffTransfer
     * Method description: Getter heatCoeffTransfer
     * @param
     * @return double
     ***************************************************************************/
    public double getHeatCoeffTransfer() {
        return heatCoeffTransfer;
    }

    /***************************************************************************
     * Method name: setHeatCoeffTransfer
     * Method description: Setter heatCoeffTransfer
     * @param heatCoeffTransfer
     * @return void
     ***************************************************************************/
    public void setHeatCoeffTransfer(double heatCoeffTransfer) {
        this.heatCoeffTransfer = heatCoeffTransfer;
    }
}
