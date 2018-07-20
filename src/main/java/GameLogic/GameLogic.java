package GameLogic;

import Chess.chess;
import Chess.sendinqueue;
import UserInsatance.Userlistoperations;
import com.google.gson.Gson;
import peices.peices;

import java.util.LinkedList;

import static java.lang.System.out;


public class GameLogic {

    public static GameProcess GP;
    public static String presentuser="";
    public static String presenttype="";

    public GameLogic(){}

    public GameLogic(String host, Userlistoperations ulo)
    {
        String returnstring="{\"user\":\"Created Instance\",\"data\":\"{}\"}";
        String clientqueue="from_"+ulo.getGI().getuuid();
        String EX_name="gameserver";
        new sendinqueue().sendinexchange(host, clientqueue, returnstring,EX_name);
        GP=new GameProcess();

    }

    private String getturn()
    {
        LinkedList<String> users=chess.ulo.getUuids();
        LinkedList<String> types=chess.ulo.getTypes();
        if(users.size()==1) {
            presentuser = users.get(0);
            presenttype=types.get(0);
        return presentuser;
        }
        if(presentuser.compareTo(users.get(0))==0) {
            presentuser=users.get(1);
            presenttype=types.get(1);
        }
        else
        {
            presentuser=users.get(0);
            presenttype=types.get(0);
        }
        return presentuser;
    }
    private void updatestatus()
    {
        int slots= chess.ulo.getemptyslots();
        if(slots!=0)
        {
            GP.gd.status="open";
        }
        else
        {
            GP.gd.status="playing";
        }
    }
    private void updatestatus(String up)
    {
        GP.gd.status=up;
    }

    public void adduser(String host)
    {
        updatestatus();
        presentuser=getturn();
        String returnstring=processreturnstring();

        sendupdates(host,returnstring);

    }

    private void sendupdates(String host,String returnstring)
    {
        String clientqueue="from_"+ chess.ulo.getGI().getuuid();
        String EX_name="gameserver";
        new sendinqueue().sendinexchange(host, clientqueue, returnstring,EX_name);

    }

    public String processreturnstring()
    {
        String user=presentuser;
        String type=presenttype;
        GameData gd=GP.gd;

        String returnstring= new Gson().toJson(gd).replace('"','\'');

        String out="{\"user\":\""+user+"\",\"type\":\""+type+"\",\"data\":\""+returnstring+"\"}";

        return out;
    }

    public void click(String host, int from, int to) {

        replacepointandupdate(from,to);

//        int fc=findcheck();
        presentuser=getturn();
//        if(fc==-1)
//            updatestatus();
//        else if(fc==0)
//            updatestatus(presentuser);
//        else
//            updatestatus("check");
        
        String returnstring=processreturnstring();

        sendupdates(host,returnstring);
    }

    private int findcheck() {
        int fk=findking(presenttype,GP.gd);
        presentuser=getturn();
        int[] movevals=getcheckmoves(presenttype,GP.gd,fk);

        if(movevals.length==0)
            return -1;
        presentuser=getturn();
        return removenonceck(presenttype,GP.gd,movevals);

    }

    private int removenonceck(String presenttype, GameData gdd, int[] movevals) {
        for(peices pi: gdd.pclist) {
            LinkedList<Integer> movelist = new LinkedList<>();
            for (int i : pi.getMove()) {
                for (int j : movevals) {
                    if (i == j)
                        movelist.add(i);
                }
            }
            pi.setMove(movelist.stream().mapToInt(j->j).toArray());
        }
        return 0;
    }

    private int[] getcheckmoves(String presenttype, GameData gd,int fk) {
        int index=0;
        LinkedList<Integer> ls=new LinkedList<>();
        for(peices pi: gd.pclist)
        {
            if(pi.getType().compareTo(presenttype)==0)
                continue;
            inner:
            for(int i : pi.getMove())
            {
                if(i==fk)
                {
                    for(int j:pi.getMove())
                    ls.add(j);
                    break inner;
                }
            }
        }

        return ls.stream().mapToInt(j->j).toArray();

    }

    private int findking(String presenttype, GameData gd) {
        int index=0;
        for(peices pi: gd.pclist)
        {
            if(pi.getClass().getName().compareTo("peices.king")==0
                    && pi.getType().compareTo(presenttype)==0)
            {
                return index;
            }
            index++;
        }
        return 0;
    }

    public void removeuser(String host) {
        updatestatus("User Exited");
        presentuser="";

        String returnstring=processreturnstring();

        sendupdates(host,returnstring);

    }

    private boolean replacepointandupdate(int from,int to)
    {
        GP.gd.pclist.set(to,GP.gd.pclist.get(from));

        Class clazz = null;
        try {
            clazz = Class.forName("peices.open");
            peices piv = (peices) clazz.newInstance();
            GP.gd.pclist.set(from,piv);

        } catch (Exception e) {
            e.printStackTrace();
        }

        int index = 0;
        for (peices pval : GP.gd.pclist) {
            //System.out.format("index %d\n",index);
            pval.setmove(index,GP.gd.pclist);
            index++;

//            if(pval.getClass().getName().compareTo("peices.pawn")!=0) continue;
//            System.out.println(pval.getClass().getName());
//            for(int i : pval.move)
//                System.out.println(i);

        }

        return true;
    }


}
