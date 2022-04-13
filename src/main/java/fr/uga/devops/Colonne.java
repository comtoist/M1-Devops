package fr.uga.devops;
import java.util.ArrayList;

import javax.sound.sampled.ReverbType;
public class Colonne<E>{
    private String label;
    private String type;
    private ArrayList<E> lignes;

    public Colonne(){
        label = "";
        type = "String";
        lignes = new ArrayList<>();
    }
    public Colonne(String lab){
        label = lab;
        lignes = new ArrayList<>();
    }

    public Colonne(String lab,ArrayList<E> lig){
        label = lab;
        lignes = lig;
    }

    public Object get(int i){
        return lignes.get(i);
    }

    public void add(E o){
        lignes.add(o);
    }

    public void add(int i,E o){
        lignes.add(i,o);
    }

    public int getSize(){
        return lignes.size();
    }
    public String getLabel(){
        return label;
    }

    public ArrayList<E> getLignes(){
        return lignes;
    }

    public void setLabel(String la){
        label = la;
    }

    public void setLignes(ArrayList<E> lig){
        lignes = lig;
    }

    public void setType(String t){
        type = t;
    }

    public String getType(){
        return type;
    }
 

}
