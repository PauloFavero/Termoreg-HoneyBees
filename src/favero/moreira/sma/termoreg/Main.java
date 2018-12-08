package favero.moreira.sma.termoreg;

public class Main extends Thread {

    public static void main(String[] args) throws InterruptedException {

        // Get the only object available
        Hive hive = Hive.getInstance();
        Environment environment = Environment.getInstance();

        Thread t = Thread.currentThread();
        t.setName("Main Thread");

        GuiSettings guiWindow = new GuiSettings();


        System.out.println("Hive temperature: " + hive.getTemp());

        while(true){
            System.out.println("Hive temperature BEFORE: " + hive.getTemp());
            beesPerception(hive,environment);
            t.sleep(500);
            System.out.println("Hive temperature AFTER: " + hive.getTemp());

        }

    }

    /* ************************************************ */
    /* Method name:        beesPerception               */
    /* Method description: Call the perception of each  */
    /* bee and do the temperature control               */
    /* Input params:       Hive                         */
    /* Input params:       Environment                  */
    /* Outpu params:       Void                         */
    /* ************************************************ */
    private static void beesPerception(Hive hive, Environment environment) {

        for (int i = 0; i < hive.getBee().length; i++) {
            hive.getBee()[i].perception();
        }
        heatTransfer(hive, environment);
    }

    /* TODO
    * replace with the correct variable of Bees.
    */

    /* ************************************************ */
    /* Method name:        heatTransfer                 */
    /* Method description:    Set the temperature of    */
    /* the Hive if have necessary changes               */
    /* Input params:       Hive                         */
    /* Input params:       Environment                  */
    /* Outpu params:       Void                         */
    /* ************************************************ */
    private static void heatTransfer(Hive hive, Environment environment) {

        //hiveTemp = Bees.contribution hiveTemp + (envTemp-hiveTemp)/hiveCoeffTransfer
        double temp = Bee.dTempBees + hive.getTemp() + (environment.getTemp()-hive.getTemp())/hive.getHeatCoeffTransfer();

        hive.setTemp(Bee.dTempBees + hive.getTemp() + (environment.getTemp()-hive.getTemp())/hive.getHeatCoeffTransfer());
    }


}
