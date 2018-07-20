package peices;

import java.util.LinkedList;

public class open extends peices {
    public open() {
        super();
        super.assign(peicesimgvalue.open);
    }
    @Override
    public void setmove(int index,LinkedList<peices> pc) {
        move=new int[]{index};
    }
}
