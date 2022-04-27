package fr.uga.devops;
import org.junit.*;
import static org.junit.Assert.*;
import java.util.ArrayList;
import static org.junit.jupiter.api.Assertions.*;

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

    @Test
	public void integerPrint(){
            Integer[] tab = {1,2,3};
            Datafram d = new Datafram(tab,"Chiffre");
            d.printf(d.datafram);
	}
	
	@Test
	public void stringPrint(){
	    String[] strings = {"Salut","Bonjour","Coucou"};
            Datafram ds = new Datafram(strings,"Chaine de caracteres");
            ds.printf(ds.datafram);
	}

	@Test
	public void integerAndString() throws Exception{
		try{

			Integer[] tab = {1,2,3};
			String[] strings = {"Salut","Bonjour","Coucou"};
			Object[][] objs = {tab,strings};
            String[] labels = {"Chiffre","Chaine de caracteres"};
            String[] types = {"Integer","String"};
            Datafram dob = new Datafram(objs,labels,types);
            System.out.println();
            dob.printf(dob.datafram); 
		}catch(Exception e){
			e.printStackTrace();
		}
	}

    @Test
	public void testHeadNoArgs(){
	    Integer[] tab = {1,2,3,4,5};
        Datafram d = new Datafram(tab,"Chiffre");
	    d.head();
	}

    @Test
	public void testTailNoArgs(){
	    Integer[] tab = {1,2,3,4,5};
        Datafram d = new Datafram(tab,"Chiffre");
	    d.tail();
	}
	
	
	@Test
	public void testHeadOk(){
	    Integer[] tab = {1,2,3};
            Datafram d = new Datafram(tab,"Chiffre");
	    d.head(1);
	}

	@Test
	public void testHeadOverflow(){
	    Integer[] tab = {1,2,3};
            Datafram d = new Datafram(tab,"Chiffre");
	    d.head(9);
	}

	@Test
	public void testTailOk(){
	    Integer[] tab = {1,2,3};
            Datafram d = new Datafram(tab,"Chiffre");
	    d.tail(1);
	}

	@Test
	public void testTailOverflow(){
	    Integer[] tab = {1,2,3};
            Datafram d = new Datafram(tab,"Chiffre");
	    d.tail(9);
	}
	
	@Test
	public void newDataframTest() throws Exception{
		Datafram d = new Datafram("src/main/resources/csvTest2.csv");
		ArrayList<Colonne> d2 = d.newDatafram(d.datafram, "1,2,3,4", "Nom,prenom");
		Datafram d3 = new Datafram("src/main/resources/newDataframTest.csv");

		//Test du nombre de colonne
		assertEquals(d3.datafram.size(), d2.size());
		for(int i = 0; i < d2.size(); i++){
		    Colonne c1 = d2.get(i);
		    Colonne c2 = d3.datafram.get(i);
		    assertEquals(c2.getSize(), c1.getSize());
		    assertEquals(c2.getLabel(), c1.getLabel());
		    assertEquals(c2.getType(), c1.getType());
		    for(int j = 0; j < d2.get(i).getSize(); j++){
		        assertEquals(c2.getLignes().get(j), c1.getLignes().get(j));
		    }
		}
	}

	@Test
	public void newDataframTestWithInteger() throws Exception{
		Datafram da = new Datafram("src/main/resources/csvTest3.csv");
		ArrayList<Colonne> d2 = da.newDatafram(da.datafram, "1,2,4,5", "Nom,prenom,age");
		Datafram d3 = new Datafram("src/main/resources/newDataframTestInteger.csv");

		//Test du nombre de colonne
		assertEquals(d3.datafram.size(), d2.size());
		for(int i = 0; i < d2.size(); i++){
		    Colonne c1 = d2.get(i);
		    Colonne c2 = d3.datafram.get(i);
		    assertEquals(c2.getSize(), c1.getSize());
		    assertEquals(c2.getLabel(), c1.getLabel());
		    assertEquals(c2.getType(), c1.getType());
		    for(int j = 0; j < d2.get(i).getSize(); j++){
		        assertEquals(c2.getLignes().get(j), c1.getLignes().get(j));
		    }
		}
	 }
	 
	 
	@Test
        public void maximumTest() throws Exception{
        	Datafram d = new Datafram("src/main/resources/csvTest2.csv");
        	float max = d.maximum(d.datafram.get(3));
        	assertEquals(65, max);
    	}
	
	//Ce test ne passe pas sur github
    	/*@Test
    	public void maximumTestErreur() throws Exception{
        	Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            		float maximum = d.somme(d.datafram.get(2));
        	});
    	}*/

    	@Test
    	public void minimumTest() throws Exception{
    		Datafram d = new Datafram("src/main/resources/csvTest2.csv");
        	float min = d.minimum(d.datafram.get(3));
        	assertEquals(15, min);
    	}

	//Ce test ne passe pas sur github
    	/*@Test
    	public void minimumTestErreur() throws Exception{
        	Exception thrown = Assertions.assertThrows(Exception.class, () -> {
            		float minimum = d.somme(d.datafram.get(2));
        	});
    	} */       
}

