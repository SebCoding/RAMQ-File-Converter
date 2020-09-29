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
class FICH_DP {

    String NO_ATTES;
    String NBR_TOT_DP;
    String TOT_ENVOI;
    String REMU;
    String NOM_FICH;

    // Used for testing
    //final String DEVPR = "16998";

    final String DEVPR = "67315";
    final String LOGCL = "MedicalOffice";
    final String VERSI = "1.7";

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("NO_ATTES = "+NO_ATTES+"\n");
        sb.append("NBR_TOT_DP = "+NBR_TOT_DP+"\n");
        sb.append("TOT_ENVOI = "+TOT_ENVOI+"\n");
        sb.append("REMU = "+REMU+"\n");
        sb.append("NOM_FICH = "+NOM_FICH+"\n");
        sb.append("DEVPR = "+DEVPR+"\n");
        sb.append("LOGCL = "+LOGCL+"\n");
        sb.append("VERSI = "+VERSI+"\n");
        return sb.toString();
    }

}
