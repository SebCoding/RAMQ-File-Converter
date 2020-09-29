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
class DFilesCountException extends Exception {

    private boolean tooManyDFiles;
    private String controlFileName;

    public DFilesCountException(String cFileName, boolean value){
        controlFileName = cFileName;
        tooManyDFiles = value;
    }

    public String getInfo(){
        StringBuffer sb = new StringBuffer();
        sb.append("The ");

        if(tooManyDFiles){
            sb.append(" list of files contains too many D files.\n");
            sb.append("These files are not refered by the ");
            sb.append(controlFileName);
            sb.append(" control file.\n");
            sb.append("Please add only D files that correspond to the R file.");
        }
        else{
            sb.append(controlFileName+" control file");
            sb.append(" makes reference to D files that are not in the list of files to convert.\n");
            sb.append("Please make sure that you select all the D files that correspond to the R file.");
        }
        return sb.toString();
    }
}
