import java.math.BigInteger;
import java.util.ArrayList;
import java.util.LinkedHashMap;

/**
 * Created by lanev_000 on 5.04.2016.
 */
public class InputOutputBundle {
    private BigInteger sumOfAllNumbers = BigInteger.valueOf(0);
    private BigInteger maxOfAllNumbers = null;
    private BigInteger minOfAllNumbers = null;
    private LinkedHashMap<String, BigInteger> maxSumFile = new LinkedHashMap<>();
    private LinkedHashMap<String, BigInteger> minSumFile = new LinkedHashMap<>();

    private Object IOBmonitor1 = new Object();

    public BigInteger getSumOfAllNumbers() {
        synchronized (IOBmonitor1){
            return sumOfAllNumbers;
        }
    }

    public BigInteger getMaxOfAllNumbers() {
        synchronized (IOBmonitor1){
            return maxOfAllNumbers;
        }
    }

    public BigInteger getMinOfAllNumbers() {
        synchronized (IOBmonitor1){
            return minOfAllNumbers;
        }
    }

    public LinkedHashMap<String, BigInteger> getMaxSumFile() {
        synchronized (IOBmonitor1){
            return maxSumFile;
        }
    }

    public LinkedHashMap<String, BigInteger> getMinSumFile() {
        synchronized (IOBmonitor1){
            return minSumFile;
        }
    }

    public void increaseSumOfAllNumbers(BigInteger sumOfAllNumbers) {
        synchronized (IOBmonitor1){
                this.sumOfAllNumbers = this.sumOfAllNumbers.add(sumOfAllNumbers);
        }
    }

    public void compareAndChangeMaxOfAllNumbers(BigInteger maxOfNumbers){
        synchronized (IOBmonitor1){
            if (this.maxOfAllNumbers == null){
                this.maxOfAllNumbers = maxOfNumbers;
            }
            else if ((this.maxOfAllNumbers.compareTo(maxOfNumbers) == -1)){
                this.maxOfAllNumbers = maxOfNumbers;
            }
        }
    }

    public void compareAndChangeMinOfAllNumbers(BigInteger maxOfNumbers){
        synchronized (IOBmonitor1){
            if (this.minOfAllNumbers == null){
                this.minOfAllNumbers = maxOfNumbers;
            }
            else if ((this.minOfAllNumbers.compareTo(maxOfNumbers) == 1)){
                this.minOfAllNumbers = maxOfNumbers;
            }
        }
    }

    public void compareAndChangeMaxSumFile(String fileName, BigInteger maxOfAllFileNumbers){
        synchronized (IOBmonitor1){
            if (this.maxSumFile.isEmpty()){
                this.maxSumFile.put(fileName, maxOfAllFileNumbers);
            }
            else {
                String key = (new ArrayList<String>(this.maxSumFile.keySet())).get(0);
                BigInteger value = (new ArrayList<BigInteger>((this.maxSumFile.values())).get(0));
                if (value.compareTo(maxOfAllFileNumbers) == -1){
                    this.maxSumFile.remove(key);
                    this.maxSumFile.put(fileName, maxOfAllFileNumbers);
                }
            }
        }
    }

    public void compareAndChangeMinSumFile(String fileName, BigInteger minOfAllFileNumbers){
        synchronized (IOBmonitor1){
            if (this.minSumFile.isEmpty()){
                this.minSumFile.put(fileName, minOfAllFileNumbers);
            }
            else {
                String key = (new ArrayList<String>(this.minSumFile.keySet())).get(0);
                BigInteger value = (new ArrayList<BigInteger>((this.minSumFile.values())).get(0));
                if (value.compareTo(minOfAllFileNumbers) == -1){
                    this.minSumFile.remove(key);
                    this.minSumFile.put(fileName, minOfAllFileNumbers);
                }
            }
        }

    }

    public void setSumOfAllNumbers(BigInteger sumOfAllNumbers) {
        synchronized (IOBmonitor1){
            this.sumOfAllNumbers = sumOfAllNumbers;
        }
    }

    public void setMaxOfAllNumbers(BigInteger maxOfAllNumbers) {
        synchronized (IOBmonitor1){
            this.maxOfAllNumbers = maxOfAllNumbers;
        }
    }

    public void setMinOfAllNumbers(BigInteger minOfAllNumbers) {
        synchronized (IOBmonitor1){
            this.minOfAllNumbers = minOfAllNumbers;
        }
    }

    public void setMaxSumFile(LinkedHashMap<String, BigInteger> msxSumFile) {
        synchronized (IOBmonitor1){
            this.maxSumFile = msxSumFile;
        }
    }

    public void setMinSumFile(LinkedHashMap<String, BigInteger> minSumFile) {
        synchronized (IOBmonitor1){
            this.minSumFile = minSumFile;
        }
    }
}
