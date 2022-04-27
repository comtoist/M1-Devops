package fr.uga.devops;
import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.util.Scanner;
public class Datafram{

    ArrayList<Colonne> datafram;

    public Datafram(Integer[] tab,String label){
        Colonne col;
        ArrayList<Integer> arr = new ArrayList<Integer>();
        for(int i=0;i<tab.length;i++){
            arr.add(tab[i]);
        }
        col = new Colonne(label,"Integer",arr);
        datafram = new ArrayList<Colonne>();        
        datafram.add(col);
    }

    public Datafram(String[] tab,String label){
        Colonne col;
        ArrayList<String> arr = new ArrayList<String>();
        for(int i=0;i<tab.length;i++){
            arr.add(tab[i]);
        }
        col = new Colonne(label,"String",arr);
        datafram = new ArrayList<Colonne>();        
        datafram.add(col);
    }

    public Datafram(Object[][] tab,String[] label,String[] type) throws Exception{
        Colonne col;
        datafram = new ArrayList<Colonne>(); 
        for(int i=0;i<tab.length;i++){
            if(type[i].equals("Integer")){
                ArrayList<Integer> arr = new ArrayList<Integer>();
                for(int j=0;j<tab[i].length;j++){
                    arr.add((Integer)tab[i][j]);
                }
                col = new Colonne(label[i],"Integer",arr);
                datafram.add(col);
            }else if(type[i].equals("String")){
                ArrayList<String> arr = new ArrayList<String>();
                for(int j=0;j<tab[i].length;j++){
                    arr.add((String)tab[i][j]);
                }
                col = new Colonne(label[i],"String",arr);
                datafram.add(col);
            }else{
                throw new Exception("Type non supporte");
            }
            
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
                Colonne col;
                if(text.equals("String")){
                    col = new Colonne<String>();
                }else if(text.equals("Integer")){
                    col = new Colonne<Integer>();                        
                    col.setType("Integer");
                    
                }else{
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
                Colonne col = datafram.get(i);
                col.setLabel(text);
                i++;
            }
            
            
            i=0;
            sc.useDelimiter(",|\\n");

            while(sc.hasNext()){
                text = sc.next();
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
