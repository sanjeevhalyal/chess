package peices;

import org.junit.jupiter.api.Test;

import java.lang.reflect.InvocationTargetException;

import static java.lang.System.out;
import static org.junit.jupiter.api.Assertions.*;

class peicesTest {

    @Test
    public void start() throws NoSuchMethodException, IllegalAccessException, InvocationTargetException {
        String[] arr="rook,dark".split(",");
        out.println(arr.length);

    }

}