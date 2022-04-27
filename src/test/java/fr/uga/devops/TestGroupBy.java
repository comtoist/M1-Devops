package fr.uga.devops;

import org.junit.*;

import fr.uga.devops.Datafram;

import static org.junit.Assert.*;
import java.util.*;

public class TestGroupBy{
    public Colonne col;
    public Datafram d;
    public Datafram res1;
    public Datafram res2;
    public Datafram res3;
    public Datafram res4;
    public Datafram res5;
    public Datafram res6;
    public Datafram res7;

    @Before
    public void setUp() throws Exception{
        col = new Colonne("test");
        d = new Datafram("src/main/resources/ResearchCSV.csv");
        res1 = new Datafram("src/main/resources/Res1.csv");
        res2 = new Datafram("src/main/resources/Res2.csv");
        res3 = new Datafram("src/main/resources/Res3.csv");
        res4 = new Datafram("src/main/resources/Res4.csv");
        res5 = new Datafram("src/main/resources/Res5.csv");
        res6 = new Datafram("src/main/resources/Res6.csv");
        res7 = new Datafram("src/main/resources/Res7.csv");
    }

    @Test
    public void TestGroupByInteger(){
        Datafram res = d.GroupBy(">", "age", 65);

        assertEquals("MT",res,res1);
        res = d.GroupBy(">=", "age", 65);
        assertEquals("MOE",res,res2);
        res = d.GroupBy("<", "age", 65);
        assertEquals("LT",res,res3);
        res = d.GroupBy("<=", "age", 65);
        assertEquals("LOE",res,res4);
        res = d.GroupBy("=", "age", 65);
        assertEquals("EQ",res,res6);
        res = d.GroupBy("!=", "age", 65);
        assertEquals("NE",res,res5);
        res = d.GroupBy("=", "age", 22);
        assertEquals("Vide",res,res7);
    }

    @Test
    public void TestGroupByString(){
        ArrayList<String> liste = new ArrayList<String>();
        liste.add("Pomme");
        ArrayList<String> liste2 = new ArrayList<String>();
        liste2.add("Benoit");
        Datafram res = d.GroupBy("=", "Nom", liste);
        assertEquals("Equal",res,res6);
        res = d.GroupBy("!=", "Nom", liste);
        assertEquals("Not Equal",res,res5);
        res = d.GroupBy("=", "Nom", liste);
        assertEquals("Vide",res,res7);
    }
}