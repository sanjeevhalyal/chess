package Chess;

import InstanceObjects.GameInstance;
import org.json.JSONObject;

public class click implements processinput {
    public void process(String data, String host, GameInstance GI) {

        JSONObject jsonObj = new JSONObject(data);
        String[] click=jsonObj.getString("click").split(",");
        int from=Integer.parseInt(click[0]),to=Integer.parseInt(click[1]);
        chess.GL.click(host,from,to);
    }
}
