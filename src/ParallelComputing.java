import java.io.File;
import java.io.FileNotFoundException;
import java.math.BigInteger;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

/**
 * Created by lanev_000 on 5.04.2016.
 */
public class ParallelComputing implements Runnable{

    private InputOutputBundle IOB;
    private File file;

    public ParallelComputing(InputOutputBundle inputOutputBundle, File file) {
        this.IOB = inputOutputBundle;
        this.file = file;
    }

    @Override
    public void run() {
        try (Scanner sc = new Scanner(file, "UTF-8")){
            BigInteger sumOfFileNumbers = BigInteger.valueOf(0);
            while(sc.hasNextLine()){
                String[] lineComponents = sc.nextLine().split(" ");
                for (String component: lineComponents) {
                    try {
                        BigInteger nextNumber = new BigInteger(component);
                        synchronized (IOB.getMonitor1()){
                            IOB.setSumOfAllNumbers(IOB.getSumOfAllNumbers().add(nextNumber));
                        }
                        synchronized (IOB.getMonitor2()) {
                            if (IOB.getMaxOfAllNumbers().compareTo(nextNumber) == -1){
                                IOB.setMaxOfAllNumbers(nextNumber);
                            }
                        }
                        synchronized (IOB.getMonitor3()) {
                            if (IOB.getMinOfAllNumbers().compareTo(nextNumber) == 1){
                                IOB.setMinOfAllNumbers(nextNumber);
                            }
                        }
                        sumOfFileNumbers = sumOfFileNumbers.add(nextNumber);
                    } catch (NumberFormatException e) {
                        //throw new StringToBigIntegerConversionException(component, file, e);
                        System.out.println("Unconvertable element " + component + " in file " + file.getName() + ".");
                    }
                }
            }
            synchronized (IOB.getMonitor4()) {
                if (IOB.getMaxSumFile().isEmpty()){
                    IOB.getMaxSumFile().put(file.getName(), sumOfFileNumbers);
                }
                String key = (new ArrayList<String>(IOB.getMaxSumFile().keySet())).get(0);
                BigInteger value = (new ArrayList<BigInteger>(IOB.getMaxSumFile().values())).get(0);
                if (value.compareTo(sumOfFileNumbers) == -1){
                    IOB.getMaxSumFile().remove(key);
                    IOB.getMaxSumFile().put(file.getName(), sumOfFileNumbers);
                }
            }
            synchronized (IOB.getMonitor5()) {
                if (IOB.getMinSumFile().isEmpty()){
                    IOB.getMinSumFile().put(file.getName(), sumOfFileNumbers);
                }
                String key = (new ArrayList<String>(IOB.getMinSumFile().keySet())).get(0);
                BigInteger value = (new ArrayList<BigInteger>(IOB.getMinSumFile().values())).get(0);
                if (value.compareTo(sumOfFileNumbers) == 1){
                    IOB.getMinSumFile().remove(key);
                    IOB.getMinSumFile().put(file.getName(), sumOfFileNumbers);
                }
            }
        } catch (FileNotFoundException e) {
            throw new RuntimeException(e);
        }

    }

    public static void main(String[] args) throws Exception{
        InputOutputBundle IOB = new InputOutputBundle();

        Scanner sc = new Scanner(System.in);
        System.out.println("Insert path to folder");
        String path = sc.nextLine();

        File folder = new File(path);
        File[] listOfFiles = folder.listFiles();
        List<File> txtFiles = new ArrayList<>();
        for (File file : listOfFiles) {
            String[] fileNameParts = file.getName().split("\\.");
            if (fileNameParts[1].equals("txt")){
                txtFiles.add(file);
            }
        }

        List<Thread> tList = new ArrayList<>();
        for (File textFile : txtFiles) {
            Thread newThread = new Thread(new ParallelComputing(IOB, textFile));
            tList.add(newThread);
            newThread.start();
        }

        for (Thread thread : tList) {
            thread.join();
        }

        System.out.println("The sum of all numbers is: " + IOB.getSumOfAllNumbers() + ".");
        System.out.println("The maximum number of all found numbers is: " + IOB.getMaxOfAllNumbers() + ".");
        System.out.println("The minimum number of all found numbers is: " + IOB.getMinOfAllNumbers() + ".");
        System.out.println("The file name with maximum sum of numbers is: " + IOB.getMaxSumFile().keySet().toArray()[0]
                + ". The sum itself is: " + IOB.getMaxSumFile().values().toArray()[0] + ".");
        System.out.println("The file name with minimum sum of numbers is: " + IOB.getMinSumFile().keySet().toArray()[0]
                + ". The sum itself is: " + IOB.getMinSumFile().values().toArray()[0] + ".");
    }
}
