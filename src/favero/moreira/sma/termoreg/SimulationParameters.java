package favero.moreira.sma.termoreg;

import javax.swing.*;

public class SimulationParameters {


    private static int speedSimulation;

    static boolean simulationStop;

    static Timer timerPerception;
    static Timer timerGui;

    public SimulationParameters() {
        this.speedSimulation = 500;
        this.simulationStop = false;
    }

    public SimulationParameters(Timer timerPerc, Timer timerGui) {
        this.speedSimulation = 500;
        this.simulationStop = false;
        this.timerGui = timerGui;
        this.timerPerception = timerPerc;
    }

    public static void setSpeedSimulation(int speedSimulation) {
        SimulationParameters.speedSimulation = speedSimulation;
    }

    public static int getSpeedSimulation() {
        return speedSimulation;
    }

    public static void setSimulationStop(boolean simulationStop) {
        SimulationParameters.simulationStop = simulationStop;
    }

    public static void stopSimulation() {
        SimulationParameters.timerPerception.stop();
        SimulationParameters.timerGui.stop();
    }

    public static void startSimulation() {
        SimulationParameters.timerPerception.start();
        SimulationParameters.timerGui.start();
    }

    public static void setDelayPerception() {

        SimulationParameters.timerPerception.setDelay(SimulationParameters.getSpeedSimulation());
    }

    public static boolean isSimulationStop() {
        return simulationStop;
    }

}
