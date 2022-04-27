package fr.uga.devops;

import java.io.File;
import java.lang.reflect.Type;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.lang.reflect.Method;
import java.util.Scanner;

public class Datafram {

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
        for(int i=0; i < tab.length; i++){
            if(type[i].equals("Integer")){
                ArrayList<Integer> arr = new ArrayList<Integer>();
                for(int j=0; j < tab[i].length; j++){
                    arr.add((Integer)tab[i][j]);
                }
                col = new Colonne(label[i],"Integer",arr);
                datafram.add(col);
            }else if(type[i].equals("String")){
                ArrayList<String> arr = new ArrayList<String>();
                for(int j=0; j < tab[i].length; j++){
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
                text = text.replaceAll("\\R", "");
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
                text = text.replaceAll("\\R", "");
                Colonne col = datafram.get(i);
                col.setLabel(text);
                i++;
            }
            
            
            i=0;
            sc.useDelimiter(",|\\n");

            while(sc.hasNext()){
                text = sc.next();
                text = text.replaceAll("\\R", "");
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

    private Datafram(){
        datafram = new ArrayList<Colonne>();
    }

    private void addCol(Colonne col){
        datafram.add(col);
    }

    public Datafram getSubFram(ArrayList<Integer> listeLigne){
        Datafram sub = new Datafram();
        for(int i = 0; i < datafram.size(); i++){
            Colonne col = new Colonne();
            col.setLabel(datafram.get(i).getLabel());
            col.setType(datafram.get(i).getType());
            for(int j = 0; j < listeLigne.size(); j++){
                col.add(datafram.get(i).get(listeLigne.get(j)));
            }
            sub.addCol(col);
        }
        return sub;
    }

    public Datafram GroupBy(String op, String col, ArrayList<String> constante){
        int numCol = -1;
        int i = 0;
        ArrayList<Integer> listeLigne = new ArrayList<>();
        while(numCol < 0 && i < datafram.size()){
            if(col.equals(datafram.get(i).getLabel())){
                numCol = i;
            }
            i++;
        }
        if(i == datafram.size()){
            return getSubFram(new ArrayList<Integer>());
        }
        try{
            if(op.equals("=")){
                listeLigne = datafram.get(numCol).IsIn(constante);
            }else{
                listeLigne = datafram.get(numCol).IsNotIn(constante);
            }
        }catch(Exception e){
            e.printStackTrace();
        }
        return getSubFram(listeLigne);
    }

    public Datafram GroupBy(String op, String col, Integer constante){
        int numCol = -1;
        int i = 0;
        ArrayList<Integer> listeLigne = new ArrayList<>();
        while(numCol < 0 && i < datafram.size()){
            if(col.equals(datafram.get(i).getLabel())){
                numCol = i;
            }
            i++;
        }
        if(i == datafram.size()){
            return getSubFram(new ArrayList<Integer>());
        }
        try{
            listeLigne = datafram.get(numCol).GroupByComp(op,constante);
        }catch(Exception e){
            e.printStackTrace();
        }
        return getSubFram(listeLigne);
    }

    public void printf(){
        ArrayList<Colonne> dat = datafram;
        //On suppose que les colonnes font toutes la meme taille
        String titleTemplate = "%-20s ";
        int columnSize = dat.get(0).getSize();
        int nbColonnes = dat.size();
        int currentLine;

        //On parcours chaque ligne de chaque colonne et on l'affiche
        //Affichage des label
        for(int i=0;i<nbColonnes;i++){
            String label = dat.get(i).getLabel();
            System.out.printf(titleTemplate,label+" ");
        }
        System.out.println();

        //Affichages des colonnes            
        for(currentLine=0;currentLine<columnSize;currentLine++){
            for(int j=0;j<nbColonnes;j++){
                System.out.printf(titleTemplate,dat.get(j).get(currentLine));
            }
            System.out.println();
            
        }
    }

    public void printf(ArrayList<Colonne> dat){
        //On suppose que les colonnes font toutes la meme taille
        String titleTemplate = "%-20s ";
        int columnSize = dat.get(0).getSize();
        int nbColonnes = dat.size();
        int currentLine;

        //On parcours chaque ligne de chaque colonne et on l'affiche
        //Affichage des label
        for(int i=0;i<nbColonnes;i++){
            String label = dat.get(i).getLabel();
            System.out.printf(titleTemplate,label+" ");
        }
        System.out.println();

        //Affichages des colonnes            
        for(currentLine=0;currentLine<columnSize;currentLine++){
            for(int j=0;j<nbColonnes;j++){
                System.out.printf(titleTemplate,dat.get(j).get(currentLine));
            }
            System.out.println();
            
        }
    }

    @Override
    public boolean equals(Object dat){
        boolean res = true;
        if(datafram.size()==((Datafram)dat).datafram.size()){
            for(int i=0; i < datafram.size(); i++){
                if(!datafram.get(i).equals(((Datafram)dat).datafram.get(i))){
                    res = false;
                }
            }
        }
        return res;
    }

}
