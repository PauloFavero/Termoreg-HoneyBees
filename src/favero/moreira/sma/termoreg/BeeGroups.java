package favero.moreira.sma.termoreg;

import java.util.Arrays;
import java.util.Random;

public class BeeGroups {
    private static int numGroup;

    public static void updateGroup(){
        double[] values = new double[2*numGroup];
        double[] newInf = new double[numGroup];
        double[] newSup = new double[numGroup];
        for(int i = 0; i< 6; i++){
            values[i] = (double) randomGenerator();
        }
        Arrays.sort(values);
        for(int i=0; i < (values.length/2); i++)
            newInf[i] = values[i];
        for(int i=0; i < (values.length/2); i++)
            newSup[i] = values[(values.length/2) + i];
        Hive.getInstance().update(numGroup, newInf, newSup);

    }


    public static double randomGenerator() {

        Random r = new Random();
        double randomValue = 32 + (36 - 32) * r.nextDouble();

        return randomValue;
    }

    public static int getNumGroup() {
        return numGroup;
    }

    public static void setNumGroup(int numGroup) {
        BeeGroups.numGroup = numGroup;
    }
}
