package peices;

import GameLogic.pointvalueenum;

import java.util.LinkedList;

import static GameLogic.getpointvalue.pointvalidation;

public class king extends peices {
    public  king() {
        super();
        super.assign(peicesimgvalue.king);
    }

    @Override
    public void setmove(int index,LinkedList<peices> pc) {
        LinkedList<Integer> out = new LinkedList<>();
        out.add(index);

        for(int i=-1;i<=1;i++)
        {
            for(int j=-1;j<=1;j++)
            {
                if(kingcal(pointvalueenum.vaild,out,i,j,index,pc))
                    out.add(index + i + (j * 8));
                if(kingcal(pointvalueenum.enemy,out,i,j,index,pc))
                    out.add(index + i + (j * 8));
                if(kingcal(pointvalueenum.king,out,i,j,index,pc))
                    out.add(index + i + (j * 8));
            }

        }

        this.move=out.stream().mapToInt(j->j).toArray();

        return;
    }

    private boolean kingcal(pointvalueenum pve, LinkedList<Integer> out , int x, int y, int index, LinkedList<peices> pc)
    {
        if(pointvalidation(pc.get(index).getType(),index,x,y,pc)== pve)
            return true;
        return false;
    }

}
