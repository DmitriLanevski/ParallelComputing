import java.io.File;

/**
 * Created by lanev_000 on 5.04.2016.
 */
public class StringToBigIntegerConversionException extends RuntimeException{
    private String element;
    private File file;

    public StringToBigIntegerConversionException(String element, File file , NumberFormatException e) {
        super("Unconvertable element " + element + " in file " + file.getName(), e);
        this.element = element;
        this.file = file;

    }

    public String getElement() {
        return element;
    }

    public File getFile() {
        return file;
    }
}
