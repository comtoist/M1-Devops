package fr.uga.devops;
import org.junit.*;
import static org.junit.Assert.*;

public class TestColonne{
    public Colonne col;

    @Before
    public void setUp() throws Exception{
        col = new Colonne("test");
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

}