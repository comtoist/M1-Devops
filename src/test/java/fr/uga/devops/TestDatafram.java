package fr.uga.devops;
import org.junit.*;
import static org.junit.Assert.*;

public class TestDatafram {
    public Datafram data;

    
    @Test
    public void testConstructeurCsv(){
        Datafram d = new Datafram("src/main/resources/csvTest.csv");
        assertTrue(true);
    }

    @Test 
    public void constructorIntegerArray(){
        Integer[] tab = {1,2,3};
        Datafram d = new Datafram(tab,"Chiffre");
        assertEquals(1,d.datafram.size());
        assertEquals(3,d.datafram.get(0).getSize());
    }
    @Test 
    public void constructorIntegerArrayLabel(){
        Integer[] tab = {1,2,3};
        Datafram d = new Datafram(tab,"Chiffre");
        assertEquals("Chiffre",d.datafram.get(0).getLabel());
    }
    @Test 
    public void constructorIntegerArrayLabelNotEquals(){
        Integer[] tab = {1,2,3};
        Datafram d = new Datafram(tab,"Chiffre");
        assertFalse(d.datafram.get(0).getLabel().equals("qdsf"));
    }

    @Test 
    public void constructorStringArray(){
        String[] strings = {"Salut","Bonjour","Coucou"};
        Datafram d = new Datafram(strings,"Chiffre");
        assertEquals(1,d.datafram.size());
        assertEquals(3,d.datafram.get(0).getSize());
    }
    @Test 
    public void constructorStringArrayLabel(){
        String[] strings = {"Salut","Bonjour","Coucou"};
        Datafram d = new Datafram(strings,"Chiffre");
        assertEquals("Chiffre",d.datafram.get(0).getLabel());
    }
    @Test 
    public void constructorStringArrayLabelNotEquals(){
        String[] strings = {"Salut","Bonjour","Coucou"};
        Datafram d = new Datafram(strings,"Chiffre");
        assertFalse(d.datafram.get(0).getLabel().equals("qdsf"));
    }

    @Test 
    public void constructorIntegerAndStringArray(){
        try{

            Integer[] tab = {1,2,3};
            String[] strings = {"Salut","Bonjour","Coucou"};
            
            Object[][] objs = {tab,strings};
            String[] labels = {"Chiffre","Chaine de caracteres"};
            String[] types = {"Integer","String"};
            
            Datafram d = new Datafram(objs,labels,types);
            assertEquals(2,d.datafram.size());
            assertEquals(3,d.datafram.get(0).getSize());
            assertEquals(3,d.datafram.get(1).getSize());
        }catch(Exception e){
            e.printStackTrace();
        }
    }

    // @Test
    // public void constructorIntegerAndStringArrayErreur() throws Exception{
    //     try{

    //         Integer[] tab = {1,2,3};
    //         String[] strings = {"Salut","Bonjour","Coucou"};
            
    //         Object[][] objs = {tab,strings};
    //         String[] labels = {"Chiffre","Chaine de caracteres"};
    //         String[] types = {"qsdsq","String"};//Erreur, qsdsq au lieu de Integer
            
    //         Exception thrown = Assertions.assertThrows(Exception.class, () -> {
    //             Datafram d = new Datafram(objs,labels,types);
    //         });
    //     }catch(Exception e){
    //         e.printStackTrace();
    //     }
    // }
        
        
}
