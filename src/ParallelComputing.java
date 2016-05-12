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
            BigInteger maxOfAllFileNumbers = null;
            BigInteger minOfAllFileNumbers = null;
            String key;
            BigInteger value;

            while(sc.hasNextLine()){
                String[] lineComponents = sc.nextLine().split(" ");
                for (String component: lineComponents) {
                    try {
                        BigInteger nextNumber = new BigInteger(component);
                        sumOfFileNumbers = sumOfFileNumbers.add(nextNumber);
                        if (maxOfAllFileNumbers == null){
                            maxOfAllFileNumbers = nextNumber;
                        }
                        else if (maxOfAllFileNumbers.compareTo(nextNumber) == -1){
                            maxOfAllFileNumbers = nextNumber;
                        }
                        if (minOfAllFileNumbers == null){
                            minOfAllFileNumbers = nextNumber;
                        }
                        else if (minOfAllFileNumbers.compareTo(nextNumber) == 1){
                            minOfAllFileNumbers = nextNumber;
                        }
                    } catch (NumberFormatException e) {
                        //throw new StringToBigIntegerConversionException(component, file, e);
                        System.out.println("Unconvertable element " + component + " in file " + file.getName() + ".");
                    }
                }
            }

            IOB.setSumOfAllNumbers(IOB.getSumOfAllNumbers().add(sumOfFileNumbers));
            if (IOB.getMaxOfAllNumbers() == null){
                IOB.setMaxOfAllNumbers(maxOfAllFileNumbers);
            }
            else if (IOB.getMaxOfAllNumbers().compareTo(maxOfAllFileNumbers) == -1){
                IOB.setMaxOfAllNumbers(maxOfAllFileNumbers);
            }
            if (IOB.getMinOfAllNumbers() == null){
                IOB.setMinOfAllNumbers(minOfAllFileNumbers);
            }
            else if (IOB.getMinOfAllNumbers().compareTo(minOfAllFileNumbers) == 1){
                IOB.setMinOfAllNumbers(minOfAllFileNumbers);
            }

            if (IOB.getMaxSumFile().isEmpty()){
                IOB.getMaxSumFile().put(file.getName(), sumOfFileNumbers);
            }
            else {
                key = (new ArrayList<String>(IOB.getMaxSumFile().keySet())).get(0);
                value = (new ArrayList<BigInteger>(IOB.getMaxSumFile().values())).get(0);
                if (value.compareTo(sumOfFileNumbers) == -1){
                    IOB.getMaxSumFile().remove(key);
                    IOB.getMaxSumFile().put(file.getName(), sumOfFileNumbers);
                }
            }

            if (IOB.getMinSumFile().isEmpty()){
                IOB.getMinSumFile().put(file.getName(), sumOfFileNumbers);
            }
            else {
                key = (new ArrayList<String>(IOB.getMinSumFile().keySet())).get(0);
                value = (new ArrayList<BigInteger>(IOB.getMinSumFile().values())).get(0);
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
