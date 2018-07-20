package peices;

import GameLogic.*;

import java.util.LinkedList;

import static GameLogic.getpointvalue.pointvalidation;

public class pawn extends peices {
    public pawn() {
        super();
        super.assign(peicesimgvalue.pawn);
    }




    @Override
    public void setmove(int index,LinkedList<peices> pc) {
        LinkedList<Integer> out = new LinkedList<>();
        out.add(index);
        int x=index%8,y=index/8;
        LinkedList<point> pointsvalid=new LinkedList<>();
        LinkedList<point> pointsenemy=new LinkedList<>();
        LinkedList<point> pointsking=new LinkedList<>();
        if (this.type.compareTo("light") == 0) {
            if (index / 8 == 6) {
                pointsvalid.add(new point(0,-2));
            }
            pointsvalid.add(new point(0,-1));
            pointsenemy.add(new point(1,-1));
            pointsenemy.add(new point(-1,-1));
            pointsking.add(new point(1,-1));
            pointsking.add(new point(-1,-1));


        } else
        {
            if (index / 8 == 1) {
                pointsvalid.add(new point(0,2));
            }
            pointsvalid.add(new point(0,1));
            pointsenemy.add(new point(1,1));
            pointsenemy.add(new point(-1,1));
            pointsking.add(new point(1,1));
            pointsking.add(new point(-1,1));

        }


        for(point i : pointsvalid)
            pawncal(pointvalueenum.vaild,out,i.getX(),i.getY(),index,pc);

        for(point i : pointsenemy)
            pawncal(pointvalueenum.enemy,out,i.getX(),i.getY(),index,pc);

        for(point i : pointsking)
            pawncal(pointvalueenum.king,out,i.getX(),i.getY(),index,pc);

        this.move=out.stream().mapToInt(j->j).toArray();

        return;
    }

    private void pawncal(pointvalueenum pve, LinkedList<Integer> out ,int x, int y,int index,LinkedList<peices> pc)
    {
        if(pointvalidation(pc.get(index).getType(),index,x,y,pc)== pve)
         out.add(index + x + (y * 8));

        return;
    }

}
