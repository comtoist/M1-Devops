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

        //Affiche les 5 dernieres lignes
        public void tail(){
            String titleTemplate = "%-20s ";
            int columnSize = datafram.get(0).getSize();
            int nbColonnes = datafram.size();
            int currentLine;

            //On parcours chaque ligne de chaque colonne et on l'affiche
            //Affichage des label
            for(int i=0;i<nbColonnes;i++){
                String label = datafram.get(i).getLabel();
                System.out.printf(titleTemplate,label+" ");
            }
            System.out.println();

            //Affichages des colonnes            
            for(currentLine=columnSize-5;currentLine<columnSize;currentLine++){
                for(int j=0;j<nbColonnes;j++){
                    System.out.printf(titleTemplate,datafram.get(j).get(currentLine));
                }
                System.out.println();
                
            }

        }

        //Affiche les n dernieres lignes
        public void tail(int n){
            String titleTemplate = "%-20s ";
            int columnSize = datafram.get(0).getSize();
            int nbColonnes = datafram.size();
            int currentLine;

            //On parcours chaque ligne de chaque colonne et on l'affiche
            //Affichage des label
            for(int i=0;i<nbColonnes;i++){
                String label = datafram.get(i).getLabel();
                System.out.printf(titleTemplate,label+" ");
            }
            System.out.println();

            //Verification index >0
            int resOk = columnSize-n;
            if(resOk<0){
                resOk=0;
            }   
            //Affichages des colonnes    
            for(currentLine=resOk;currentLine<columnSize;currentLine++){
                for(int j=0;j<nbColonnes;j++){
                    System.out.printf(titleTemplate,datafram.get(j).get(currentLine));
                }
                System.out.println();
                
            }

        }

        //Affiche les 5 premieres lignes
        public void head(){
            String titleTemplate = "%-20s ";
            int columnSize = datafram.get(0).getSize();
            if(columnSize>5){
                columnSize=5;
            }
            int nbColonnes = datafram.size();
            
            int currentLine;

            //On parcours chaque ligne de chaque colonne et on l'affiche
            //Affichage des label
            for(int i=0;i<nbColonnes;i++){
                String label = datafram.get(i).getLabel();
                System.out.printf(titleTemplate,label+" ");
            }
            System.out.println();

            //Affichages des colonnes            
            for(currentLine=0;currentLine<columnSize;currentLine++){
                for(int j=0;j<nbColonnes;j++){
                    System.out.printf(titleTemplate,datafram.get(j).get(currentLine));
                }
                System.out.println();
                
            }

        }

        //Affiche les n premieres lignes
        public void head(int n){
            String titleTemplate = "%-20s ";
            int columnSize = datafram.get(0).getSize();;
            if(n<columnSize){
                columnSize = n;
            }
            int nbColonnes = datafram.size();
            int currentLine;

            //On parcours chaque ligne de chaque colonne et on l'affiche
            //Affichage des label
            for(int i=0;i<nbColonnes;i++){
                String label = datafram.get(i).getLabel();
                System.out.printf(titleTemplate,label+" ");
            }
            System.out.println();

            //Affichages des colonnes            
            for(currentLine=0;currentLine<columnSize;currentLine++){
                for(int j=0;j<nbColonnes;j++){
                    System.out.printf(titleTemplate,datafram.get(j).get(currentLine));
                }
                System.out.println();
                
            }

        }
        
        public ArrayList<Colonne> newDatafram(ArrayList<Colonne> datafram, String line, String col){
		ArrayList<Colonne> datafram2 = new ArrayList<>();
		Scanner scline = new Scanner(line);
		scline.useDelimiter(",");
		String text;
		ArrayList<String> TabLine = new ArrayList<>();
		while(scline.hasNext()){
		    text = scline.next();
		    TabLine.add(text);
		}

		Scanner scCol = new Scanner(col);
		scCol.useDelimiter(",");
		ArrayList<String> TabIndex = new ArrayList<>();
		while(scCol.hasNext()){
		    text = scCol.next();
		    TabIndex.add(text);
		}

		for(int i = 0; i < TabIndex.size(); i++){
		    int k = 0;
		    while(!(datafram.get(k).getLabel().equals(TabIndex.get(i)))){
		        k++;
		    }
		    Colonne c = datafram.get(k);
		    Colonne newC = new Colonne(c.getLabel());
		    newC.setType(c.getType());
		    newC.setLabel(c.getLabel());

		    for(int j = 0; j < TabLine.size(); j++){
		        newC.add(c.get(Integer.parseInt(TabLine.get(j))-1));
		    }
		    datafram2.add(newC);
		}
		printf(datafram2);
		return datafram2;
       }
}
