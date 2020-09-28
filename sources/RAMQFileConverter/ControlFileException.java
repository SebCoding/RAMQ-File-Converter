package ramqfileconverter;

/**
 * <p>Title: RAMQFileConverter</p>
 *
 * <p>Description: Application to convert RAMQ reclamation files to XML</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Sebastien vezina</p>
 *
 * @author Sebastien vezinase: vezinase@gmail.com
 * @version 1.1
 */
class ControlFileException extends Exception {

    private String info = "The list of files to convert must contain exactly one control file.\n"+
                          "A control file's name starts with the letter 'R'.";

    public String getInfo() {
        return info;
    }
}
