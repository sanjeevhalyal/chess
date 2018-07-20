package peices;

import GameLogic.pointvalueenum;

import java.util.LinkedList;

import static GameLogic.getpointvalue.pointvalidation;

public class knight extends peices {
    public  knight() {
        super();
        super.assign(peicesimgvalue.knight);
    }
    @Override
    public void setmove(int index,LinkedList<peices> pc) {
        LinkedList<Integer> out = new LinkedList<>();
        out.add(index);


        quadknight(out,2,1,index,pc);
        quadknight(out,2,-1,index,pc);
        quadknight(out,-2,1,index,pc);
        quadknight(out,-2,-1,index,pc);




        this.move=out.stream().mapToInt(j->j).toArray();

        return;
    }

    private boolean quadknight(LinkedList<Integer> out , int X, int Y, int index, LinkedList<peices> pc)
    {

        if(knightcal(pointvalueenum.vaild,out,X,Y,index,pc)) {
            out.add(index + X + (Y * 8));
            return true;
        }
        else if(knightcal(pointvalueenum.enemy,out,X,Y,index,pc))
            out.add(index + X + (Y * 8));

        return false;

    }

    private boolean knightcal(pointvalueenum pve, LinkedList<Integer> out , int x, int y, int index, LinkedList<peices> pc)
    {
        if(pointvalidation(pc.get(index).getType(),index,x,y,pc)== pve)
            return true;
        return false;
    }
}
