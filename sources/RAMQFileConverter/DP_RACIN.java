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
 * <p>Company: Sebastien Vezina: vezinase@gmail.com</p>
 *
 * @author Sebastien Vezina
 * @version 1.1
 */
class DP_RACIN {

    String originalFileName;
    String fileName;
    private List dpList = new ArrayList();

    public DP_RACIN(String fname){
        originalFileName = fname;
        fileName = fname+".XML";
    }

    public void addDP(DP dp){
        dpList.add(dp);
    }

    public int getNbDP(){
        return dpList.size();
    }

    public DP getDP(int i){
        return (DP) dpList.get(i);
    }

/*
    public void setNoAttes(String s){
        fileName = s+".XML";
    }
*/

    public String toString(){
        int size = dpList.size();
        StringBuffer sb = new StringBuffer();

        for(int i = 0 ; i < size ; i++){
            sb.append("DP #"+i+":\n");
            sb.append(((DP)dpList.get(i)).toString());
        }
        return sb.toString();
    }
}
