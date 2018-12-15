package favero.moreira.sma.termoreg;

import org.jfree.ui.RefineryUtilities;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

public class Main extends Thread {


    public static void main(String[] args) throws InterruptedException {

        // Get the only object available
        Hive hive = Hive.getInstance();
        Environment environment = Environment.getInstance();
        Thread t = Thread.currentThread();
        t.setName("Main Thread");

        final GuiSettings guiWindow = new GuiSettings("Super Bee Simulator 2000");
        final DynamicTimeSeriesChart chart = new DynamicTimeSeriesChart("Hive Temperature");
        guiWindow.pack();
        RefineryUtilities.centerFrameOnScreen(guiWindow);

        guiWindow.setVisible(true);

        //500x729
        guiWindow.getSize();
        Timer timerGraphics = new Timer(1000, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        System.out.println("graphics");
                        guiWindow.getChart().update((float) hive.getTemp(), 2);
                    }
                });
            }
        });
        Timer timerPerception = new Timer(500, new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                EventQueue.invokeLater(new Runnable() {
                    @Override
                    public void run() {
                        if (!BeeGroups.getIsbAbleUpdate()) {
                            //guiWindow.update(hive,(Integer)1000/simulation.getSpeedSimulation());
                            //guiWindow.getChart().update((float) hive.getTemp(), (Integer) 1000 / simulation.getSpeedSimulation());
                            System.out.println("percepetion");
                            beesPerception(hive, environment);
                            //sleep(simulation.getSpeedSimulation());
                        }
                    }
                });
            }
        });
        SimulationParameters simulation = new SimulationParameters(timerPerception, timerGraphics);

        BeeGroups.updateGroup();
        SimulationParameters.startSimulation();


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
