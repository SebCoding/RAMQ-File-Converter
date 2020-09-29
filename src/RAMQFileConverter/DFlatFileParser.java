package ramqfileconverter;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.FileReader;
import java.io.IOException;
import java.io.BufferedReader;

/**
 * <p>Title: RAMQFileConverter</p>
 *
 * <p>Description: Application to convert RAMQ reclamation files to XML</p>
 *
 * <p>Copyright: Copyright (c) 2007</p>
 *
 * <p>Company: Sebastien Vezina: vezinase@gmail.com</p>
 *
 * @author Sebastien Vezina: vezinase@gmail.com
 * @version 1.1
 */
class DFlatFileParser {

    //private File file;

    public DFlatFileParser() { }

    public DP_RACIN parseFile(File f) throws Exception {

        String line;
        DP_RACIN dpRacin = new DP_RACIN(f.getName());
        FileReader fr = new FileReader(f);
        BufferedReader inFile = new BufferedReader(fr);

        line = inFile.readLine();

        // Get NO_ATTES
        //dpRacin.setNoAttes(line.substring(10, 10+7).trim());

        while((line != null) && (line.length() >= 719)){
            parseLine(dpRacin, line);
            //System.err.println(dpRacin);
            line = inFile.readLine();
        }

        inFile.close();
        return dpRacin;
    }

    private void parseLine(DP_RACIN dpRacin, String line)
            throws FileFormatException, InvalidEntryException {

        DP dp = new DP();
        PERS_ASSU pa = new PERS_ASSU();
        dp.pers = pa;

        // Compiler will do the additions before generating bytecode
        dp.CHN = line.substring(0,2).trim();
        dp.ENRG = line.substring(2, 2+1).trim();

        dp.TRNSM = line.substring(5, 5+5).trim();

        // Used for testing
        //dp.TRNSM = "16998";

        dp.ATTES = line.substring(10, 10+7).trim();
        dp.NCE = line.substring(18, 18+4).trim();

        pa.NAM = line.substring(22, 22+12).trim();
        pa.PRE = line.substring(34, 34+20).trim();
        pa.NOM = line.substring(54, 54+30).trim();
        pa.CAM = line.substring(84, 84+2).trim();
        pa.EXPIR_CAM = line.substring(86, 86+4).trim();
        pa.NAISS = line.substring(90, 90+8).trim();
        pa.SEXE = line.substring(98, 98+1).trim();
        pa.ADR_1 = line.substring(99, 99+30).trim();
        pa.ADR_2 = line.substring(129, 129+30).trim();
        pa.CP = line.substring(159, 159+6).trim();

        dp.DISP = line.substring(165, 165+6).trim();
        dp.CPTE_ADMIN = line.substring(172, 172+4).trim();
        dp.DISP_REFNT = line.substring(177, 177+1).trim();
        dp.DIAGN = line.substring(184, 184+4).trim();
        dp.ETAB = line.substring(188, 188+5).trim();
        dp.ADMIS = line.substring(193, 193+6).trim();
        dp.SORTI = line.substring(199, 199+6).trim();
        dp.KM = line.substring(205, 205+3).trim();
        dp.MNT_KM = line.substring(208, 208+5).trim();

        // ACTES
        ACTE a1 = new ACTE();
        ACTE a2 = new ACTE();
        ACTE a3 = new ACTE();

        a1.DAT = line.substring(240, 240+6).trim();
        a1.PLAGE_HRE = line.substring(246, 246+1).trim();
        a1.ACTE = line.substring(247, 247+5).trim();
        a1.ROLE = line.substring(252, 252+1).trim();
        a1.MODIF = line.substring(253, 253+3).trim();
        a1.UNIT = line.substring(256, 256+3).trim();
        a1.MNT = line.substring(259, 259+6).trim();
        if(!a1.isEmpty())
            dp.addActe(a1);

        a2.DAT = line.substring(265, 265+6).trim();
        a2.PLAGE_HRE = line.substring(271, 271+1).trim();
        a2.ACTE = line.substring(272, 272+5).trim();
        a2.ROLE = line.substring(277, 277+1).trim();
        a2.MODIF = line.substring(278, 278+3).trim();
        a2.UNIT = line.substring(281, 281+3).trim();
        a2.MNT = line.substring(284, 284+6).trim();
        if(!a2.isEmpty())
            dp.addActe(a2);

        a3.DAT = line.substring(290, 290+6);
        a3.PLAGE_HRE = line.substring(296, 296+1).trim();
        a3.ACTE = line.substring(297, 297+5).trim();
        a3.ROLE = line.substring(302, 302+1).trim();
        a3.MODIF = line.substring(303, 303+3).trim();
        a3.UNIT = line.substring(306, 306+3).trim();
        a3.MNT = line.substring(309, 309+6).trim();
        if(!a3.isEmpty())
            dp.addActe(a3);

        // Visites
        VISITE v1 = new VISITE();
        VISITE v2 = new VISITE();
        VISITE v3 = new VISITE();

        // VISITE #1
        v1.AN_MOIS = line.substring(323, 323+4).trim();
        v1.addJR(line.substring(327, 327+2).trim());
        for(int k = 0 ; k < 12 ; k += 2){
            String tmp = line.substring(329+k, 329+k+2).trim();
            if(!tmp.equals("00")){
                v1.addJR(tmp);
            }
        }
        v1.ACTE = line.substring(342, 342+5).trim();
        v1.NBR = line.substring(347, 347+2).trim();
        v1.MNT = line.substring(349, 349+5).trim();
        if(!v1.isEmpty()){
            dp.addVisite(v1);
        }

        // VISITE #2
        v2.AN_MOIS = line.substring(354, 354+4).trim();
        v2.addJR(line.substring(358, 358+2).trim());
        for(int k = 0 ; k < 12 ; k += 2){
            String tmp = line.substring(360+k, 360+k+2).trim();
            if(!tmp.equals("00")){
                v2.addJR(tmp);
            }
        }
        v2.ACTE = line.substring(373, 373+5).trim();
        v2.NBR = line.substring(378, 378+2).trim();
        v2.MNT = line.substring(380, 380+5).trim();
        if(!v2.isEmpty()){
            dp.addVisite(v2);
        }

        // VISITE #3
        v3.AN_MOIS = line.substring(385, 385+4).trim();
        v3.addJR(line.substring(389, 389+2).trim());
        for(int k = 0 ; k < 12 ; k += 2){
            String tmp = line.substring(391+k, 391+k+2).trim();
            if(!tmp.equals("00")){
                v3.addJR(tmp);
            }
        }
        v3.ACTE = line.substring(404, 404+5).trim();
        v3.NBR = line.substring(409, 409+2).trim();
        v3.MNT = line.substring(411, 411+5).trim();
        if(!v3.isEmpty()){
            dp.addVisite(v3);
        }

        dp.TOT_DEM = line.substring(416, 416+6).trim();
        dp.COMPL = line.substring(422, 422+200).trim();
        dp.CS = line.substring(637, 637+5).trim();
        dp.ACCID = line.substring(642, 642+6).trim();

        // Add the DP in the DP_RACIN
        dpRacin.addDP(dp);

        validateActeInVisit(dpRacin.originalFileName, dp);
    }

    private void validateActeInVisit(String filename, DP dp) throws
            InvalidEntryException {

        VISITE v;
        String extraMessage;
        int nbVisits = dp.nbVisites;

        for(int i = 0 ; i < nbVisits ; i++){
            v = dp.Visites[i];
            if(v.ACTE.equals("00000")){
                extraMessage = "An ACTE field in a VISIT element must have a value different than 0.\n";
                extraMessage += "The new files specifications of the RAMQ do not allow such a value for this field.";
                throw new InvalidEntryException(filename, "ACTE", v.ACTE,
                                                extraMessage);
            }
        }
    }
/*
    private int toInteger(String s){
        if(s.trim().equals(""))
            return -1;
        return Integer.parseInt(s);
    }
*/
}
