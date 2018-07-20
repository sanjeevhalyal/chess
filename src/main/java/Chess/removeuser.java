package Chess;

import InstanceObjects.GameInstance;
import org.json.JSONObject;

import java.lang.reflect.InvocationTargetException;

public class removeuser implements processinput {
    public void process(String data, String host, GameInstance GI) {
        System.out.println(data+" working");
        JSONObject jsonObj = new JSONObject(data);

        String name=jsonObj.getString("name");
        String uuid=jsonObj.getString("uuid");


        if(chess.ulo.removeuser(uuid,name))
            chess.GL.removeuser(host); //game logic to send data to clients
        else
        {
            String returnstring="{\"error\":\"Critical Error Removing Close the windows please\",\"uuid\":\""+uuid+"\"}";

            String clientqueue="from_"+ chess.ulo.getGI().getuuid();
            String EX_name="gameserver";
            new sendinqueue().sendinexchange(host, clientqueue, returnstring,EX_name);
        }

        if(chess.ulo.getusers().size()==0)
        {
                GI.resetuser();
            try {
                chess.GL.GP.gd.resetboxlist();
            } catch (IllegalAccessException e) {
                e.printStackTrace();
            } catch (InstantiationException e) {
                e.printStackTrace();
            } catch (ClassNotFoundException e) {
                e.printStackTrace();
            } catch (NoSuchMethodException e) {
                e.printStackTrace();
            } catch (InvocationTargetException e) {
                e.printStackTrace();
            }

        }

    }

}
