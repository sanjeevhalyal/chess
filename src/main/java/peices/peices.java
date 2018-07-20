package peices;


import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.LinkedList;

;

public abstract class peices {
    protected int[] move;

    public int[] getMove() {
        return move;
    }

    public void setMove(int[] move) {
        this.move = move;
    }

    public String getType() {
        return type;
    }

    public void setType(String type) {
        this.type = type;
    }

    public String getImg() {
        return img;
    }

    public void setImg(String img) {
        this.img = img;
    }

    public peicesimgvalue getPiv() {
        return piv;
    }

    public void setPiv(peicesimgvalue piv) {
        this.piv = piv;
    }

    protected  String type;
    protected String img;
    protected peicesimgvalue piv;

    public peices() {

    }

    public void assign (String type) {
        Method m = null;
        try {
            m = peicesimgvalue.class.getMethod(type);
            img = (String) m.invoke(piv);
            this.type=m.getName();
//            System.out.print(piv);
//            System.out.println(m.getName());
//            System.out.println(returnVal);

        } catch (NoSuchMethodException e) {
            e.printStackTrace();
        } catch (IllegalAccessException e) {
            e.printStackTrace();
        } catch (InvocationTargetException e) {
            e.printStackTrace();
        }
    }

    public void assign (peicesimgvalue piv) {
        this.piv=piv;
    }

    peices(peicesimgvalue piv) {
        this.piv=piv;
    }

    public abstract void setmove(int index,LinkedList<peices> pc);
}
