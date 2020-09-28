package ramqfileconverter;

/**
 * <p>Title: RAMQFileConverter</p>
 *
 * <p>Description: Application to convert RAMQ reclamation files to XML</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Sebastien Vezina</p>
 *
 * @author Sebastien Vezina: vezinase@gmail.com
 * @version 1.1
 */
class FileFormatException extends Exception {

    private String fileName;
    private String exceptionInfo;

    public FileFormatException(String fileName, String message) {
        this.fileName = fileName;
        exceptionInfo = message;
    }

    public String getFileName() {
        return fileName;
    }

    public String getInfo() {
        return exceptionInfo;
    }
}
