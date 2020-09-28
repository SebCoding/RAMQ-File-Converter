package ramqfileconverter;

import java.io.File;
import java.util.List;
import java.util.ArrayList;
import java.io.BufferedReader;
import java.io.FileReader;

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
class RFlatFileParser {

    private int nbFichDP;

    public FICH_CTRL parseFile(File f) throws Exception {

      String line;
      FICH_CTRL fichCtrl = new FICH_CTRL();
      FileReader fr = new FileReader(f);
      BufferedReader inFile = new BufferedReader(fr);
      nbFichDP = 0;

      // Skip fisrt line that contains 80 padding characters
      line = inFile.readLine();

      line = inFile.readLine();
      while((line != null) && (line.length() >= 35)){
          parseLine(fichCtrl, line);
          if(nbFichDP++ == 0){
              fichCtrl.firstNumAttes = fichCtrl.getFichDP(0).NO_ATTES;
          }
          //System.err.println(fichCtrl);
          line = inFile.readLine();
      }
      inFile.close();
      return fichCtrl;
  }

  private void parseLine(FICH_CTRL fCtrl, String line)
         throws FileFormatException
 {
     FICH_DP fDP = new FICH_DP();

     // Parse line
     // (Compiler will solve the additions before generating bytecode)
     fDP.NO_ATTES = line.substring(0, 0+7);

     // This field now holds only 5 characters in the new xml format
     fDP.NBR_TOT_DP = line.substring(7, 7+6).substring(1);
     fDP.TOT_ENVOI = line.substring(13, 13+9);
     fDP.NOM_FICH = line.substring(22, 22+14).trim();

     // Should always be 'M' for "Demande de paiement à l'acte pour médecins"
     fDP.REMU = "M";

     // Constant field containing the software developper id
     //fDP.DEVPR = "?????";
     // Constant field containing the software name
     //fDP.LOGCL = "FileConverter";
     // Constant field containing the software version
     //fDP.VERSI = "1.0";

     fCtrl.addFichDP(fDP);
 }

}
