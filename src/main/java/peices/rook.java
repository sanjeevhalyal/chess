package peices;

import GameLogic.pointvalueenum;

import java.util.LinkedList;

import static GameLogic.getpointvalue.pointvalidation;

public class rook extends peices {
    public rook() {
        super();
        super.assign(peicesimgvalue.rook);
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
                Xb=quadrook( out , 0, Y, index, pc);
            if(Yb)
                Yb=quadrook(out , X, 0, index, pc);
            if(Xnb)
                Xnb=quadrook(out , Xn, 0, index, pc);
            if(Ynb)
                Ynb=quadrook( out , 0, Yn, index, pc);

        }

        this.move=out.stream().mapToInt(j->j).toArray();

        return;
    }

    private boolean quadrook(LinkedList<Integer> out , int X, int Y, int index, LinkedList<peices> pc)
    {

        if(rookcal(pointvalueenum.vaild,out,X,Y,index,pc)) {
            out.add(index + X + (Y * 8));
            return true;
        }
        else if(rookcal(pointvalueenum.enemy,out,X,Y,index,pc))
            out.add(index + X + (Y * 8));

        return false;

    }


    private boolean rookcal(pointvalueenum pve, LinkedList<Integer> out , int x, int y, int index, LinkedList<peices> pc)
    {
        if(pointvalidation(pc.get(index).getType(),index,x,y,pc)== pve)
            return true;
        return false;
    }
}
