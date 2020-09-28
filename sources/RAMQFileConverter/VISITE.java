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
class VISITE {

    final private int MAX_JR = 7;

    String AN_MOIS;
    private String[] JR;
    String ACTE;
    String NBR;
    String MNT;

    int nbJR = 0;

    public VISITE() {
        JR = new String[7];
    }

    public void addJR(String jr){
        if(nbJR < MAX_JR){
            JR[nbJR++] = jr;
        }
    }

    public int getNbJR(){
        return nbJR;
    }

    public String getJR(int i){
        if(i < MAX_JR){
            return JR[i];
        }
        return "";
    }

    public boolean isEmpty(){

        if((Integer.parseInt(AN_MOIS) == 0) &&
           (Integer.parseInt(JR[0]) == 0) &&
           (Integer.parseInt(ACTE) == 0) &&
           (Integer.parseInt(NBR) == 0) &&
           (Integer.parseInt(MNT) == 0) &&
           (nbJR <= 1))
        {
            return true;
        }
        return false;
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();
        sb.append("AN_MOIS = "+AN_MOIS+"\n");
        for(int i = 0 ; i < nbJR ; i++){
            sb.append("JR["+i+"] = "+JR[i]+"\n");
        }
        sb.append("ACTE = "+ACTE+"\n");
        sb.append("NBR = "+NBR+"\n");
        sb.append("MNT = "+MNT+"\n");
        return sb.toString();
  }

}
