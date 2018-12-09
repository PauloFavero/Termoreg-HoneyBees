package favero.moreira.sma.termoreg;

import org.jfree.ui.RefineryUtilities;

public class Main extends Thread {



    public static void main(String[] args) throws InterruptedException {

        // Get the only object available
        Hive hive = Hive.getInstance();
        Environment environment = Environment.getInstance();
        SimulationParameters simulation = new SimulationParameters();

        Thread t = Thread.currentThread();
        t.setName("Main Thread");

        final GuiSettings guiWindow = new GuiSettings("Super Bee Simulator 2000");
        guiWindow.pack();
        RefineryUtilities.centerFrameOnScreen(guiWindow);

        guiWindow.setVisible(true);

        //500x729
        guiWindow.getSize();


        System.out.println("Hive temperature: " + hive.getTemp());

        while (true) {
            if (!BeeGroups.getIsbAbleUpdate()) {
                System.out.println("Hive temperature BEFORE: " + hive.getTemp());
                guiWindow.update(hive);
                beesPerception(hive, environment);
                sleep(simulation.getSpeedSimulation());
                System.out.println("Hive temperature AFTER: " + hive.getTemp());
            }
        }

    }

    // ***************************************************************************
    // * Method name:        beesPerception                                      *
    // * Method description: Call the perception of each                         *
    // * bee and do the temperature control                                      *
    // * Input params:       Hive                                                *
    // * Input params:       Environment                                         *
    // * Output params:       Void                                               *
    // ***************************************************************************
    private static void beesPerception(Hive hive, Environment environment) {

        for (int i = 0; i < hive.getBee().length; i++) {
            hive.getBee()[i].perception();
        }
        heatTransfer(hive, environment);
    }


    // ***************************************************************************
    // * Method name:        heatTransfer                                        *
    // * Method description: Set the temperature of the Hive                     *
    // if have necessary changes                                                 *
    // * Input params:       Hive                                                *
    // * Input params:       Environment                                         *
    // * Output params:       Void                                               *
    // ***************************************************************************
    private static void heatTransfer(Hive hive, Environment environment) {

        //hiveTemp = Bees.contribution hiveTemp + (envTemp-hiveTemp)/hiveCoeffTransfer
        double temp = Bee.dTempBees + hive.getTemp() + (environment.getTemp() - hive.getTemp()) / hive.getHeatCoeffTransfer();

        hive.setTemp(Bee.dTempBees + hive.getTemp() + (environment.getTemp() - hive.getTemp()) / hive.getHeatCoeffTransfer());
        Bee.dTempBees = 0.0;
    }

}
