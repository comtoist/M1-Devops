package fr.uga.devops;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.util.Scanner;
//https://stackoverflow.com/questions/13569232/how-to-parse-a-string-variable-into-any-data-type-in-java
public class Datafram{

    ArrayList<Colonne> datafram;

    public Datafram(ArrayList<ArrayList<Object>> contenu){
        datafram = new ArrayList<Colonne>();
        for(int i=0;i<contenu.size();i++){
            Colonne<Object> col = new Colonne(contenu.get(i).get(0).toString());
            for(int j=0;j<contenu.get(i).size();j++){
                col.add(contenu.get(i).get(j));
            }
            datafram.set(i,col);
        }
    }


    public Datafram(String filename){
        try{

            datafram = new ArrayList<Colonne>();
            File getCSVFiles = new File(filename);
            Scanner sc = new Scanner(getCSVFiles);
            // sc.useDelimiter(",");
            int i =0;
            String text ="";
            String line;
            Scanner scLine;
            //Creation des colonnes avec les types
            int nbCol = 0;

            line = sc.nextLine();
            scLine = new Scanner(line);
            scLine.useDelimiter(",");
                //System.out.println(line);
            while(scLine.hasNext()){
                text = scLine.next();
                System.out.println("Text = "+text);
                Colonne col;
                if(text.equals("String")){
                    col = new Colonne<String>();
                }else if(text.equals("Integer")){
                    col = new Colonne<Integer>();                        
                    col.setType("Integer");
                    
                }else{
                    System.out.println("Type non reconnu");
                    col = new Colonne<>();
                }
                datafram.add(col);
                i++;
            }
            
            nbCol = i;
            i=0;
            //Creation des labels
            line = sc.nextLine();
            scLine = new Scanner(line);
            scLine.useDelimiter(",");
            while(scLine.hasNext()){
                text = scLine.next();
                System.out.println(text);
                Colonne col = datafram.get(i);
                col.setLabel(text);
                i++;
            }
            
            
            i=0;
            sc.useDelimiter(",|\\n");

            while(sc.hasNext()){
                text = sc.next();
                System.out.println(text);
                if(i == nbCol){
                    i=0;
                }
                
                
                Colonne col2 = datafram.get(i);
                if(col2.getType().equals("Integer")){
                    col2.add(Integer.parseInt(text));
                }else if(col2.getType().equals("String")){
                    col2.add(text);
                }
                i++;
                
            }
            sc.close(); 
        }catch(Exception e){
            e.printStackTrace();
        }
        }
        
        
        
    
}