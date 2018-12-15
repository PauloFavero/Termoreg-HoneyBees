package favero.moreira.sma.termoreg;

import javax.swing.*;

public class SimulationParameters {


    private static int speedSimulation;

    static boolean simulationStop;

    static Timer timerPerception;
    static Timer timerGui;

    /*****************************************************************************
     * Method name: SimulationParameters
     * Method description: Constructor
     * the temperature control
     * @param
     * @return Void
     ****************************************************************************/
    public SimulationParameters() {
        this.speedSimulation = 500;
        this.simulationStop = false;
    }

    /*****************************************************************************
     * Method name: SimulationParameters
     * Method description: Overload Construction with Timers for simulation
     * @param timerPerc
     * @param timerGui
     * @return Void
     ****************************************************************************/
    public SimulationParameters(Timer timerPerc, Timer timerGui) {
        this.speedSimulation = 500;
        this.simulationStop = false;
        this.timerGui = timerGui;
        this.timerPerception = timerPerc;
    }

    /*****************************************************************************
     * Method name: setSpeedSimulation
     * Method description: Setter speedSimulation
     * @param speedSimulation
     * @return Void
     ****************************************************************************/
    public static void setSpeedSimulation(int speedSimulation) {
        SimulationParameters.speedSimulation = speedSimulation;
    }

    /*****************************************************************************
     * Method name: getSpeedSimulation
     * Method description: Getter speedSimulation
     * @param
     * @return speedSimulation
     ****************************************************************************/
    public static int getSpeedSimulation() {
        return speedSimulation;
    }
    /*****************************************************************************
     * Method name: setSimulationStop
     * Method description: Setter simulationStop
     * @param simulationStop
     * @return void
     ****************************************************************************/
    public static void setSimulationStop(boolean simulationStop) {
        SimulationParameters.simulationStop = simulationStop;
    }

    /*****************************************************************************
     * Method name: stopSimulation
     * Method description: Stop all the timer in simulation.
     * @param
     * @return Void
     ****************************************************************************/
    public static void stopSimulation() {
        SimulationParameters.timerPerception.stop();
        SimulationParameters.timerGui.stop();
    }

    /*****************************************************************************
     * Method name: startSimulation
     * Method description: Start all timer in the simulation
     * @param
     * @return Void
     ****************************************************************************/
    public static void startSimulation() {
        SimulationParameters.timerPerception.start();
        SimulationParameters.timerGui.start();
    }

    /*****************************************************************************
     * Method name: setDelayPerception
     * Method description: Set a desired delay to control bees percetion
     * in simulation
     * @param
     * @return Void
     ****************************************************************************/
    public static void setDelayPerception() {

        SimulationParameters.timerPerception.setDelay(SimulationParameters.getSpeedSimulation());
    }


    /*****************************************************************************
     * Method name: setDelayGUI
     * Method description: TODO: See how to move faster the graphics
     * @param
     * @return Void
     ****************************************************************************/
    public static void setDelayGUI() {

//        SimulationParameters.timerGui.setDelay(SimulationParameters.getSpeedSimulation());
    }

    /*****************************************************************************
     * Method name: isSimulationStop
     * Method description: Check simulation status
     * @param
     * @return simulationStop
     ****************************************************************************/
    public static boolean isSimulationStop() {
        return simulationStop;
    }

}
