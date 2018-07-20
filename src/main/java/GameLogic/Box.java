package GameLogic;


import java.util.LinkedList;


public class Box {

    String value;
    String pcval;
    String img;
    public Box()
    {
        value="open";
    }

    public String getValue() {
        return value;
    }

    public void setValue(String value) {
        this.value = value;
    }
}
