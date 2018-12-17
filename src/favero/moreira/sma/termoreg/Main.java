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
                            System.out.println("percepetion");
                            hive.beesInHivePerception();
                        }
                    }
                });
            }
        });

        SimulationParameters simulation = new SimulationParameters(timerPerception, timerGraphics);
        guiWindow.setVisible(true);
        BeeGroups.updateGroup();


    }


}
