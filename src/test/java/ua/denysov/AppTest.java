package ua.denysov;

import org.junit.Test;

import static org.junit.Assert.*;

public class AppTest {

    int[] array = {55,22,66,22,77,55,66};
    int[] array1 = {55,55};

    @Test
    public void testSearchNumberWithoutPair(){
        assertEquals(App.searchNumberWithoutPair(array), 77);
    }

    @Test
    public void testSearchNumberWithoutPair1(){
        assertEquals(App.searchNumberWithoutPair(array1), -1);
    }
}