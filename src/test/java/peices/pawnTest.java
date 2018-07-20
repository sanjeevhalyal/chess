package peices;

import GameLogic.GameData;
import org.junit.jupiter.api.Test;

import java.io.IOException;

import static org.junit.jupiter.api.Assertions.*;

class pawnTest extends GameData{

    @Test
    void movetest() throws IOException {
        GameData gd=new GameData();

        int index = 0;
        for (peices pval : gd.pclist) {
            System.out.format("index %d\n",index);
            pval.setmove(index,gd.pclist);
            index++;

            if(pval.getClass().getName().compareTo("peices.queen")!=0) continue;
            System.out.println(pval.getClass().getName());
            for(int i : pval.move)
            System.out.println(i);

        }
    }

    public static void clearScreen() {
        System.out.print("\033[H\033[2J");
        System.out.flush();
    }

}