package peices;

import GameLogic.point;
import GameLogic.pointvalueenum;

import java.util.LinkedList;

import static GameLogic.getpointvalue.pointvalidation;

public class bishop extends peices {
    public bishop() {
        super();
        super.assign(peicesimgvalue.bishop);
    }

    @Override
    public void setmove(int index,LinkedList<peices> pc) {

        LinkedList<Integer> out = new LinkedList<>();
        out.add(index);
        int i;
        boolean Xb=true,Yb=true,Xnb=true,Ynb=true;
        for(i=1;i<8;i++)
        {
            int X=i,Y=i,Xn=i*-1,Yn=i*-1;

            if(Xb)
                Xb=quadbishop( out , X, Y, index, pc);
            if(Yb)
                Yb=quadbishop(out , X, Yn, index, pc);
            if(Xnb)
                Xnb=quadbishop(out , Xn, Yn, index, pc);
            if(Ynb)
                Ynb=quadbishop( out , Xn, Y, index, pc);

        }

        this.move=out.stream().mapToInt(j->j).toArray();

        return;
    }

    private boolean quadbishop(LinkedList<Integer> out , int X, int Y, int index, LinkedList<peices> pc)
    {

        if(bishopcal(pointvalueenum.vaild,out,X,Y,index,pc)) {
            out.add(index + X + (Y * 8));
            return true;
        }
        else if(bishopcal(pointvalueenum.enemy,out,X,Y,index,pc))
            out.add(index + X + (Y * 8));

        return false;

    }


    private boolean bishopcal(pointvalueenum pve, LinkedList<Integer> out , int x, int y, int index, LinkedList<peices> pc)
    {
        if(pointvalidation(pc.get(index).getType(),index,x,y,pc)== pve)
            return true;
        return false;
    }
}
