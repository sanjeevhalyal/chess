package GameLogic;

import peices.peices;
import peices.peicesimgvalue;

import java.util.LinkedList;

public class getpointvalue {

    public static boolean isvalid(int index,int x,int y)
    {
        if((index/8)+y>7 || (index/8)+y<0)
            return false;
        if((index%8)+x>7 || (index%8)+x<0)
            return false;
        return true;
    }

    public static pointvalueenum pointvalidation(String type,int index,int x, int y,LinkedList<peices> pc)
    {
        if(!isvalid(index,x,y)) {
            return pointvalueenum.invalid;
        }
        int updateindex=index+y*8+x;


        if(pc.get(updateindex).getPiv()== peicesimgvalue.open)
            return pointvalueenum.vaild;


        if(type.compareTo(pc.get(updateindex).getType())==0)
            return pointvalueenum.friendly;
        else if(pc.get(updateindex).getClass().getName().compareTo("king")==0)
        {
            return pointvalueenum.king;
        }
        else {
            return pointvalueenum.enemy;
        }


    }
}
