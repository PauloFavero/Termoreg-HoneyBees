package favero.moreira.sma.termoreg;

public class SimulationParameters {
    private static int speedSimulation;

    public SimulationParameters(){
        this.speedSimulation = 500;
    }
    public static void setSpeedSimulation(int speedSimulation) {
        SimulationParameters.speedSimulation = speedSimulation;
    }

    public static int getSpeedSimulation() {
        return speedSimulation;
    }

}
