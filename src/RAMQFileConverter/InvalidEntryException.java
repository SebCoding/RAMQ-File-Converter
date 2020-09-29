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
public class InvalidEntryException extends Exception {

    private String filename;
    private String fieldName;
    private String fieldValue;
    private String extraMessage;

    public InvalidEntryException(String fn, String fieldN, String fieldV, String eM) {
        filename = fn;
        fieldName = fieldN;
        fieldValue = fieldV;
        extraMessage = eM;
    }

    public String getInfo() {
        StringBuffer sb = new StringBuffer();
        sb.append("Invalid entry in file: "+filename+"\n");
        sb.append("Field: ["+fieldName+"] has invalid value: ["+fieldValue+"]\n");
        sb.append(extraMessage);
        return sb.toString();
    }
}
