package fr.uga.devops;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.*;

public class TestColonne{
    public Colonne col;

    @Before
    public void setUp() throws Exception{
        col = new Colonne("test");
    }

    @Test
    public void NoArgConstructor(){
        Colonne col2 = new Colonne();
    }

    @Test
    public void DoubleArgsConstructor(){
        Colonne col2 = new Colonne("Toto");
        assertEquals("Toto",col2.getLabel());
        assertEquals("String",col2.getType());
    }

    @Test
    public void TripleArgsConstructorString(){
        ArrayList<String> strArr = new ArrayList<String>(); 
        Colonne col2 = new Colonne("Toto","String",strArr);
        assertEquals("Toto",col2.getLabel());
        assertEquals("String",col2.getType());
       
    }

    @Test
    public void TripleArgsConstructorInteger(){
        ArrayList<String> intArr = new ArrayList<String>();
        Colonne col2 = new Colonne("Toto","Integer",intArr);
        assertEquals("Integer",col2.getType());
        assertTrue(!"String".equals(col2.getType()));

    }
    


    @Test 
    public void getLabelTest() throws Exception{
        String lab = col.getLabel();
        assertTrue(lab.equals("test"));
        assertFalse(lab.equals("dshfiosf"));
    }

    @Test
    public void getSizeTest() throws Exception{
        assertEquals(0,col.getSize());
        col.add("toto");
        assertEquals(1,col.getSize());
        col.add("jsdklfd");
        assertEquals(2,col.getSize());
        col.add("gkgk");
        assertEquals(3,col.getSize());
    }

    @Test
    public void getType(){
        System.out.println(col.getType());
        assertEquals("String",col.getType());
    }


    @Test
    public void add(){
        col.add("Toto");
        assertTrue(col.getLignes().contains("Toto"));
    }
    
    @Test
    public void add2args(){
        col.add("premier");
        col.add("troisieme");

        col.add(1,"deuxieme");
        assertEquals("deuxieme",col.getLignes().get(1));
    }

    @Test
    public void get(){
        col.add("premier");
        col.add("troisieme");
        col.add(1,"deuxieme");
        assertEquals("premier",col.getLignes().get(0));
    }

    @Test
    public void setLabel(){
        col.setLabel("Totu");
        assertEquals("Totu",col.getLabel());
    }

    @Test
    public void setType(){
        col.setType("Integer");
        assertEquals("Integer",col.getType());
    }




    
   

    


}
