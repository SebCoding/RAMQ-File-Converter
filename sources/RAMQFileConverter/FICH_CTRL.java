package ramqfileconverter;

import java.util.List;
import java.util.ArrayList;

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
class FICH_CTRL {

    String fileName = "FICH_CTRL.XML";
    String firstNumAttes;

    private List fdpList = new ArrayList();

    public void addFichDP(FICH_DP fdp){
        fdpList.add(fdp);
    }

    public FICH_DP getFichDP(int i){
        return (FICH_DP) fdpList.get(i);
    }

    public int getNbFichDP(){
        return fdpList.size();
    }

    public String toString(){
        int size = fdpList.size();
        StringBuffer sb = new StringBuffer();

        for(int i = 0 ; i < size ; i++){
            sb.append("FICH_DP #"+i+":\n");
            sb.append(((FICH_DP)fdpList.get(i)).toString());
        }
        return sb.toString();
    }
}
