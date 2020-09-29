package ramqfileconverter;

import java.util.List;
import java.io.File;
import java.util.zip.ZipOutputStream;
import java.io.FileOutputStream;
import java.io.FileInputStream;
import java.util.zip.ZipEntry;
import java.io.IOException;

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
class FileZipper {

    public FileZipper() { }

    public File zipFiles(List files, String path, String outFileName,
                         boolean deleteFiles) {

        // Nothing to do
        if((files == null) || (files.size() == 0))
            return null;

        // Create a buffer for reading the files
        byte[] buf = new byte[1024];

        try {
            // Create the ZIP file
            ZipOutputStream out = new ZipOutputStream(new FileOutputStream(path+outFileName));

            // Compress the files
            for (int i=0; i <(int) files.size(); i++) {

                File current = (File) files.get(i);
                FileInputStream in = new FileInputStream(current);

                // Add ZIP entry to output stream.
                out.putNextEntry(new ZipEntry(current.getName()));

                // Transfer bytes from the file to the ZIP file
                int len;
                while ((len = in.read(buf)) > 0) {
                    out.write(buf, 0, len);
                }

                // Complete the entry
                out.closeEntry();
                in.close();
            }

            // Complete the ZIP file
            out.close();
        } catch (IOException e) {
            System.err.println(e);
        }

        if(deleteFiles){
            System.gc();
            deleteFiles(files);
        }
        return null;
    }

    private void deleteFiles(List files){
        int size = files.size();
        for(int i = 0 ; i < size ; i++){
            File tmp = (File)files.get(i);
            if(tmp.delete()){
                System.err.println("Deleted file: "+tmp);
            }
            else{
                System.err.println("Unable to deleted file: "+tmp);
            }

        }
    }
}
