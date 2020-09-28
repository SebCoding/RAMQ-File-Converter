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

import java.util.List;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import org.xml.sax.SAXNotRecognizedException;
import org.xml.sax.SAXNotSupportedException;
import org.xml.sax.SAXException;
import javax.xml.parsers.ParserConfigurationException;

class FileConverter {

    private String zipFileName;
    private String conversionFolder = "/"; //"ConversionResult/";
    private XMLConverter xmlConverter;

    public FileConverter() throws SAXNotRecognizedException,
            SAXNotSupportedException, SAXException,
            ParserConfigurationException {
        xmlConverter = new XMLConverter();
    }

    public String getResultDirName(){
        return conversionFolder.substring(0, conversionFolder.length()-1);
    }

    public String convert(List files) throws Exception {

        validateFileNames(files);

        if(!filesAreInSamePath(files)) {
            throw new PathException();
        }

        if(!exactlyOneControlFile(files)){
            throw new ControlFileException();
        }

        // path to store results
        String path = getLocation((File)files.get(0))+conversionFolder;

        // create destination directory
        File resultDir = new File(path);
        if(!resultDir.exists()){
            if (resultDir.mkdir() != true) {
                throw new IOException("failed to create " + conversionFolder +
                                      " directory");
            }
        }

        FileZipper zipper = new FileZipper();

        files = parseFlatFilesAndConvert2XML(files, path);
        File zippedResult = zipper.zipFiles(files, path, zipFileName, false);

        // For some reason we need this to assure that all xml files
        // will be deleted on exit
        System.gc();
        //deleteFiles(files);
        return zipFileName;
    }

    private void deleteFiles(List files){
        int size = files.size();
        for(int i = 0 ; i < size ; i++){
            ((File)files.get(i)).delete();
        }
    }

    public List parseFlatFilesAndConvert2XML(List files, String path)
            throws Exception
    {
        File current;
        DP_RACIN dpRacin;
        FICH_CTRL fichCtrl;
        int nbFiles = files.size();
        RFlatFileParser rParser = new RFlatFileParser();
        DFlatFileParser dParser = new DFlatFileParser();
        List resultList = new ArrayList();

        for(int i = 0 ; i < nbFiles ; i++) {

            current = (File) files.get(i);

            if(current.getName().charAt(0) == 'R'){
                fichCtrl = rParser.parseFile(current);
                if(fichCtrl.getNbFichDP() > 0){
                    validateDFilesNamesAndCount(fichCtrl, current, files);
                    zipFileName = "E"+fichCtrl.firstNumAttes+".zip";
                    addXMLExtensionsToFileNames(fichCtrl);
                    //System.err.println(fichCtrl);
                    current = xmlConverter.toXML(fichCtrl, path);
                }
            }
            else {
                dpRacin = dParser.parseFile(current);
                //System.err.println(dpRacin);
                //System.err.println("Read "+dpRacin.getNbDP()+" DPs");
                current = xmlConverter.toXML(dpRacin, path);
            }
            resultList.add(current);
        }

        files.clear();
        return resultList;
    }

    private void addXMLExtensionsToFileNames(FICH_CTRL fCtrl){

        int size = fCtrl.getNbFichDP();
        for (int i = 0; i < size; i++) {
            fCtrl.getFichDP(i).NOM_FICH += ".XML";
        }
    }

    private void validateDFilesNamesAndCount(FICH_CTRL fichCtrl,
                                             File controlFile, List files)
            throws DFilesCountException, UnexistingDFileException
    {
        int nbFiles = files.size();

        // Check that we have right amount of D files
        if((nbFiles-1) < fichCtrl.getNbFichDP()){
            // Missing D files
            throw new DFilesCountException(controlFile.getName(), false);
        }
        else if((nbFiles-1) > fichCtrl.getNbFichDP()){
            // Too many D files
            throw new DFilesCountException(controlFile.getName(), true);
        }

        // Check that all D files are the right ones
        int nbDFiles = fichCtrl.getNbFichDP();
        for(int i = 0 ; i < nbDFiles ; i++){
            String tmp = fichCtrl.getFichDP(i).NOM_FICH;
            if(!inList(files, tmp)){
                throw new UnexistingDFileException(tmp, controlFile.getName());
            }
        }
    }

    private boolean inList(List files, String fileName){
        int size = files.size();
        for(int i = 0 ; i < size ; i++){
            if(((File)files.get(i)).getName().equals(fileName)){
                return true;
            }
        }
        return false;
    }


    private void validateFileNames(List files) throws FileFormatException {
        String name;
        int size = files.size();
        for(int i = 0 ; i < size ; i++) {
            name = ((File)files.get(i)).getName();
            if((name.charAt(0) == 'D') || (name.charAt(0) == 'R')) {
                continue;
            }
            else {
                throw new FileFormatException(name, "File names must start with R or D");
            }
        }
    }

    private boolean filesAreInSamePath(List files) {

        int size = files.size();
        String fisrtPath = getLocation((File)files.get(0));

        for(int i = 1 ; i < size ; i++) {
            String currentPath = getLocation((File)files.get(i));
            if(!fisrtPath.equals(currentPath)) {
                return false;
            }
        }
        return true;
    }

    private boolean exactlyOneControlFile(List files) {

        int n = 0;
        int nbFiles = files.size();

        for(int i = 0 ; i < nbFiles ; i++){
            if(((File)files.get(i)).getName().charAt(0) == 'R'){
                n++;
            }
        }
        return n == 1;
    }

    private String getLocation(File f) {
        String name = f.getName();
        String path = f.getAbsolutePath();
        int locationLength = path.length() - name.length();
        return path.substring(0, locationLength);
    }
}
