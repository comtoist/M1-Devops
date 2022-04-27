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
*label* et *type* doivent être de la même taille