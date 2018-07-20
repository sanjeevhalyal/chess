package UserInsatance;

import InstanceObjects.GameInstance;

import java.util.LinkedList;

public class Userlistoperations {
    LinkedList<UserInfo> users=new LinkedList<>();
    int totalslots;
    GameInstance GI;
    public Userlistoperations(int totalslots,GameInstance GI)
    {
        this.totalslots=totalslots;
        this.GI=GI;
    }

    public boolean adduser(String name, String uuid)
    {
        if(totalslots>users.size())
        {
            for(String o : getusers())
            {
                if(o.compareTo(name)==0)
                    return false;
            }
            String type=users.size()==1?"light":"dark";

            users.add(new UserInfo(name,uuid,type));
            GI.addusers(name);
            return true;
        }
        return false;
    }

    public boolean removeuser(String uuid,String name)
    {
        UserInfo rm=null;
        for(UserInfo o : users)
        {
            if(o.getuuid().compareTo(uuid)==0)
                rm=o;
        }
        if(! users.remove(rm))
            return false;
        return GI.removeuser(name);


    }
    public LinkedList<String> getUuids() {
        LinkedList<String> list=new LinkedList<String>();

        for(UserInfo o : users)
        {
            list.add(o.getuuid());
        }
        return list;
    }

    public LinkedList<String> getTypes() {
        LinkedList<String> list=new LinkedList<String>();

        for(UserInfo o : users)
        {
            list.add(o.getType());
        }
        return list;
    }

    public LinkedList<String> getusers() {
        LinkedList<String> list=new LinkedList<String>();

        for(UserInfo o : users)
        {
            list.add(o.getuser());
        }
        return list;
    }

    public int getemptyslots()
    {
        return totalslots-users.size();
    }

    public GameInstance getGI()
    {
        return GI;
    }

}
