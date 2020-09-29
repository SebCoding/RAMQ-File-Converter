package ramqfileconverter;

import java.util.List;
import org.w3c.dom.Document;
import java.io.File;
import com.sun.org.apache.xml.internal.serialize.OutputFormat;
import com.sun.org.apache.xml.internal.serialize.XMLSerializer;
import java.io.IOException;
import java.io.FileOutputStream;
import java.util.ArrayList;
import com.sun.org.apache.xerces.internal.dom.DocumentImpl;
import javax.xml.parsers.DocumentBuilderFactory;
import javax.xml.parsers.DocumentBuilder;
import javax.xml.parsers.FactoryConfigurationError;
import javax.xml.parsers.ParserConfigurationException;
import org.w3c.dom.Element;
import javax.xml.transform.TransformerFactory;
import javax.xml.transform.Transformer;
import javax.xml.transform.Source;
import javax.xml.transform.dom.DOMSource;
import javax.xml.transform.Result;
import javax.xml.transform.stream.StreamResult;
import javax.xml.transform.TransformerConfigurationException;
import javax.xml.transform.TransformerException;
import javax.xml.transform.OutputKeys;
import java.io.OutputStream;
import org.xml.sax.SAXException;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;


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
class XMLConverter {

    final private String FICH_CTRL_SchemaURL = "http://tempuri.org/FICH_CTRL.xsd";
    final private String DP_RACIN_SchemaURL = "http://tempuri.org/DP_MED.xsd";

    private DocumentBuilderFactory domFactory = null;
    private DocumentBuilder domBuilder = null;

    private XMLValidator xmlValidator;

    public XMLConverter() throws ParserConfigurationException,
            SAXNotSupportedException, SAXNotRecognizedException, SAXException {
        domFactory = DocumentBuilderFactory.newInstance();
        domBuilder = domFactory.newDocumentBuilder();
        xmlValidator = new XMLValidator();
    }

    public File toXML(FICH_CTRL fichCtrl, String path) throws IOException,
            SAXException {
        File result;
        Document doc = domBuilder.newDocument();
        // Root element
        Element rootElement = doc.createElementNS(FICH_CTRL_SchemaURL,
                                                  "ns0:FICH_CTRL");
        doc.appendChild(rootElement);

        int nbFICH_DP = fichCtrl.getNbFichDP();
        for(int i = 0 ; i < nbFICH_DP ; i++){

            FICH_DP fDP = fichCtrl.getFichDP(i);
            Element curElement = doc.createElement("FICH_DP");
            curElement.setAttribute("NO_ATTES", fDP.NO_ATTES);
            curElement.setAttribute("NBR_TOT_DP", fDP.NBR_TOT_DP);
            curElement.setAttribute("TOT_ENVOI", fDP.TOT_ENVOI);
            curElement.setAttribute("REMU", fDP.REMU);
            curElement.setAttribute("NOM_FICH", fDP.NOM_FICH);
            curElement.setAttribute("DEVPR", fDP.DEVPR);
            curElement.setAttribute("LOGCL", fDP.LOGCL);
            curElement.setAttribute("VERSI", fDP.VERSI);
            rootElement.appendChild(curElement);
        }

        result = writeXmlFile(doc, fichCtrl.fileName, path);
        xmlValidator.validateXMLControlFileDoc(doc);
        return result;
    }

    public File toXML(DP_RACIN dpRacin, String path) throws SAXException,
            IOException {
        File result;
        Document doc = domBuilder.newDocument();
        // Root element
        Element rootElement = doc.createElementNS(DP_RACIN_SchemaURL,
                                                  "ns0:DP_RACIN");
        doc.appendChild(rootElement);

        int nbDP = dpRacin.getNbDP();
        for(int i = 0 ; i < nbDP ; i++){

            // DP Element
            DP dp = dpRacin.getDP(i);
            Element dpElem = doc.createElement("DP");

            // Set DP attributes
            if(!isEmptyOrZeros(dp.CHN))
                dpElem.setAttribute("CHN",dp.CHN);

            if(!isEmptyOrZeros(dp.ENRG))
                dpElem.setAttribute("ENRG",dp.ENRG);

            // mandatory attributes
            dpElem.setAttribute("TRNSM",dp.TRNSM);
            dpElem.setAttribute("DISP",dp.DISP);

            if(!isEmptyOrZeros(dp.CPTE_ADMIN))
                dpElem.setAttribute("CPTE_ADMIN",dp.CPTE_ADMIN);

            // mandatory attributes
            dpElem.setAttribute("ATTES",dp.ATTES);
            dpElem.setAttribute("NCE",dp.NCE);

            if(!isEmptyOrZeros(dp.DISP_REFNT))
                dpElem.setAttribute("DISP_REFNT",dp.DISP_REFNT);

            // mandatory attribute
            dpElem.setAttribute("DIAGN",dp.DIAGN);

            if(!isEmptyOrZeros(dp.ETAB))
                dpElem.setAttribute("ETAB",dp.ETAB);

            if(!isEmptyOrZeros(dp.ADMIS))
                dpElem.setAttribute("ADMIS",dp.ADMIS);

            if(!isEmptyOrZeros(dp.SORTI))
                dpElem.setAttribute("SORTI",dp.SORTI);

            if(!isEmptyOrZeros(dp.KM))
                dpElem.setAttribute("KM",dp.KM);

            if(!isEmptyOrZeros(dp.MNT_KM))
                dpElem.setAttribute("MNT_KM",dp.MNT_KM);

            if(!isEmptyOrZeros(dp.ACCID))
                dpElem.setAttribute("ACCID",dp.ACCID);

            dpElem.setAttribute("TOT_DEM",dp.TOT_DEM);

            if(!isEmptyOrZeros(dp.COMPL))
                dpElem.setAttribute("COMPL",dp.COMPL);

            if(!isEmptyOrZeros(dp.CS))
                dpElem.setAttribute("CS",dp.CS);

            // ACTE
            int nbActes = dp.nbActes;
            for(int a = 0 ; a < nbActes ; a++){
                ACTE currA = dp.Actes[a];
                Element acteElem = doc.createElement("ACTE");

                // Set ACTE attributes

                // Mandatory attribute
                acteElem.setAttribute("DAT", currA.DAT);

                if(!isEmptyOrZeros(currA.PLAGE_HRE))
                    acteElem.setAttribute("PLAGE_HRE", currA.PLAGE_HRE);

                // Mandatory attributes
                acteElem.setAttribute("ACTE", currA.ACTE);
                acteElem.setAttribute("ROLE", currA.ROLE);

                if(!isEmptyOrZeros(currA.MODIF))
                    acteElem.setAttribute("MODIF", currA.MODIF);

                if(!isEmptyOrZeros(currA.UNIT))
                    acteElem.setAttribute("UNIT", currA.UNIT);

                if(!isEmptyOrZeros(currA.MNT))
                    acteElem.setAttribute("MNT", currA.MNT);

                dpElem.appendChild(acteElem);
            }

            // PERS_ASSU Element
            PERS_ASSU pa = dp.pers;
            Element persAssuElem = doc.createElement("PERS_ASSU");

            // Set PERS_ASSU attributes
            if(!isEmptyOrZeros(pa.NAM))
                persAssuElem.setAttribute("NAM",pa.NAM);

            if(!isEmptyOrZeros(pa.PRE))
                persAssuElem.setAttribute("PRE",pa.PRE);

            if(!isEmptyOrZeros(pa.NOM))
                persAssuElem.setAttribute("NOM",pa.NOM);

            if(!isEmptyOrZeros(pa.NAISS))
                persAssuElem.setAttribute("NAISS",pa.NAISS);

            if(!isEmptyOrZeros(pa.SEXE))
                persAssuElem.setAttribute("SEXE",pa.SEXE);

            if(!isEmptyOrZeros(pa.CAM))
                persAssuElem.setAttribute("CAM",pa.CAM);

            if(!isEmptyOrZeros(pa.EXPIR_CAM))
                persAssuElem.setAttribute("EXPIR_CAM",pa.EXPIR_CAM);

            if(!isEmptyOrZeros(pa.ADR_1))
                persAssuElem.setAttribute("ADR_1",pa.ADR_1);

            if(!isEmptyOrZeros(pa.ADR_2))
                persAssuElem.setAttribute("ADR_2",pa.ADR_2);

            if(!isEmptyOrZeros(pa.CP))
                persAssuElem.setAttribute("CP",pa.CP);

            dpElem.appendChild(persAssuElem);

            // VISITE
            int nbVisites = dp.nbVisites;
            for(int v = 0 ; v < nbVisites ; v++){
                VISITE currV = dp.Visites[v];
                Element visiteElem = doc.createElement("VISITE");

                // Set VISITE attributes (all mandatory exept 'MNT')
                visiteElem.setAttribute("AN_MOIS", currV.AN_MOIS);

                // JR
                int nbJR = currV.getNbJR();
                String tmp = "";
                for(int j = 0 ; j < nbJR ; j++){
                    tmp += currV.getJR(j);
                }
                visiteElem.setAttribute("JR", tmp);
                visiteElem.setAttribute("ACTE", currV.ACTE);
                visiteElem.setAttribute("NBR", currV.NBR);

                if(!isEmptyOrZeros(currV.MNT))
                    visiteElem.setAttribute("MNT", currV.MNT);

                dpElem.appendChild(visiteElem);
            }

            rootElement.appendChild(dpElem);
        }

        result = writeXmlFile(doc, dpRacin.fileName, path);
        xmlValidator.validateXMLDPFile(doc);
        return result;
    }

/*
    private File writeXmlFile(Document doc, String filename, String path){

        File file = null;

        // Byte Order Mark
        byte[] utf8BOM = new byte[3];

        utf8BOM[0] = (byte)0xEF;
        utf8BOM[1] = (byte)0xBB;
        utf8BOM[2] = (byte)0xBF;

        try {
            file = new File(path + filename);
            file.deleteOnExit();
            FileOutputStream outputStream = new FileOutputStream(file);

            // Write the UTF-8 BOM at the beginning of the file
            outputStream.write(utf8BOM);

            // output with a serializer
            OutputFormat format = new OutputFormat(doc);
            format.setIndenting(true); // for nicer output
            XMLSerializer serial = new XMLSerializer(outputStream, format);
            serial.serialize(doc);
        }
        catch(Exception e){
            System.err.println(e);
        }
        return file;
    }
*/


 // This method writes a DOM document to a file
    private File writeXmlFile(Document doc, String filename, String path) {

        // UTF-8 Byte Order Mark
        byte[] utf8BOM = new byte[3];
        utf8BOM[0] = (byte)0xEF;
        utf8BOM[1] = (byte)0xBB;
        utf8BOM[2] = (byte)0xBF;

        FileOutputStream outS;
        File result = new File(path+filename);
        result.deleteOnExit();

        try {
            outS = new FileOutputStream(result);

            // Write the UTF-8 BOM at the beginning of the file
            outS.write(utf8BOM);

            // Save the document to the disk file
            TransformerFactory tranFactory = TransformerFactory.newInstance();
            Transformer transformer = tranFactory.newTransformer();

            transformer.setOutputProperty(OutputKeys.ENCODING,"UTF-8");
            transformer.setOutputProperty(OutputKeys.INDENT, "yes");

            Source src = new DOMSource(doc);
            Result dest = new StreamResult(outS);
            transformer.transform(src, dest);
        }
        catch(Exception e) {
            System.err.println(e);
        }
        return result;
    }

    private boolean isEmptyOrZeros(String s){
        return (s.length() == 0) || containsOnlyZeros(s);
    }

    private boolean containsOnlyZeros(String s){
        int size = s.length();
        for(int i = 0 ; i < size ; i++){
            if(s.charAt(i) != '0'){
               return false;
            }
        }
        return true;
    }

}
