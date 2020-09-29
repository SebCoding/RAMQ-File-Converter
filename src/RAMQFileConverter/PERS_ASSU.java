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
class PERS_ASSU {

    // Attributes
    String NAM;
    String PRE;
    String NOM;
    String NAISS;
    String SEXE;
    String CAM;
    String EXPIR_CAM;
    String ADR_1;
    String ADR_2;
    String CP;

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("NAM = "+NAM+"\n");
        sb.append("PRE = "+PRE+"\n");
        sb.append("NOM = "+NOM+"\n");
        sb.append("NAISS = "+NAISS+"\n");
        sb.append("SEXE = "+SEXE+"\n");
        sb.append("CAM = "+CAM+"\n");
        sb.append("EXPIR_CAM = "+EXPIR_CAM+"\n");
        sb.append("ADR_1 = "+ADR_1+"\n");
        sb.append("ADR_2 = "+ADR_2+"\n");
        sb.append("CP = "+CP+"\n");
        return sb.toString();
    }
}
