package fr.uga.devops;

public class Main{

    public static void main(String[] args) {
        try{

            Datafram d = new Datafram("src/main/resources/csvTest.csv");
            Integer[] tab = {1,2,3};
            //Datafram d = new Datafram(tab,"Chiffre");
            
            System.out.println();
                
            String[] strings = {"Salut","Bonjour","Coucou"};
            Datafram ds = new Datafram(strings,"Chaine de caracteres");
            
            System.out.println();
            
            
            Object[][] objs = {tab,strings};
            String[] labels = {"Chiffre","Chaine de caracteres"};
            String[] types = {"Integer","String"};
            Datafram dob = new Datafram(objs,labels,types);
           
            System.out.println();
             
        }catch(Exception e){
            e.printStackTrace();
        }
        
    }
}