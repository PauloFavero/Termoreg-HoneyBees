package favero.moreira.sma.termoreg;

import java.io.BufferedWriter;
import java.io.File;
import java.io.FileWriter;
import java.io.IOException;
import java.util.Arrays;
import java.util.Random;

public class BeeGroups {
    private static int numGroup;
    private static boolean bAbleUpdate = false;


    public static void updateGroup() {

        try {

            File file = new File("MinMax.txt");

            if (!file.exists()) {
                file.createNewFile();
            }

            FileWriter data = new FileWriter(file.getAbsoluteFile(),true);
            BufferedWriter dataBuffer = new BufferedWriter(data);

            double[] values = new double[2 * numGroup];
            double[] newInf = new double[numGroup];
            double[] newSup = new double[numGroup];
            for (int i = 0; i < values.length; i++) {
                values[i] = (double) randomGenerator();
            }
            Arrays.sort(values);
            for (int i = 0; i < numGroup; i++) {
                if(i==0){
                    dataBuffer.write(" New Experience  ");
                    dataBuffer.newLine();;
                }

                newInf[i] = values[2*i];
                newSup[i] = values[2*i + 1];

                dataBuffer.write(Double.toString(newInf[i]));
                dataBuffer.newLine();;
                dataBuffer.write(Double.toString(newSup[i]));
                dataBuffer.newLine();;
                dataBuffer.newLine();;
            }
            dataBuffer.close();
            Hive.getInstance().update(numGroup, newInf, newSup);

        } catch (IOException e) {
            e.printStackTrace();
        }


    }


    public static double randomGenerator() {

        Random r = new Random();
        double randomValue = 32 + (36 - 32) * r.nextDouble();

        return randomValue;
    }

    public static boolean getIsbAbleUpdate() {
        return bAbleUpdate;
    }

    public static void setbAbleUpdate(boolean bAbleUpdate) {
        BeeGroups.bAbleUpdate = bAbleUpdate;
    }

    public static int getNumGroup() {
        return numGroup;
    }

    public static void setNumGroup(int numGroup) {
        BeeGroups.numGroup = numGroup;
    }
}
