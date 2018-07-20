package GameLogic;

import com.google.gson.Gson;
import peices.*;
import peices.peicesimgvalue;
import sun.awt.image.ImageWatched;

import java.lang.reflect.Array;
import java.lang.reflect.Constructor;
import java.lang.reflect.InvocationTargetException;
import java.util.Arrays;
import java.util.LinkedList;



public class GameData {

    String status;

    public LinkedList<peices> pclist;


    public GameData() {
        try {
            resetboxlist();
//            String returnstring= new Gson().toJson(pcvaluelist);
//            System.out.println(returnstring);
        } catch (Exception ex) {
            ex.printStackTrace();
        }
    }

    public void resetboxlist() throws IllegalAccessException, InstantiationException, ClassNotFoundException, NoSuchMethodException, InvocationTargetException {
        pclist = new LinkedList<peices>();
        int index = 0;
        for (String pval : pcvaluelist.pcvaluelist) {
            String[] parr = pval.split(",");
            Class clazz = Class.forName("peices." + parr[0]);
            peices piv = (peices) clazz.newInstance();
            if (parr.length > 1) {
                piv.assign(parr[1]);
            }
            pclist.add(piv);



            index++;
        }
        updatemove();
        status = "open";
    }

    public void updatemove()
    {
        int index = 0;
        for (peices pval : pclist) {
            pval.setmove(index,pclist);
            index++;
        }
    }


}

