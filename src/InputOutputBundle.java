import java.math.BigInteger;
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
