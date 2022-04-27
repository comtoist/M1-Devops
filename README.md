# M1-Devops
[![Java CI with Maven](https://github.com/comtoist/M1-Devops/actions/workflows/maven.yml/badge.svg)](https://github.com/comtoist/M1-Devops/actions/workflows/maven.yml)

## Fonctionnalité

  - Creation de data frame en utilisant :
      * Un chemin vers un fichier csv.
      * Un tableau d'objet représentant l'intérieur de la frame, la liste des labels et la liste des types.
      * Un tableau d'entier (ou de chaine de caractère) avec un label (pour créer un data frame d'une seule colonne).
  - Affichage de l'entièreté ou des premières (ou dernières lignes) de la data frame.
  - La sélection des lignes à utiliser d'une data frame.
  - Création de data frame en utilisant seulement certaines lignes de data frame existant.
  - Demander des statistiques sur les colonnes d'entier d'un data frame.

## Installation

Une fois les fichiers sources téléchargés depuis le dépot git il faut utiliser les lignes :
```sh
mvn compile
mvn package
```

Une archive jar sera alors créée dans le dossier target.

## Utilisation

En utilisant l'archive créée par la compilation dans le classpath de vos projet, vous aurez accès au dataframe.

Pour utiliser le data frame il faut penser à l'importer :
```sh
import fr.uga.devops.Datafram
```

## Documentation
Voici la spécification des classes utilisées :

### Datafram

#### Constructors
```sh
Datafram(Integer[] tab,String label)
```
Crée un data frame contenant une colonne *label* avec *tab* comme contenu.

```sh
Datafram(String[] tab,String label)
```
Crée un data frame contenant une colonne *label* avec *tab* comme contenu.

```sh
Datafram(Object[][] tab,String[] label,String[] type)
```
Crée un data frame contenant plusieurs colonnes portant comme nom *label* avec *type* comme type et *tab* comme contenu.
*label* et *type* doivent être de la même taille. 
*tab* doit aussi avoir sa première dimension (nombre de colonne) égale à celle de *label* et *type*.

```sh
Datafram(String filename)
```
Crée un data frame à partir d'un fichier au format csv dont le chemin relatif est *filename*.

```sh
Datafram getSubFram(ArrayList<Integer> listeLigne)
```
Crée un sous data frame à partir du data frame appelé. 
Ce data frame possèdera les lignes dont l'indice se trouve dans *listLigne*.

```sh
Datafram GroupBy(String op, String col, ArrayList<String> constante)
```
Crée un sous data frame en suivant une contrainte sur la colonne labelisé *col*.
*op* avec la valeur "=" signifie que l'on veut uniquement les lignes dont la colonne *col* vaut *constante*.
Si *op* vaut autre chose alors on veut cherche toute celles qui ne valent pas *constante*.

```sh
Datafram GroupBy(String op, String col, Integer constante)
```
Crée un sous data frame selon la valeur de la colonne labélisé *col*.
*op* peut prendre les valeurs ">", ">=", "<", "<=", "=" et "!=".
*constante* est la valeur utilisé avec l'opérateur *op* pour choisir les lignes.
Exemple : GroupBy("<","age",50) va choisir toute les lignes pour lesquelles "age" est inférieur à 50.

```sh
void printf()
```
Affiche le contenu du data frame dans la sortie standard.

```sh
void printf(ArrayList<Colonne> dat)
```
Affiche le contenu du data frame *dat*.

```sh
boolean equals(Object dat)
```
Permet de comparer deux data frames.

```sh
ArrayList<Colonne> newDatafram(ArrayList<Colonne> datafram, String line, String col)
```
Crée un dataframe qui est une sélection d'index de ligne *line* et de label de colonne *col* d'un dataframe *datafram*.

```sh
void head(int n)
```
Affiche les *n* premières lignes du data frame.

void head()
```
Affiche les 5 premières lignes du data frame.

void tail(int n)
```
Affiche les *n* dernières lignes du data frame.

void tail()
```
Affiche les 5 dernières lignes du data frame.