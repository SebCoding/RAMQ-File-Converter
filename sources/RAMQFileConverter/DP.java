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
class DP {

    private final int MAX_ACTES = 3;
    private final int MAX_VISITES = 3;

    // Attributes
    String CHN;
    String ENRG;
    String TRNSM;
    String DISP;
    String CPTE_ADMIN;
    String ATTES;
    String NCE;
    String DISP_REFNT;
    String DIAGN;
    String ETAB;
    String ADMIS;
    String SORTI;
    String KM;
    String MNT_KM;
    String ACCID;
    String TOT_DEM;
    String COMPL;
    String CS;

    // PERS_ASSU
    PERS_ASSU pers;

    // ACTES 0..3
    ACTE[] Actes;
    int nbActes;

    //VISITE 0..3
    VISITE[] Visites;
    int nbVisites;

    public DP() {
        pers = new PERS_ASSU();
        Actes = new ACTE[MAX_ACTES];
        Visites = new VISITE[MAX_VISITES];
        nbActes = 0;
        nbVisites = 0;
    }

    public void addActe(ACTE a){

        if(nbActes < MAX_ACTES){
            Actes[nbActes++] = a;
        }
    }

    public void addVisite(VISITE v){

        if(nbVisites < MAX_VISITES){
            Visites[nbVisites++] = v;
        }
    }

    public String toString() {
        StringBuffer sb = new StringBuffer();

        // DP Attributes
        sb.append("CHN = "+CHN+"\n");
        sb.append("ENRG = "+ENRG+"\n");
        sb.append("TRNSM = "+TRNSM+"\n");
        sb.append("DISP = "+DISP+"\n");
        sb.append("CPTE_ADMIN = "+CPTE_ADMIN+"\n");
        sb.append("ATTES = "+ATTES+"\n");
        sb.append("NCE = "+NCE+"\n");
        sb.append("DISP_REFNT = "+DISP_REFNT+"\n");
        sb.append("DIAGN = "+DIAGN+"\n");
        sb.append("ETAB = "+ETAB+"\n");
        sb.append("ADMIS = "+ADMIS+"\n");
        sb.append("SORTI = "+SORTI+"\n");
        sb.append("KM = "+KM+"\n");
        sb.append("MNT_KM = "+MNT_KM+"\n");
        sb.append("ACCID = "+ACCID+"\n");
        sb.append("TOT_DEM = "+TOT_DEM+"\n");
        sb.append("COMPL = "+COMPL+"\n");
        sb.append("CS = "+CS+"\n");

        // PERS_ASSU
        sb.append(pers.toString());

        // Actes
        for (int i = 0; i < nbActes; i++) {
            sb.append("ACTE #" + i + ":\n");
            sb.append(Actes[i].toString());
        }

        // Visites
        for (int i = 0; i < nbVisites; i++) {
            sb.append("VISITE #" + i + ":\n");
            sb.append(Visites[i].toString());
        }
        return sb.toString();
    }
}
