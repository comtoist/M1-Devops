package fr.uga.devops;

public class Main{

    public static void main(String[] args) {
        try{

            Datafram d = new Datafram("src/main/resources/csvTest.csv");
            Integer[] tab = {1,2,3};
            //Datafram d = new Datafram(tab,"Chiffre");
            d.printf(d.datafram);
            System.out.println();
            d.head(1);      
            
            
            String[] strings = {"Salut","Bonjour","Coucou"};
            Datafram ds = new Datafram(strings,"Chaine de caracteres");
            ds.printf(ds.datafram);
            System.out.println();
            ds.head(1);  
            
            Object[][] objs = {tab,strings};
            String[] labels = {"Chiffre","Chaine de caracteres"};
            String[] types = {"Integer","String"};
            Datafram dob = new Datafram(objs,labels,types);
            dob.printf(dob.datafram);
            System.out.println();
            dob.head(2); 
        }catch(Exception e){
            e.printStackTrace();
        }
    }
}
