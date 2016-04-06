import java.math.BigInteger;
import java.util.HashMap;
import java.util.LinkedHashMap;

/**
 * Created by lanev_000 on 5.04.2016.
 */
public class InputOutputBundle {
    private BigInteger sumOfAllNumbers = BigInteger.valueOf(0);
    private BigInteger maxOfAllNumbers = BigInteger.valueOf(-2147483648);
    private BigInteger minOfAllNumbers = BigInteger.valueOf(2147483647);
    private LinkedHashMap<String, BigInteger> maxSumFile = new LinkedHashMap<>();
    private LinkedHashMap<String, BigInteger> minSumFile = new LinkedHashMap<>();

    private Object monitor1 = new Object();
    private Object monitor2 = new Object();
    private Object monitor3 = new Object();
    private Object monitor4 = new Object();
    private Object monitor5 = new Object();


    public BigInteger getSumOfAllNumbers() {
        return sumOfAllNumbers;
    }

    public BigInteger getMaxOfAllNumbers() {
        return maxOfAllNumbers;
    }

    public BigInteger getMinOfAllNumbers() {
        return minOfAllNumbers;
    }

    public LinkedHashMap<String, BigInteger> getMaxSumFile() {
        return maxSumFile;
    }

    public LinkedHashMap<String, BigInteger> getMinSumFile() {
        return minSumFile;
    }

    public void setSumOfAllNumbers(BigInteger sumOfAllNumbers) {
        this.sumOfAllNumbers = sumOfAllNumbers;
    }

    public void setMaxOfAllNumbers(BigInteger maxOfAllNumbers) {
        this.maxOfAllNumbers = maxOfAllNumbers;
    }

    public void setMinOfAllNumbers(BigInteger minOfAllNumbers) {
        this.minOfAllNumbers = minOfAllNumbers;
    }

    public void setMaxSumFile(LinkedHashMap<String, BigInteger> msxSumFile) {
        this.maxSumFile = msxSumFile;
    }

    public void setMinSumFile(LinkedHashMap<String, BigInteger> minSumFile) {
        this.minSumFile = minSumFile;
    }

    public Object getMonitor1() {
        return monitor1;
    }

    public Object getMonitor2() {
        return monitor2;
    }

    public Object getMonitor3() {
        return monitor3;
    }

    public Object getMonitor4() {
        return monitor4;
    }

    public Object getMonitor5() {
        return monitor5;
    }
}
