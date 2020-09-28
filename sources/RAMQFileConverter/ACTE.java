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
class ACTE {

    // Attributes
    String DAT;
    String PLAGE_HRE;
    String ACTE;
    String ROLE;
    String MODIF;
    String UNIT;
    String MNT;

    public boolean isEmpty(){
        if(Integer.parseInt(ROLE) == 0)
            return true;
        else
            return false;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("DAT = "+DAT+"\n");
        sb.append("PLAGE_HRE = "+PLAGE_HRE+"\n");
        sb.append("ACTE = "+ACTE+"\n");
        sb.append("ROLE = "+ROLE+"\n");
        sb.append("MODIF = "+MODIF+"\n");
        sb.append("UNIT = "+UNIT+"\n");
        sb.append("MNT = "+MNT+"\n");
        return sb.toString();
    }
}
