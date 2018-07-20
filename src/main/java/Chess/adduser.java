package Chess;

import InstanceObjects.GameInstance;
import org.json.JSONObject;

import static java.lang.System.out;

public class adduser implements processinput {

    public void process(String data, String host, GameInstance GI)
    {
        out.println(data+" working");
        JSONObject jsonObj = new JSONObject(data);

        String name=jsonObj.getString("name");
        String uuid=jsonObj.getString("uuid");
        String password=jsonObj.getString("password");

        try {
            // thread to sleep for 1000 milliseconds
            Thread.sleep(1000);
        } catch (Exception e) {
            System.out.println(e);
        }


        if(chess.password!=null &&  chess.password.compareTo(password)!=0)
        {
            String returnstring="{\"error\":\"Wrong Password\",\"uuid\":\""+uuid+"\"}";

            String clientqueue="from_"+ chess.ulo.getGI().getuuid();
            String EX_name="gameserver";

            out.println(chess.password);
            out.println(password);
            new sendinqueue().sendinexchange(host, clientqueue, returnstring,EX_name);
            return;
        }



        if(chess.ulo.adduser(name,uuid))
            chess.GL.adduser(host); //game logic to send data to clients
        else
        {
            String returnstring="{\"error\":\"Slots Full or User exist\",\"uuid\":\""+uuid+"\"}";

            String clientqueue="from_"+ chess.ulo.getGI().getuuid();
            String EX_name="gameserver";
            new sendinqueue().sendinexchange(host, clientqueue, returnstring,EX_name);
        }

    }


}

