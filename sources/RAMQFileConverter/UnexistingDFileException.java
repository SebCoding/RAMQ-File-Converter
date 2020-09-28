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
class UnexistingDFileException extends Exception {

    private String fileName;
    private String controlFileName;

    public UnexistingDFileException(String fName, String cFileName){
        fileName = fName;
        controlFileName = cFileName;
    }

    public String getInfo(){
        return "The file <"+fileName+"> is refered by the control file <"+
                controlFileName+"> but is not in the list of files to convert.\n"+
                "Please select all the files files needed before conversion.";
    }
}
